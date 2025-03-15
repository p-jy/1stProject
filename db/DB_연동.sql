CREATE DATABASE IF NOT EXISTS library;
USE library;

CREATE TABLE IF NOT EXISTS book (
    bookCode VARCHAR(10) PRIMARY KEY,  -- 도서 코드
    title VARCHAR(100) NOT NULL,       -- 도서명
    author VARCHAR(50) NOT NULL,       -- 작가명
    publisher VARCHAR(50) NOT NULL,    -- 출판사
    rentReturn BOOLEAN DEFAULT TRUE    -- 대여 가능 여부
);

CREATE TABLE IF NOT EXISTS member (
    memberId VARCHAR(20) PRIMARY KEY,  -- 회원 ID
    name VARCHAR(50) NOT NULL,         -- 회원 이름
    phone VARCHAR(15) NOT NULL,        -- 연락처
    password VARCHAR(255) NOT NULL     -- 비밀번호
);

SELECT * FROM book;