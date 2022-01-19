package jp.ac.hcs.ZemiS3A1.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザー情報 Service
 */
@Service
@Transactional
public class RegisterService {
	/**
	 * ユーザー情報 Repository
	 */
	@Autowired
	RegisterRepository registerRepository;


	/**
	 * ユーザー情報 新規登録
	 * @param register ユーザー情報
	 */
	public boolean create(RegisterData data/*RegisterRequest registerRequest*/) {
//		RegisterData register = new RegisterData();
//		register.setUser_name(registerRequest.getUser_name());
//		register.setUser_id(registerRequest.getUser_id());
//		register.setEncrypted_password(registerRequest.getPassword());

		int rowNumber = registerRepository.getRegisterInsert(data);

		boolean result = (rowNumber > 0) ? true : false;

		return result;

	}
}
