package card;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * カード情報をまとめて管理するエンティティクラス
 */

@Data
public class CardEntity {
	
	/** カード情報のリスト*/
	private List<CardData> cardList = new ArrayList<CardData>();
}
