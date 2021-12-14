package card;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
public class CardRepository {
	
	/** SQL カード情報を全件取得 */
	private static final String SQL_CARD_ALL = "SELECT * FROM card";
	
	/** SQL カードの追加 */
	private static final String SQL_CARD_INSERT = "INSERT INTO card (card_id, card_title, card_detail, user_id, card_date, card_check, card_description, card_detail_description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	/** SQL カードの修正 */
	private static final String SQL_CARD_REVISION = "UPDATA card() WHERE card_id = ?";
	
	/** SQL カードの削除 */
	private static final String SQL_CARD_DELETE = "DELETE FROM card WHERE card_id = ?";
	
	@Autowired
	private JdbcTemplate jdbc;
	
public CardEntity selectAll() throws DataAccessException  {
		
		List<Map<String, Object>> resultList = jdbc.queryForList(SQL_CARD_ALL);
		
		CardEntity cardEntity = mappingResult(resultList);
		return cardEntity;
	}
	
	/**
	 * カード情報を1件追加
	 * @param data
	 * @param card_id
	 * @return
	 */
	
	public int insertOne(CardData data, String card_id) {
		
		int rowNumber = jdbc.update(SQL_CARD_INSERT,
				card_id,
				data.getCard_title(),
				data.getCard_detail(),
				data.getCard_date(),
				data.getCard_check(),
				data.getCard_description(),
				data.getCard_detail_description());
		
		return rowNumber;
	}
	
	/**
	 * カードの変更
	 * @param data
	 * @param card_id
	 * @return
	 */
	
	public int updateOne(CardData data, String card_id) {
		
		int rowNumber = jdbc.update(SQL_CARD_REVISION,
				data.getCard_detail(),
				data.getCard_check(),
				data.getCard_detail_description(),
				data.getCard_description(),
				data.getCard_date());
		return rowNumber;
	}
	
	/**
	 * カードの削除
	 * @param card_id
	 * @return
	 */
	
	public int deleteOne (String card_id) {
		
		int rowNumber = jdbc.update(SQL_CARD_DELETE, card_id);
		
		return rowNumber;
	}
	
	/**
	 * カード情報をマッピング形式に変換
	 * @param resultList
	 * @return
	 */
	
	public CardEntity mappingResult(List<Map<String, Object>> resultList) {
		CardEntity entity = new CardEntity();
		
		for (Map<String, Object> map : resultList) {
			
			CardData data = new CardData();
			
			data.setCard_id((String) map.get("card_id"));
			data.setCard_title((String) map.get("card_title"));
			data.setCard_detail((String) map.get("card_detail"));
			data.setUser_id((String) map.get("user_id"));
			data.setList_id((String) map.get("list_id"));
			data.setCard_date((Data) map.get("card_date"));
			data.setCard_check((String) map.get("card_check"));
			data.setCard_description((String) map.get("card_description"));
			data.setCard_detail_description((String) map.get("card_detail_description"));
		}
		
		return entity;
		
	}
}
