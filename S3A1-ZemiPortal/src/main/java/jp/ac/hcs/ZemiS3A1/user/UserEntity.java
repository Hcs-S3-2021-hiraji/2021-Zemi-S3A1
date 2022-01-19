package jp.ac.hcs.ZemiS3A1.user;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserEntity {
	
	private List<UserData> userList = new ArrayList<UserData>();
}
