package Library;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Member implements Serializable{
	private static final long serialVersionUID = 1L;
	//회원
	//아이디, 비밀번호, 이름, 연락처
	@NonNull
	private String id; //아이디
	@NonNull
	private String pw; //비번
	@NonNull
	private String name; //이름
	@NonNull
	private String num; //연락처
	
	public Member(String id) {
		this.id = id;
		this.pw = pw;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(id, other.id);
	}

	public void update(Member member) {
		
		this.name = member.name;
		this.num = member.num;
		
	}
	
	
	
}