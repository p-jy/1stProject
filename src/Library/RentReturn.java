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
	
	public List<Book> print(String id) {
		List<Book> list = rentReturn.get(id);
		
		if(list == null) {
			System.out.println("[대여 중인 도서가 없습니다.]");
			return null;
		}
		
		for(int i = 0; i < list.size(); i++) {
			System.out.print(i+ 1 + ". ");
			System.out.println(list.get(i).getBookCode() + " " + list.get(i).getTitle());
		}
		return list;
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
		
		return false;
	}

	public boolean returnBook(Book book, String id) {
		
		List<Book> list = rentReturn.get(id);
		
		if(book == null || list == null) {
			System.out.println("[대여 중인 도서가 없습니다.]");
			return false;
		}
		
		list.remove(book);
		book.setRentReturn(true);
		
		return true;
	}

	
}
