package jp.ac.hcs.ZemiS3A1.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
public class UserRepository {

	/** SQL 全件取得(ユーザID昇順) */
	private static final String SQL_SELECT_ALL = "SELECT * FROM m_user ORDER BY user_id";

	/** SQL ユーザ情報を1件取得 */
	private static final String SQL_SELECT_ONE = "SELECT m_user (user_id, user_name, user_role, created_at, registeredperson_id) FROM m_user WHERE user_id = ?";

	/** SQL ユーザ情報の1件追加 */
	private static final String SQL_INSERT_OEN = "INSERT INTO m_user (user_id, encrypted_password, user_name, user_role, created_at, registeredperson_id) VALUES (?, ?, ?, ?, ?, ?)";

	/** SQL ユーザ情報削除 */
	private static final String SQL_DELETE_OEN = "DELETE FROM m_user WHERE user_id = ?";

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * ユーザの全データを取得
	 * @return
	 * @throws DataAccessException
	 */

	public UserEntity selectAll() throws DataAccessException  {

		List<Map<String, Object>> resultList = jdbc.queryForList(SQL_SELECT_ALL);

		UserEntity userEntity = mappingResult(resultList);
		return userEntity;
	}

	/**
	 * ユーザ新規登録
	 * @param data
	 * @param user_id
	 * @return
	 */

	public int getUserInsert(UserData data, String user_id) {

		int rowNumber = jdbc.update(SQL_INSERT_OEN,
				data.getUser_id(),
				passwordEncoder.encode(data.getEncrypted_password()),
				data.getRegisteredperson_id()
				);

		return rowNumber;
	}

	/**
	 * ユーザデータをEntity形式でマッピングする
	 * @param resultList Userテーブルから取得したデータ
	 * @return
	 */

	public UserEntity mappingResult(List<Map<String, Object>> resultList) {

		UserEntity entity = new UserEntity();

		for (Map<String, Object> map : resultList) {

			UserData data = new UserData();

			data.setUser_id((String) map.get("user_id"));
			data.setEncrypted_password((String) map.get("encrypted_password"));
			data.setUser_name((String) map.get("user_name"));
			data.setUser_role((String) map.get("user_role"));
			data.setCreated_at((Data) map.get("created_at"));
			data.setRegisteredperson_id((String) map.get("registerredperson_id"));
			data.setUpdated_at((Data) map.get("updated_at"));
			data.setChanger_id((String) map.get("changer_id"));
			data.setCreated_at((Data) map.get("created_at"));
			data.setEnabled((boolean) map.get("enabled"));
		}

		return entity;

	}
}
