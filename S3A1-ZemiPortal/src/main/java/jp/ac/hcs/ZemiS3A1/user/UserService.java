package jp.ac.hcs.ZemiS3A1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * ユーザ情報を全件取得
	 * @return
	 */
	
	public UserEntity selectAll() {
		
		return userRepository.selectAll();
	}
	
	/**
	 * ユーザ新規追加
	 * @param userData
	 * @param user_id
	 * @return
	 */
	
	public boolean getUserInsert(UserData userData, String user_id) {
		
		int rowNumber = userRepository.getUserInsert(userData, user_id);
		
		boolean result = (rowNumber > 0) ? true : false;
		
		return result;
	}
	
}
