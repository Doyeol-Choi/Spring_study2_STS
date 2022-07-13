package ex01;

public class Main01 {

	public static void main(String[] args) {

		for(int i=0; i<5; i++) {
			System.out.println("딩");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=0; i<5; i++) {
			System.out.println("동");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
