package prac.dao;

import java.util.List;

import prac.model.vo.Student;

public interface StudentDAO {

	List<Student> selectStudentList();

}