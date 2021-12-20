package ZemiS3A1.main;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class RegisterRequest {

	/**ユーザーID*/
	@NotEmpty(message = "{require_check}")
	@Email(message = "{email_check}")
	private String user_id;

	/**パスワード*/
	@NotBlank(message = "{require_check}")
	@Length(min = 5, max = 100, message = "{length_check}")
	@Pattern(regexp = "^[a-zA-Z0-9] + $",message = "{pattern_check}")
	private String password;

	/**名前*/
	@NotBlank(message = "{require_check}")
	private String user_name;

	/**ユーザ権限*/
	@NotNull(message =  "{require_check}")
	private String user_role;

	/**有効・無効*/
	@NotNull(message = "{require_check}")
	private boolean enabled;
}
