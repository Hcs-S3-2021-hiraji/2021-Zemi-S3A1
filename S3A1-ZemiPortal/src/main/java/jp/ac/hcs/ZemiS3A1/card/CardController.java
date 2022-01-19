package card;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CardController {

	@Autowired
	CardService cardService;

	/**
	 * カード情報を取得し画面に出力する
	 * @param model
	 * @return
	 */
	@GetMapping("index")
	public String getTitleCard(Principal principal,Model model) {
		CardEntity cardEntity = cardService.selectAll(principal.getName());

		model.addAttribute("cardEntity", cardEntity);
		return "index";
	}

	/**
	 * カードの追加
	 * @param principal
	 * @param data
	 * @param card_id
	 * @return
	 */

	@PostMapping("index")
	public String insertCard(@RequestParam("card_title") String card_title,
			Principal principal, Model model, CardData data, Integer card_id) {

		boolean result = cardService.insert(principal.getName(), card_id, card_title);
		log.info("[" + principal.getName() + "]：" + "タイトル：" + card_title);

		if(result) {
			log.warn("成功");
		} else {
			log.warn("失敗");
		}

		return getTitleCard(principal,model);
	}

	@GetMapping("ボード画面")
	public String updateOne(Principal principal, CardData data, String card_id) {

		boolean result = cardService.updateOne(data, card_id);

		if(result) {
			log.warn("[" + principal.getName() + "]カード情報の変更成功");
		} else {
			log.warn("[" + principal.getName() + "]カード情報の変更失敗");
		}

		return "ボード画面";
	}

	/*@PostMapping("")
	public String deleteOne(@RequestParam("card_id") String card_id, Principal principal, Model model) {

		boolean result = cardService.deleteOne(card_id);

		if(result) {
			log.warn("[" + principal.getName() + "]カードの削除成功");
		} else {
			log.warn("[" + principal.getName() + "]カードの削除失敗");
		}

		return getCardList(model);
	}*/


}
