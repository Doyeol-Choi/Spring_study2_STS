package ex03.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerEx {

	public static void main(String[] args) {
		// 자바프로그램간의 네트워크 연결을 위해서 사용하는 객체 : Socket
		ServerSocket server = null;
		
		Socket socket = null;
		
		BufferedReader in = null;
		BufferedWriter out = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			server = new ServerSocket(9999);	// 서버 객체 생성
			
			System.out.println("연결 대기중...");
			socket = server.accept();	// 응답받을 준비
			System.out.println("연결 되었습니다.");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				String inputMessage = in.readLine();	// 클라이언트로 부터 메시지 읽어드림
				System.out.println("클라이언트> " + inputMessage);
				
				if(inputMessage.equalsIgnoreCase("exit") || inputMessage.equals("")) {
					System.out.println("상대방이 나갔습니다.");
					break;
				}
				
				System.out.print("보내기 >> ");
				String outMessage = scan.nextLine();	// 보낼 메시지 작성
				
				out.write(outMessage + "\n");	// 클라이언트에게 메시지 보내기
				out.flush();
				
				if(outMessage.equalsIgnoreCase("exit") || outMessage.equals("")) {
					System.out.println("종료합니다.");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {	// 닫을 땐 생성의 역순이 좋다.
				scan.close();
				out.close();
				in.close();
				socket.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
