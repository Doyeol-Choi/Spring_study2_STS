package ex01;

public class Main02 {

	public static void main(String[] args) {
		
		Dong01 d = new Dong01();
		
		Thread thread = new Thread(d);
		thread.start();	// 새로운 스레드가 생성된 후 스레드 동작이 시작됨
		
		for(int i=0; i<5; i++) {
			System.out.println("딩");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
