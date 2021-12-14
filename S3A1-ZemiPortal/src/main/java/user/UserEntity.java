package user;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserEntity {
	
	private List<UserData> userList = new ArrayList<UserData>();
}
