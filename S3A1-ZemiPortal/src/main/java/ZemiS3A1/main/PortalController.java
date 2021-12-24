package ZemiS3A1.main;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import card.CardEntity;
import card.CardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PortalController {

	@RequestMapping("/")
	public String index(Principal principal, Model model) {
		CardService cardService = new CardService();
		log.info("test" + principal.getName());
		CardEntity cardEntity = cardService.selectAll(principal.getName());
		model.addAttribute("cardEntity",cardEntity);
		log.info("ログイン確認用");
		return "index";
	}

}
