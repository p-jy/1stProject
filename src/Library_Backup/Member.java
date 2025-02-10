package Library_Backup;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
	//회원
	//아이디, 비밀번호, 이름, 연락처
	private String id; //아이디
	private String pw; //비번
	private String name; //이름
	private String num; //연락처
	
	}