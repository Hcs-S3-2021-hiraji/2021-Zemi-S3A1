package list;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * リスト情報をまとめて管理するエンティティクラス
 */

@Data
public class ListEntity {
	
	/** リスト情報*/
	private List<ListData> listAll = new ArrayList<ListData>();
}
