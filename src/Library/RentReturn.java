package Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.NonNull;

public class RentReturn {
	//String : id
	private Map<String, List<Book>> rentReturn;
	
	public RentReturn(Map<String, List<Book>> rentReturn) {
		this.rentReturn = rentReturn;
	}
	
	public void print(String id) {
		List<Book> list = rentReturn.get(id);
		
		if(list == null) {
			System.out.println("[대여 중인 도서가 없습니다.]");
			return;
		}
		
		list.stream().forEach(b-> System.out.println(b.getBookCode()));
	}

	public boolean rentBook(String id, Book book) {	
		List<Book> list = rentReturn.get(id);
		
		if(list == null) {
			list = new ArrayList<Book>();
			list.add(book);
			
			rentReturn.put(id, list);
			return true;
		}
		
		for(Book b : list) {
			if(b.equals(book)) {
				return false;
			}
		}
		
		list.add(book);
		book.setRentReturn(false);
		
		return false;
	}
}
