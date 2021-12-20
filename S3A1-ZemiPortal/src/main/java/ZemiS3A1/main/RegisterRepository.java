package ZemiS3A1.main;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterRepository {

	/** SQL 全権取得*/
	private static final String SQL_SELECT_ALL = "SELECT * FORM m_user ORDER BY user_id";

	/** SQL ユーザーの追加*/
	private static final String SQL_REGISTER_INSERT = "INSERT INTO m_user (user_id,encrypted_password,user_name,user_role,enabled) VALUES (?,?,?,?,?)";

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 *  ユーザーの全データを取得
	 * @return
	 * @throws DataAccessException
	 */
	public RegisterEntity selectAll() throws DataAccessException{

		List<Map<String, Object>> resultList = jdbc.queryForList(SQL_SELECT_ALL);

		RegisterEntity registerEntity = mappingResult(resultList);
		return registerEntity;
	}


	/**
	 * ユーザー新規登録
	 * @param data
	 * @param user_id
	 * @return
	 */
	public int getRegisterInsert(RegisterData data) throws DataAccessException {

		int rowNumber = jdbc.update(SQL_REGISTER_INSERT,
				data.getUser_id(),
				passwordEncoder.encode(data.getPassword()),
				data.getUser_name(),
				data.getUser_role(),
				data.isEnabled());

		return rowNumber;
	}

	public RegisterEntity mappingResult(List<Map<String, Object>> resultList) {

		RegisterEntity entity = new RegisterEntity();

		for (Map<String, Object> map : resultList) {

			RegisterData data = new RegisterData();

			data.setUser_id((String) map.get("user_id"));
			data.setPassword((String) map.get("encrypted_password"));
			data.setUser_name((String) map.get("user_name"));
			data.setUser_role((String) map.get("user_role"));
			data.setEnabled(true);

			entity.getRegisterList().add(data);
		}

		return entity;
	}

}
