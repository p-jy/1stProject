insert into member(me_id, me_pw, me_name, me_num, me_authority)
value("admin", "admin", "admin", "010-0000-0000", "admin");

insert into category values
("000", "총류"),
("100", "철학"),
("200", "종교"),
("300", "사회과학"),
("400", "자연과학"),
("500", "기술과학"),
("600", "예술"),
("700", "언어"),
("800", "문학"),
("900", "역사");

insert into member(me_id, me_pw, me_name, me_num)
value("abc123", "abcd1234", "user1", "010-1111-1111");

DROP PROCEDURE IF EXISTS INSERT_BOOK;

DELIMITER //
CREATE PROCEDURE INSERT_BOOK(
	# 4개의 변수를 선언
	IN _CA_CODE CHAR(3),
    IN _TITLE VARCHAR(100),
    IN _AUTHOR VARCHAR(50),
    IN _PUBLISHER VARCHAR(20)
)
BEGIN
	DECLARE _COUNT INT;
    DECLARE _COUNT_STR CHAR(3);
    DECLARE _BO_CODE CHAR(6);
    
	# BO_CODE가 _CA_CODE로 시작하는 도서의 권수를 구하여 +1
	SET _COUNT = (SELECT COUNT(*) FROM BOOK WHERE BO_CODE LIKE CONCAT(_CA_CODE, '%')) + 1;
    # 도서의 권수를 이용하여 3자리 문자열을 만듦. 빈자리는 앞을 0으로 채움
    SET _COUNT_STR = LPAD(_COUNT, 3, "0");
    # 도서 코드는 _CA_CODE와 위에서 구한 3자리 문자열을 합하여 구함.
    SET _BO_CODE = CONCAT(_CA_CODE, _COUNT_STR);
    # 위에서 구한 값들을 이용하여 INSERT문 작성
    INSERT BOOK(BO_CODE, BO_TITLE, BO_AUTHOR, BO_PUBLISHER, BO_CA_CODE)
    VALUES(_BO_CODE, _TITLE, _AUTHOR, _PUBLISHER, _CA_CODE);
END //
DELIMITER ;

CALL INSERT_BOOK("000", "한국의 꽃살·기둥·누각", "임석재", "이화여자대학교출판부");
CALL INSERT_BOOK("100", "장자의 철학", "강신주", "태학사");
CALL INSERT_BOOK("200", "저항하는 그리스도인", "강성호", "복 있는사람");
CALL INSERT_BOOK("300", "수익 분배의 경제학", "오정석", "삼성경제연구소");
CALL INSERT_BOOK("400", "역발상의 과학 : 더하고 빼고 뒤집으면 답이 보인다", "김준래", "오엘북스");
CALL INSERT_BOOK("500", "이유석의 이유식", "이유석", "BR미디어");
CALL INSERT_BOOK("600", "프로처럼 사진 찍기", "조승범", "청년정신");
CALL INSERT_BOOK("700", "프랑스어동사활용사전", "서영하", "청록출판사");
CALL INSERT_BOOK("800", "위로가 필요한 시간", "김경집", "조화로운삶");
CALL INSERT_BOOK("900", "미술로 보는 우리 역사", "전국역사교사모임", "푸른나무");
CALL INSERT_BOOK("900", "유배인과 배수첩들의 뒤안길", "전웅", "소나기");
