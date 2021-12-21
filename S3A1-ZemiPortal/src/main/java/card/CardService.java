package card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CardService {

	@Autowired
	static
	CardRepository cardRepository;

	/**
	 * 指定されたユーザIDのカード情報を全件取得する
	 * @param user_id
	 * @return
	 */
	public CardEntity selectAll(String user_id) {

		CardEntity cardEntity;
		try {
			cardEntity = cardRepository.selectAll(user_id);
		} catch(DataAccessException e) {
			e.printStackTrace();
			cardEntity = null;
		}

		return cardEntity;
	}

	public CardEntity selectAllb() {

		CardEntity cardEntity;
		try {
			cardEntity = cardRepository.selectAllb();
		} catch(DataAccessException e) {
			e.printStackTrace();
			cardEntity = null;
		}

		return cardEntity;
	}

	/**
	 * タイトル情報のみでの生成
	 * @param data
	 * @param card_id
	 * @param card_title
	 * @return
	 */
	public static boolean insertTitle(CardData data, int card_id, String card_title) {

		int rowNumber = cardRepository.insertTitle(data, card_id, card_title);

		boolean result = (rowNumber > 0) ? true : false;

		return result;
	}

	/**
	 * カードの追加
	 * @param data
	 * @param card_id
	 * @return
	 */

	public static boolean insert(String user_id, CardData data, int card_id, String card_title) {

		int rowNumber = cardRepository.insert(user_id, data, card_id, card_title);

		boolean result = (rowNumber > 0) ? true : false;

		return result;
	}

	/**
	 * カードの変更
	 * @param data
	 * @param card_id
	 * @return
	 */

	public boolean updateOne(CardData data, String card_id) {

		int rowNumber = cardRepository.updateOne(data, card_id);

		boolean result = (rowNumber > 0) ? true : false;

		return result;
	}

	/**
	 * カードの削除
	 * @param card_id
	 * @return
	 */

	public boolean deleteOne(String card_id) {

		int rowNumber = cardRepository.deleteOne(card_id);

		boolean result = (rowNumber > 0) ? true : false;

		return result;
	}
}
