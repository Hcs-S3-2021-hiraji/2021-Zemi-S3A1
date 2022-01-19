package jp.ac.hcs.ZemiS3A1.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService {
	
	@Autowired
	ListRepository listRepository;
}
