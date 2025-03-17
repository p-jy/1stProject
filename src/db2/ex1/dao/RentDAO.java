package db2.ex1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import db2.ex1.model.vo.Rent;
import db2.ex1.model.vo.Member;
import db2.ex1.model.vo.Book;

public interface RentDAO {

	// 단일 대여 이력 조회 (RE_NUM 등으로 식별)
    Rent selectRent(@Param("rent") Rent rent);

    // 대여 등록
    boolean insertRent(@Param("rent") Rent rent);

    // 대여 정보 수정 (상태 변경, 반납 처리 등)
    boolean updateRent(@Param("old") Rent oldRent, @Param("new") Rent newRent);

    // 대여 이력 삭제 (필요하다면)
    boolean deleteRent(@Param("rent") Rent rent);

    // 전체 대여 목록 조회
    List<Rent> selectRentList();

    // 특정 회원의 대여 목록 조회
    List<Rent> selectRentListByMember(@Param("member") Member member);

    // 특정 도서의 대여 목록 조회 (도서별 이력 확인용)
    List<Rent> selectRentListByBook(@Param("book") Book book);

    // 현재 대여 중인 도서 수 확인
    int countActiveRentsByMember(@Param("memberId") String memberId);

    // 반납 처리 시 사용할 수도 있는 메서드 (상태 변경 + 반납일 업데이트)
    boolean updateRentReturn(
        @Param("rentId") int rentId,
        @Param("returnDate") java.util.Date returnDate
    );
}
