package card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
	
	@Autowired
	CardRepository cardRepository;
	
	
	public CardEntity selectAll() {
		
		CardEntity entity = cardRepository.selectAll();
		
		return entity;
	}
	
	/**
	 * カードの追加
	 * @param data
	 * @param card_id
	 * @return
	 */
	
	public boolean insertOne(CardData data, String card_id) {
		
		int rowNumber = cardRepository.insertOne(data, card_id);
		
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
