package jp.ac.hcs.ZemiS3A1.user;

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

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	/**
	 * ユーザ一覧表示
	 * @param model モデル
	 * @return
	 */
	@GetMapping("user/userList")
	public String getUserList(Model model) {
		
		
		
		return "user/userList";
		
	}
	
	
	/**
	 * ユーザ新規登録
	 * @return
	 */
	@GetMapping("ログイン画面")
	public String getUserInsert(UserForm userform, Model model) {
		return "画面";
	}
	
	/**
	 * ユーザ新規登録
	 */
	@PostMapping()
	public String insertOne(@ModelAttribute @Validated UserForm form, BindingResult bindingresult, Model model, Principal principal) {
		
		if(bindingresult.hasErrors()) {
			return getUserInsert(form, model);
		}
		
		UserEntity userEntity = userService.selectAll();
		
		model.addAttribute("userEntity", userEntity);
		
		for (UserData data : userEntity.getUserList()) {
			if (form.getUser_id().equals(data.getUser_id())){
				return "user/userInsert";
			}
		}
		
		UserData data = new UserData();
		data.setUser_id(form.getUser_id());
		data.setEncrypted_password(form.getPassword());
		data.setUser_name(form.getUser_name());
		
		boolean result = userService.getUserInsert(data, principal.getName());

		if (result) {
			log.info("[" + principal.getName() + "]ユーザ登録成功");
		} else {
			log.warn("[" + principal.getName() + "]ユーザ登録失敗");
		}

		return getUserList(model);
	}
}
