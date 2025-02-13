package Library;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 7d9bc6091f9a09074f9fdbee6b43795d1b3d4723
import java.util.Objects;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Member implements Serializable{
<<<<<<< HEAD
	private static final long serialVersionUID = 1L;
=======
	
	private static final long serialVersionUID = 9055960272294860934L;
	
>>>>>>> 7d9bc6091f9a09074f9fdbee6b43795d1b3d4723
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
	
	public Member(String id, String pw) {
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