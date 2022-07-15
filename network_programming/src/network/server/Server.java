package network.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) {

		ServerSocket server = null;		
		Socket socket = null;	
		BufferedReader reader = null;
		BufferedWriter writer = null;
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		
		try {
			server = new ServerSocket(5702);	
			
			System.out.println("[연결 대기중...]");
			socket = server.accept();	
			System.out.println("[연결되었습니다.]");
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(run) {
				String readMessage = reader.readLine();	
				System.out.println("[클라이언트] : " + readMessage);
				
				// 종료 조건
				if(readMessage.equalsIgnoreCase("end")) {
					System.out.println("[클라이언트가 나갔습니다.]");
					run = false;
					return;
				}
				
				System.out.print("[보내기] : ");
				String writeMessage = sc.nextLine();	
				
				writer.write(writeMessage + "\n");
				writer.flush();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {	
				sc.close();
				writer.close();
				reader.close();
				socket.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
