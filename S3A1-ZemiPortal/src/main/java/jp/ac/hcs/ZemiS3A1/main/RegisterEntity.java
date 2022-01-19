package jp.ac.hcs.ZemiS3A1.main;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RegisterEntity {

	private List<RegisterData> registerList = new ArrayList<RegisterData>();
}
