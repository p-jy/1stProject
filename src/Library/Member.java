package Library;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Member{
	//회원
			//아이디, 비밀번호, 이름, 연락처
	
	private String id, pw, num, name; 
	
	
	public List<RentReturn> list = new ArrayList<RentReturn>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return id.equals(other.id) && pw.equals(other.pw) && num.equals(other.num);
	}
	
	
	public Member(String id, String pw, String name, String num) {
	        this.id = id;
	        this.pw = pw;
	        this.name = name;
	        this.num = num;
	}
	
	public void print() {
		System.out.println("-----------------");
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
		System.out.println("이름 : " + name);
		System.out.println("연락처 : " + num);
		System.out.println("-----------------");
	}
	
	public void update(Member newMb) {
		if(newMb == null) {
			return;
		}
	    if (newMb.name != null) this.name = newMb.name;
	    if (newMb.num != null) this.num = newMb.num;
	}
	
	

}
