package db2.ex1.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.NonNull;

public class Rent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9055960272294860934L;
	//String : id
	
	private int re_num; //re_num
	private String id; //re_me_id
	private String code; //re_bo_code
	
	private Map<String, List<Book>> rentReturn;
	
	public Rent(Map<String, List<Book>> rentReturn) {
		this.rentReturn = rentReturn;
	}
	
	public List<Book> print(String id) {
		List<Book> list = rentReturn.get(id);
		
		if(list == null || list.isEmpty()) {
			System.out.println("[대여 중인 도서가 없습니다.]");
			return null;
		}
		
		for(int i = 0; i < list.size(); i++) {
			System.out.print(i+ 1 + ". ");
			System.out.println(list.get(i).getCode() + " " + list.get(i).getTitle());
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
		 if(list.size() >= 3) {
	            System.out.println("대여 가능 권수를 초과하였습니다.");
	            return false;
	        }
		
		for(Book b : list) {
			if(b.equals(book)) {
				 System.out.println("이미 대여한 도서입니다.");
				return false;
			}
		}
		
		list.add(book);
		
		return true;
	}

	public boolean returnBook(Book book, String id) {
		
		List<Book> list = rentReturn.get(id);
		
		if(book == null || list == null || list.isEmpty()) {
			System.out.println("[대여 중인 도서가 없습니다.]");
			return false;
		}
		
		if(list.remove(book)) {
//		book.setRentReturn(true);
		
		return true;
		}
		else {
            System.out.println("대여 기록을 찾을 수 없습니다.");
            return false;
		}

	}
	public Map<String, List<Book>> getRentReturnMap() {
	    return rentReturn;
	}
}