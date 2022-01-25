package jp.ac.hcs.ZemiS3A1.list;

import lombok.Data;

@Data
public class ListData {

	/**
	 * １件分のリスト情報
	 */

	/**
	 * リストID
	 */

	private Integer list_id;

	/**
	 * リストタイトル
	 */

	private String list_title;

	/**
	 * ユーザ名
	 */

	private String user_id;

	/**
	 * カードID
	 */

	private Integer card_id;
}
