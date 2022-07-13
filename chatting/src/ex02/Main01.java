package ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main01 {

	public static void main(String[] args) throws Exception {
		// 이미지 파일 읽어오기
		File f = new File("C:\\Study\\study.jpg");	// 하드디스크에 저장된 파일에 접근 사용하기 위한 객체
		
		InputStream is = new FileInputStream(f);	// 파일에서 데이터를 읽어올 스트림(빨대)
		
		OutputStream os = new FileOutputStream("c:\\Study\\study2.jpg");
		
		byte[] readByte = new byte[100];	// 100칸짜리 수레
		int readChkNum;
		
//		String image = "";
		while((readChkNum=is.read(readByte))!=-1) {
//			image += new String(readByte);
			os.write(readByte);	// 해당 위치에 기록
		}
		
//		System.out.println(image);	// 이미지 파일을 잘게 잘라 String으로 출력했기 때문에 알아보긴 힘들다.
		os.flush();
		os.close();
		is.close();
		System.out.println("파일 복사가 완료되었습니다.");
		
		Scanner scan = new Scanner(System.in);	

		scan.nextLine();
	}

}
