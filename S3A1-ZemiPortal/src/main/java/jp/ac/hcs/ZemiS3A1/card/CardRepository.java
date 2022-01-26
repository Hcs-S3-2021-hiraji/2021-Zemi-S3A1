package jp.ac.hcs.ZemiS3A1.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepository {

	/** SQL カード情報を全件取得 */
	private static final String SQL_CARD_ALL = "SELECT * FROM card WHERE user_id = ?";

	/** SQL カードの追加 */
	private static final String SQL_INSERT_TITLE = "INSERT INTO card (card_id, card_title, user_id) VALUES ((SELECT MAX(card_id) + 1 FROM card), ?, ?)";

	/** SQL 1件取得 */
	private static final String SQL_SELECT_CARD_ONE = "SELECT * FROM card WHERE card_id = ?";

	/** SQL カードの修正 */
	private static final String SQL_CARD_REVISION = "UPDATE card SET card_title = ? WHERE card_id = ?";

	/** SQL カードの削除 */
	private static final String SQL_CARD_DELETE = "DELETE FROM card WHERE card_id = ?";

	@Autowired
	JdbcTemplate jdbc;

	/**
	 * カード情報全件取得
	 * @param user_id
	 * @return
	 * @throws DataAccessException
	 */
	public CardEntity selectAll(String user_id) throws DataAccessException {

		//JdbcTemplate jdbc2 = new JdbcTemplate();

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		resultList = jdbc.queryForList(SQL_CARD_ALL, user_id);
		CardEntity cardEntity = mappingResult(resultList);
		return cardEntity;
	}

	/**
	 * カードテーブルからカードIDをキーにデータを1件を取得.
	 * @param card_id 検索するカードID
	 * @return CardEntity
	 * @throws DataAccessException
	 */
	public CardData selectOne(Integer card_id) throws DataAccessException {
		List<Map<String, Object>> resultList = jdbc.queryForList(SQL_SELECT_CARD_ONE, card_id);
		CardEntity entity = mappingResult(resultList);
		// 必ず1件のみのため、最初のCardDataを取り出す
		CardData data = entity.getCardList().get(0);
		return data;
	}

	/**
	 * カードタイトルを1件追加
	 * @param data
	 * @param card_id
	 * @return
	 */

	public int insertTitle(CardData data, String user_id) throws DataAccessException {

		int rowNumber = jdbc.update(SQL_INSERT_TITLE,
				data.getCard_title(),
				user_id
				);

		return rowNumber;
	}



	/**
	 * カードの変更
	 * @param data
	 * @param card_id
	 * @return
	 */

	public int updateOne(CardData data) {

		int rowNumber = jdbc.update(SQL_CARD_REVISION,
				data.getCard_title(),
				data.getCard_id());
		return rowNumber;
	}

	/**
	 * カードの削除
	 * @param card_id
	 * @return
	 */

	public int deleteOne(String card_id) {

		int rowNumber = jdbc.update(SQL_CARD_DELETE, card_id);

		return rowNumber;
	}

	/**
	 * カード情報をマッピング形式に変換
	 * @param resultList
	 * @return
	 */

	public CardEntity mappingResult(List<Map<String, Object>> resultList) throws DataAccessException {
		CardEntity entity = new CardEntity();

		for (Map<String, Object> map : resultList) {

			CardData data = new CardData();

			data.setCard_id((Integer) map.get("card_id"));
			data.setCard_title((String) map.get("card_title"));
			data.setUser_id((String) map.get("user_id"));
			entity.getCardList().add(data);
		}

		return entity;

	}
}
