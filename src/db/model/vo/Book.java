package db.model.vo;

public class Book {
    private String boCode;
    private String boTitle;
    private String boAuthor;
    private String boPublisher;
    private String boDel;
    private String boRent;
    private int boCaNum;
    
    // 기본 생성자 추가
    public Book(String boCode, String boTitle, String boAuthor, String boPublisher, String boDel, String boRent, int boCaNum) {
        this.boCode = boCode;
        this.boTitle = boTitle;
        this.boAuthor = boAuthor;
        this.boPublisher = boPublisher;
        this.boDel = boDel;
        this.boRent = boRent;
        this.boCaNum = boCaNum;
    }

    public String getBoRent() {
        return (boRent != null) ? boRent : "N";
    }

    
}
