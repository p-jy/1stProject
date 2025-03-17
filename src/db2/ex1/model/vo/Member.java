package db2.ex1.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Member implements Serializable{
	private static final long serialVersionUID = 9055960272294860934L;
	//회원
	//아이디, 비밀번호, 이름, 연락처
	@NonNull
	private String id; //me_id
	@NonNull
	private String pw; //me_pw
	@NonNull
	private String name; //me_name
	@NonNull
	private String num; //me_num
	private String del; //me_del
	private String canRent; //me_can_rent
	private Date canRentDate; //me_can_rent_date
	private int noRent; //me_no_rent
	
	
	
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

	@Override
	public String toString() {
		return "ID : " + id + " 이름 : " + name + " 번호 : " + num;
	}
	
	
	
	
}