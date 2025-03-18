package db2.ex1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import db2.ex1.model.vo.Rent;
import lombok.NonNull;

public interface RentDAO {

	Rent selectRent(@Param("rent")Rent rent);

	boolean rentBook(@Param("rent")Rent rent);

	boolean returnBook(@Param("id")String id, @Param("code")String code);

	List<Rent> selectRentList(@Param("id")String id);
}

