package ex01;

public class Dong01 implements Runnable {

	@Override
	public void run() {	// 새로운 스레드의 시작 => 메인과는 별도로 동작하는 스레드
		
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
