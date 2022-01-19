package jp.ac.hcs.ZemiS3A1.main;



import lombok.Data;

/**
 *ユーザー情報 Data
 */
@Data
public class RegisterData {

	/**
	 * 名前
	 */
	private String user_name;

	/**
	 * メールアドレス
	 */
	private String user_id;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * ユーザ権限
	 */
	private String user_role;

	/**
	 * 有効・無効
	 */
	private boolean enabled;

}
