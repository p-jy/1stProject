package Library;

import java.util.ArrayList;
import java.util.List;

public class BookManager {
	//도서관리
	
	private List<Book> list;
	
	public BookManager(List<Book> list) {
		if(list == null) {
			this.list = new ArrayList<Book>();
		} else {
			this.list = list;
		}
	}
	
	public BookManager() {
		this.list = new ArrayList<Book>();
	}
	
	
	public boolean registBook(Book book) {
		
		if(book == null || list == null) {
			return false;
		}
		
		if(list.contains(book)) {
			return false;
		}
		
		return list.add(book);
	}
}
