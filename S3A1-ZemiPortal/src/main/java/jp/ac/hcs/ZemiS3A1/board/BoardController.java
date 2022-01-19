package jp.ac.hcs.ZemiS3A1.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
}
