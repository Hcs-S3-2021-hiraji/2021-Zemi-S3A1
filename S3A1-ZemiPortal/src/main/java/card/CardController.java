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
	 * カードの追加
	 * @param principal
	 * @param data
	 * @param card_id
	 * @return
	 */
	
	@PostMapping("card/insert")
	public String insertOne(Principal principal, CardData data, String card_id) {
		
		boolean result = cardService.insertOne(data, card_id);
		
		if(result) {
			log.warn("[" + principal.getName() + "]カードの追加成功");
		} else {
			log.warn("[" + principal.getName() + "]カードの追加失敗");
		}
		
		return "board/boardList";
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
	
	@PostMapping("")
	public String deleteOne(@RequestParam("card_id") String card_id, Principal principal, Model model) {
		
		boolean result = cardService.deleteOne(card_id);
		
		if(result) {
			log.warn("[" + principal.getName() + "]カードの削除成功");
		} else {
			log.warn("[" + principal.getName() + "]カードの削除失敗");
		}
		
		return getCardList(model);
	}
	
	public String getCardList(Model model) {
		
		CardEntity cardEntity = cardService.selectAll();
		
		model.addAttribute("cardEntity" + cardEntity);
		
		return "ボード画面";
	}
}
