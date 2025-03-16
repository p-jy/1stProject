INSERT INTO member (ME_ID, ME_PW, ME_NAME, ME_NUM, ME_AUTHORITY, ME_CAN_RENT, ME_CAN_RENT_DATE, ME_NO_RENT, ME_DEL)
VALUES 	('admin', 'admin', '관리자', '1111-2222', 'ADMIN', 'Y', '2025-03-18', 0, 'N'),
        ('user01', 'password123', '홍길동', '1234-5678', 'USER', 'Y', '2025-03-17', 0, 'N'),
        ('sql에서 가입', 'password123', '홍길동', '1234-1234', 'USER', 'Y', '2025-03-17', 0, 'N');
        
SELECT * FROM member;

SHOW TABLES;
DROP DATABASE IF EXISTS library;