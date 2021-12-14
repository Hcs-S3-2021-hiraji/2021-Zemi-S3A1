package user;

import lombok.Data;

@Data
public class UserData {
	/**
	 * １件分のユーザ情報
	 */
	
	/**
	 * ユーザID
	 */
	private String user_id;
	
	/**
	 * 暗号化されたパスワード
	 */
	private String encrypted_password;
	
	/**
	 * ユーザ名
	 */
	private String user_name;
	
	/**
	 * ユーザ権限
	 */
	private String user_role;
	
	/**
	 * ユーザ状態
	 */
	private String user_status;
	
	/**
	 * 登録日時
	 */
	private Data created_at;
	
	/**
	 * 登録者のユーザID
	 */
	private String registeredperson_id;
	
	/**
	 * 更新日時
	 */
	private Data updated_at;
	
	/**
	 * 更新者のユーザID
	 */
	private String changer_id;
	
	/**
	 * 有効・無効
	 */
	private boolean enabled;
}
