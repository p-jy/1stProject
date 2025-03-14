package db.dao;

import org.apache.ibatis.annotations.Param;

import db.model.vo.Member;

public interface MemberDAO {

	Member selectMember(@Param("member")Member member);

	boolean insertStudent(@Param("member")Member member);

	boolean updateMember(@Param("member")Member newMember, @Param("member")Member newMember2);

	boolean deleteMember(@Param("member")Member member);

}
