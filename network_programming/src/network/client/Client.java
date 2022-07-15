package network.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		
		Socket socket = null;		
		BufferedWriter writer = null;
		BufferedReader reader = null;
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		
		try {
			socket = new Socket("localhost", 5702);
			System.out.println("[서버 접속 완료]");
			
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
			while(run) {
				System.out.print("[보내기] : ");
				String writeMessage = sc.nextLine();
				
				writer.write(writeMessage + "\n");
				writer.flush();
				
				// 종료 조건
				if(writeMessage.equalsIgnoreCase("end")) {
					System.out.println("[종료합니다.]");
					run = false;
					return;
				}
				
				String readMessage = reader.readLine();
				System.out.println("[서버] : " + readMessage);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				sc.close();
				reader.close();
				writer.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
