package ZemiS3A1.main;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * ログインに関する機能・画面を制御する
*/
@Slf4j
@Controller
public class RegisterController {

	/**
	 * ユーザー情報 Service
	 */
	@Autowired
	private RegisterService registerService;

	/**
	 * 新規登録画面を表示する
	 *@param model Model
	 *@return 新規登録画面
	 */
	@GetMapping("register")
	public String displayAdd(Model model) {
		model.addAttribute("registerRequest",new RegisterRequest());
		return "register";
	}

	/**
	 * ユーザー新規登録
	 * @param registerRequest リクエストデータ
	 * @param model Model
	 * @return 新規登録画面
	 */
	@PostMapping("register")
	public String create(@Validated @ModelAttribute RegisterRequest registerRequest,
			BindingResult result,Model model,Principal principal,
			RegisterData data) {

//		if(result.hasErrors()) {
//			//入力チェックエラーの場合
//			java.util.List<String>errorList = new ArrayList<String>();
//			for(ObjectError error : result.getAllErrors()) {
//				errorList.add(error.getDefaultMessage());
//			}
//			model.addAttribute("validationError",errorList);
//			return "register";
//		}

		data.setUser_name(registerRequest.getUser_name());
		data.setUser_role("VALID");
		data.setEnabled(true);

		/*ユーザー情報の登録*/
		boolean sw = registerService.create(data);

		if(sw) {
			log.info("登録成功");
		} else {
			log.info("登録失敗");
		}

		return "login";
	}








}