package db2.ex1.model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class Rent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9055960272294860934L;
	//String : id
	
	private int num; //re_num
	private String id; //re_me_id
	private String code; //re_bo_code
	private String state; //re_state
	private Date rentDate; //re_rent_date
	private Date returnDate; //re_return_date
	private Date dueDate; //re_due_date
	
	public Rent(String id, String code) {
		this.id = id;
		this.code = code;
		rentDate = new Date();
		this.state = "대여";
		//getTime : 밀리세컨드
		long times = rentDate.getTime() + 3600 * 24 * 7 * 1000L; 
		this.dueDate = new Date(times);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rent other = (Rent) obj;
		return num == other.num;
	}
	
	public String getDateStr(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}
	
	@Override
	public String toString() {
		return "도서코드 : " + code + " 대여일 : " + getDateStr(rentDate) + " 반납 예정일 : " + getDateStr(dueDate);
	}
	
	
	
}