package board;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * ボード情報をまとめて管理するエンティティクラス
 */

@Data
public class BoardEntity {
	
	/** ボード情報のリスト*/
	private List<BoardData> boardList = new ArrayList<BoardData>();
}
