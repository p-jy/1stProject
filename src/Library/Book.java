package Library;

import java.io.Serializable;
import lombok.Data;

@Data
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9055960272294860934L;
	
	//도서
		//도서번호, 도서명, 작가명, 출판사, 대여/반납상태
	
	private int bookNum; //도서번호
	private String title; //책 제목
	private String author; //작가명
	private String publisher; //출판사
	private char rentReturn; //대여반납상태
	
}
