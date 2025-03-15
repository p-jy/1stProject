package prac.main;

import java.util.List;

import prac.model.vo.StudentVO;
import prac.service.StudentService;
import prac.service.StudentServiceImp;

public class Main {

	static StudentService stduentService = new StudentServiceImp();
	
	public static void main(String[] args) {
		
		List<StudentVO> list = stduentService.getStudentList();
		System.out.println(list);
	}	

}