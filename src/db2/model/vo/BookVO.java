package db2.model.vo;

import lombok.Data;

@Data
public class BookVO {
    private String bo_code;        // 도서 코드
    private String bo_title;       // 도서 제목
    private String bo_author;      // 도서 작가명
    private String bo_publisher;   // 도서 출판사
    private boolean bo_del;        // 대여 여부
    private boolean bo_rent;       // 대여 가능 여부
    private String bo_ca_num;      // 카테고리 번호

    // 필요한 생성자나 메서드 추가 가능
    public BookVO(String bo_code, String bo_title, String bo_author, String bo_publisher, boolean bo_del, boolean bo_rent, String bo_ca_num) {
        this.bo_code = bo_code;
        this.bo_title = bo_title;
        this.bo_author = bo_author;
        this.bo_publisher = bo_publisher;
        this.bo_del = bo_del;
        this.bo_rent = bo_rent;
        this.bo_ca_num = bo_ca_num;
    }
}
