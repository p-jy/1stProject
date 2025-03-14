package db.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Member implements Serializable{

	private static final long serialVersionUID = 2356607493856389751L;
	
	@NonNull
	private String id, pw, name, num;
	
	public List<Rent> list = new ArrayList<Rent>();
	
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

	public void print() {
		
		System.out.println("ID : " + id + " 이름 : " + name + " 번호 : " + num);
		
	}
	

}