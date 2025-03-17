package db3.model.vo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    // 대여 번호 (PK)
    private int reNum;
    
    // 대여 시작일 (예: 현재 시각)
    private LocalDateTime reRentDate;
    
    // 반납 예정일 (대여 시작일 기준 7일 후)
    private LocalDateTime reDueDate;
    
    // 실제 반납일 (반납 시 업데이트, 아직 반납하지 않았다면 null)
    private LocalDateTime reReturnDate;
    
    // 대여 상태 ("대여" 또는 "반납")
    private String reState;
    
    // 대여한 회원의 아이디 (MEMBER 테이블의 ME_ID와 연결)
    private String reMeId;
    
    // 대여한 도서의 코드 (BOOK 테이블의 BO_CODE와 연결)
    private String reBoCode;
}
