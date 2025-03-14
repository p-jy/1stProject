package db2.ex1.dao;

import org.apache.ibatis.annotations.Param;

import db2.ex1.model.vo.Member;

public interface MemberDAO {

	Member selectMember(@Param("member")Member member);

	boolean insertMember(@Param("member")Member member);

	

}

