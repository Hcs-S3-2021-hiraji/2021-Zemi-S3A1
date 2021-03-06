package ZemiS3A1;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/** ユーザーIDとパスワードとアカウントの有効性を取得するSQL */
	private static final String USER_SQL = "SELECT user_id, encrypted_password as password, enabled FROM m_user WHERE user_id = ?";

	/** ユーザーIDと権限を取得するSQL */
	private static final String ROLE_SQL = "SELECT user_id, role FROM m_user WHERE user_id = ?";

	@Override
	public void configure(WebSecurity web) throws Exception {

		// 静的リソースへのアクセスには、セキュリティを適用しない
		web.ignoring().antMatchers("/css/∗∗", "/h2-console/∗∗");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// ログイン不要ページの設定
		http.authorizeRequests().antMatchers("/css/**").permitAll() // cssへアクセス許可
				.antMatchers("/login").permitAll() // ログインページは直リンクOK
				.antMatchers("/signup").permitAll() // 新規ユーザー登録画面は直リンクOK
				.antMatchers("/user/**").hasAuthority("ROLE_ADMIN") // ユーザ管理機能は管理権限ユーザに許可
				//.antMatchers("/h2-console/**").permitAll() // XXX h2-console使用時は有効にする.
				.anyRequest().authenticated(); // それ以外は直リンク禁止

		//ログイン処理
		http.formLogin().loginProcessingUrl("/login") // ログイン処理のパス
				.loginPage("/login") // ログインページの指定
				.failureUrl("/login") // ログイン失敗時の遷移先
				.usernameParameter("user_id") // ログインページのユーザID
				.passwordParameter("password") // ログインページのパスワード
				.defaultSuccessUrl("/", true); // ログイン成功後の遷移先

		//ログアウト処理
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutUrl("/logout") //ログアウト処理のパス
				.logoutSuccessUrl("/login"); //ログアウト成功後の遷移先

		// (開発用)CSRF対策 無効設定
		// XXX h2-console使用時は有効にする.
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// ログイン処理時のユーザ情報をDBから取得する
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(USER_SQL)
				.authoritiesByUsernameQuery(ROLE_SQL)
				.passwordEncoder(passwordEncoder());
	}
}
