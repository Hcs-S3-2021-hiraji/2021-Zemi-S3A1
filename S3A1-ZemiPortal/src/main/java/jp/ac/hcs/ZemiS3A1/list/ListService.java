package jp.ac.hcs.ZemiS3A1.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ListService {

	@Autowired
	ListRepository listRepository;

	/**
	 * 指定されたユーザIDのリスト情報を全件取得する
	 * @param user_id
	 * @return
	 */
	public ListEntity selectAll(String user_id) {

		ListEntity listEntity = new ListEntity();
		//CardRepository cardRepository2 = new CardRepository();

		log.info("確認用：" + user_id);

		try {
			listEntity = listRepository.selectAll(user_id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			listEntity = null;
		}

		return listEntity;
	}

	/**
	 * タイトル情報のみでの生成
	 * @param data
	 * @param list_id
	 * @param list_title
	 * @return
	 */
	public boolean insert(String user_id, String list_title, ListData data) {

		int rowNumber = listRepository.insertlistTitle(data, user_id);

		boolean result = (rowNumber > 0) ? true : false;

		if (result) {
			log.warn("登録成功");
		} else {
			log.warn("登録失敗");
		}

		return result;
	}
}
