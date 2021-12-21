package card;

import lombok.Data;

@Data
public class CardData {

	/**
	 * １件分のカード情報
	 */

	/**
	 * カードID
	 */

	private int card_id;

	/**
	 * カードタイトル
	 */

	private String card_title;

	/**
	 * カード詳細
	 */

	private String card_detail;

	/**
	 * ユーザID
	 */

	private String user_id;

	/**
	 * 	ユーザネーム
	 */
	private String user_name;


	/**
	 * リストID
	 */

	private String list_id;

	/**
	 * 日付
	 */

	private Data card_date;

	/**
	 * チェックリスト
	 */

	private String card_check;

	/**
	 * 説明
	 */

	private String card_description;

	/**
	 * 詳細説明
	 */

	private String card_detail_description;
}
