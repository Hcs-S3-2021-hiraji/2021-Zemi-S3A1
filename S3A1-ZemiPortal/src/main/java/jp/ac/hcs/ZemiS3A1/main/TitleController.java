package jp.ac.hcs.ZemiS3A1.main;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログインに関する機能・画面を制御する
*/
@Controller
public class TitleController {
	/**
	 * タイトル画面を表示する
	 * @param model
	 * @return タイトル画面
	 */
	@GetMapping("/title")
	public String getTitle(Model model) {
		return "title";
	}

}
