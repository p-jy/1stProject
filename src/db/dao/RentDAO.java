package db.dao;

import org.apache.ibatis.annotations.Param;

import db.model.vo.Rent;

public interface RentDAO {

	Rent selectRent(@Param("rent")Rent rent);

	boolean rentBook(@Param("rent")Rent rent);

	boolean returnBook(@Param("rent")String id, @Param("rent")String code);

}

