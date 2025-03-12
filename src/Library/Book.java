package Library;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import lombok.Data;

@Data
public class Book implements Serializable{
	
	private static final long serialVersionUID = 9055960272294860934L;
	
	//도서
		//도서코드, 도서명, 작가명, 출판사, 대여/반납상태
	private String bookCode; //도서코드
	private String category;
	private String title; //책 제목
	private String author; //작가명
	private String publisher; //출판사
	private boolean rentReturn;
	
	
	public Book(String bookCode, String title, String author, String publisher) {
		this.bookCode = bookCode;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.rentReturn = true;
	}
	
	public Book(int max, String category, String title, String author, String publisher) {
		this("", title, author, publisher);
		String prefix;
		try {
			prefix = Book.getCodePrefix(category);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		DecimalFormat format = new DecimalFormat("000");
		String suffix = format.format(max + 1);
		this.bookCode = prefix + suffix;
	}

	

	public static String getCodePrefix(String category) throws Exception {
		switch(category) {
		case "총류":
			return "000";
		case "철학":
			return "100";
		case "종교":
			return "200";
		case "사회과학":
			return "300";
		case "자연과학":
			return "400";
		case "기술과학":
			return "500";
		case "예술":
			return "600";
		case "언어":
			return "700";
		case "문학":
			return "800";
		case "역사":
			return "900";
		default:
			throw new Exception("[잘못된 카테고리입니다.]");
		}
	}
	
	public static String getCategory(int categoryNum) {
		switch(categoryNum) {
		case 0:
			return "총류";
		case 1:
			return "철학";
		case 2:
			return "종교";
		case 3:
			return "사회과학";
		case 4:
			return "자연과학";
		case 5:
			return "기술과학";
		case 6:
			return "예술";
		case 7:
			return "언어";
		case 8:
			return "문학";
		case 9:
			return "역사";
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		return "[" + bookCode + "] " + title + " " + author + " " + publisher + " : " + (rentReturn? "대여 가능" : "대여 불가");
	}

	public void update(Book book) {
		this.title = book.title;
		this.author = book.author;
		this.publisher = book.publisher;
	}

	

}