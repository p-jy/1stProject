package db.model.vo;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    // 회원 정보
    private String id;     // 아이디 (PK)
    private String pw;     // 비밀번호
    private String name;   // 이름
    private String num;    // 연락처
    private String authority; // USER or ADMIN
    private String canRent;    // 'Y' or 'N'
    
    public Member(String id, String pw, String name, String num) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.num = num;
        this.authority = "USER";
        this.canRent = "Y";
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Member other = (Member) obj;
        return Objects.equals(id, other.id);
    }

    public void update(Member member) {
        this.name = member.name;
        this.num = member.num;
        this.authority = member.authority;
        this.canRent = member.canRent;
    }

    @Override
    public String toString() {
        return "ID : " + id + " | 이름 : " + name + " | 권한 : " + authority + " | 대여 가능 : " + canRent;
    }


	
	
}