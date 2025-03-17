package db2.ex1.model.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Rent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9055960272294860934L;
	//String : id
	
	private int reNum; //re_num
	@NonNull
	private String id; //re_me_id
	@NonNull
	private String code; //re_bo_code
	private String state; //re_state
	private Date rentDate; //re_rent_date
	private Date returnDate; //re_return_date
	private Date dueDate; //re_due_date
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rent other = (Rent) obj;
		return reNum == other.reNum;
	}
	
	public String getDateStr(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-DD-dd");
		return f.format(date);
	}
	
	@Override
	public String toString() {
		return "도서코드 : " + code + " 대여일 : " + getDateStr(rentDate) + " ";
	}
	
	
	
}