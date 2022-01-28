package jp.ac.hcs.ZemiS3A1.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;

	/**
	 * 指定されたユーザIDのカード情報を全件取得する
	 * @param user_id
	 * @return
	 */
	public CardEntity selectAll(String user_id) {

		CardEntity cardEntity = new CardEntity();
		//CardRepository cardRepository2 = new CardRepository();

		log.info("確認用：" + user_id);

		try {
			cardEntity = cardRepository.selectAll(user_id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			cardEntity = null;
		}

		return cardEntity;
	}

	/**
	 *
	 * @param card_id
	 * @return
	 */
		public CardData selectOne(int card_id) {
		CardData data;
		try {
			data = cardRepository.selectOne(card_id);
		}catch (DataAccessException e) {
			e.printStackTrace();
			data = null;
		}
		return data;
		}


	/**
	 * タイトル情報のみでの生成
	 * @param data
	 * @param card_id
	 * @param card_title
	 * @return
	 */
	public boolean insert(String user_id, String card_title, CardData data) {

		int rowNumber = cardRepository.insertTitle(data, user_id);

		boolean result = (rowNumber > 0) ? true : false;

		if (result) {
			log.warn("登録成功");
		} else {
			log.warn("登録失敗");
		}

		return result;
	}

	/**
	 * カードの変更
	 * @param data
	 * @param card_id
	 * @return
	 */

	public boolean updateOne(CardData data) {

		int rowNumber = cardRepository.updateOne(data);

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
