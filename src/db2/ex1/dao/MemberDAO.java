package db2.ex1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import db2.ex1.model.vo.Member;

public interface MemberDAO {

	Member selectMember(@Param("member")Member member);

	boolean insertMember(@Param("member")Member member);

	boolean updateMember(@Param("old")Member oldMem, @Param("new")Member newMem);

	boolean deleteMember(@Param("member")Member member);

	List<Member> selectMemberList();

	boolean deleteMemberByAdmin(@Param("member")Member member);

}