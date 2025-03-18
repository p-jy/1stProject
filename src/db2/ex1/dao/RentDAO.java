package db2.ex1.dao;

import org.apache.ibatis.annotations.Param;

import db2.ex1.model.vo.Rent;

public interface RentDAO {

	Rent selectRent(@Param("rent")Rent rent);

	boolean rentBook(@Param("rent")Rent rent);

	
}

