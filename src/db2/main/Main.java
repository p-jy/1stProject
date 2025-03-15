package db2.main;

import java.sql.Connection;

import db2.config.DBConnection;

public class Main {
    public static void main(String[] args) {
    	//DB 연결 확인
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("DB 연결 성공!");
        } else {
            System.out.println("DB 연결 실패...");
        }
        DBConnection.closeConnection(conn);

        LibraryProgram program = new LibraryProgram();
        program.run();
    }
}