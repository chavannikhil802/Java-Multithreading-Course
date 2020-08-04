// Program to run a Tread using Runnable Interface

package demo2;

// Thread class implementing Runnable
class Runner implements Runnable {
	@Override
	// run() is executed after start() is called
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println("Hello "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
public class App {
	public static void main(String[] args) {
		// 2 different objects are created of Thread Class
		Thread t1 = new Thread(new Runner());
		Thread t2 = new Thread(new Runner());
		// Calling start() for t1 and t2
		t1.start();
		t2.start();
	}
}
