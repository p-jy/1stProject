package Library;

import java.net.Socket;

public class Main {
	
	public static void main(String[] args) {
		
		String ip = "192.168.40.47";
		int port = 5001;
		
		try(Socket socket = new Socket(ip, port)) {
			System.out.println("[서버와 연결되었습니다.]");
			
			LibraryProgram libProgram = new LibraryProgram(socket);
			libProgram.run();
		} catch(Exception e) {
			System.out.println("[서버와 연결되지 않아 프로그램을 종료합니다.]");
			e.printStackTrace();
		}
		
		
	}
	
}
