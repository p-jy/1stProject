package prac.dao;

import java.util.List;

import prac.model.vo.StudentVO;

public interface StudentDAO {

	List<StudentVO> selectStudentList();

}