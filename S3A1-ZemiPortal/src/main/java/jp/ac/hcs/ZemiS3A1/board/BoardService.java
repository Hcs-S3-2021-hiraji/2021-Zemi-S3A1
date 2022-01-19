package jp.ac.hcs.ZemiS3A1.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	BoardRepository boardRepository;
}
