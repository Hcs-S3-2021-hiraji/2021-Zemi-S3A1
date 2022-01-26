package jp.ac.hcs.ZemiS3A1.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ListRepository {

	/** SQL リスト情報を全件取得 */
	private static final String SQL_LIST_ALL = "SELECT * FROM list WHERE user_id = ?";

	/** SQL リストの追加 */
	private static final String SQL_INSERT_LISTTITLE = "INSERT INTO list (list_id, list_title, user_id) VALUES ((SELECT MAX(list_id) + 1 FROM list), ?, ?)";

	@Autowired
	JdbcTemplate jdbc;

	/**
	 * リスト情報全件取得
	 * @param user_id
	 * @return
	 * @throws DataAccessException
	 */
	public ListEntity selectAll(String user_id) throws DataAccessException {

		//JdbcTemplate jdbc2 = new JdbcTemplate();

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		resultList = jdbc.queryForList(SQL_LIST_ALL, user_id);
		ListEntity listEntity = mappingResult(resultList);
		return listEntity;
	}

	/**
	 * リストタイトルを1件追加
	 * @param data
	 * @param list_id
	 * @return
	 */

	public int insertlistTitle(ListData data, String user_id) throws DataAccessException {

		int rowNumber = jdbc.update(SQL_INSERT_LISTTITLE,
				data.getList_title(),
				user_id
				);

		return rowNumber;
	}

	/**
	 * リスト情報をマッピング形式に変換
	 * @param resultList
	 * @return
	 */

	public ListEntity mappingResult(List<Map<String, Object>> resultList) throws DataAccessException {
		ListEntity entity = new ListEntity();

		for (Map<String, Object> map : resultList) {

			ListData data = new ListData();

			data.setList_id((Integer) map.get("list_id"));
			data.setList_title((String) map.get("list_title"));
			data.setUser_id((String) map.get("user_id"));
			entity.getListAll().add(data);
		}

		return entity;

	}

}
