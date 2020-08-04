// Program to run a thread using Thread class

package demo1;

// Thread Class extending Thread
class Runner extends Thread {
	// No-Arg Constructor
	Runner() {
		// Calling start() in constructor
		start();
	}
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
		Runner runner1 = new Runner();	
		Runner runner2 = new Runner();
	}

}
