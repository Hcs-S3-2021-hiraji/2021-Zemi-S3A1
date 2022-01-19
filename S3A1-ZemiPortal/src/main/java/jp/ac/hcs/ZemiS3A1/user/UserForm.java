package jp.ac.hcs.ZemiS3A1.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserForm {
	
	//ユーザID
	@NotBlank(message = "{require_check}")
	@Email(message = "{email_check}")
	private String user_id;
	
	//パスワード
	@NotBlank(message = "{require_check}")
	@Length(min = 5, max = 100, message = "{length_check}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$",message="{pattern_check}")
	private String password;
	
	//ユーザ名
	@NotBlank(message="{require_check}")
	private String user_name;
}
