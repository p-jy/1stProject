package db.model.vo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
	
	private String code; //bo_code
	@NonNull
	private String title; //bo_title
	@NonNull
	private String author; //bo_author
	@NonNull
	private String publisher; //bo_publisher
	
	private boolean rent; //bo_rent
}
