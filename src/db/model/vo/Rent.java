package db.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;

@Data
public class Rent {
	
	private int re_num;
	private Date re_rent_date;
	@NonNull
	private String me_id;
	@NonNull
	private Book book;
	
	
}
