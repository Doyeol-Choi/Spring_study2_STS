package ex03.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEx {

	public static void main(String[] args) {
		
		Socket socket = null;
		
		BufferedReader in = null;
		BufferedWriter out = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			socket = new Socket("localhost", 9999);
			System.out.println("연결 성공");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				System.out.print("보내기>> ");
				String outputMessage = scan.nextLine();
				
				out.write(outputMessage + "\n");
				out.flush();
				
				if(outputMessage.equals("exit") || outputMessage.equals("")) {
					
					break;
				}
				
				String inputMessage = in.readLine();
				System.out.println("서버>> " + inputMessage);
				
				if(inputMessage.equals("exit") || inputMessage.equals("")) {
					System.out.println("상대방이 나갔습니다.");
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				scan.close();
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
