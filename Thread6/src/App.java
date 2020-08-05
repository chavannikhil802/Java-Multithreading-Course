// A CountDownLatch is used to make a thread wait for a specific period of time,
// (which is declared in the CountDownlatch() declaration).
// A CountDownLatch counts down from the time specified in the declaration, to 0.
// The thread waits until the count down becomes 0.
// Once the count down becomes 0, the threads can resume its execution.

// NOTE: HERE, i PRESENT IN FOR LOOP IN MAIN CLASS AND CountDownLatch time must be same
// If CountDownLatch time is more than i, then the program will never end.

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
	
	//  CountDownLatch declaration
	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch) {
		this.latch = latch;
	}
	
	public void run() {
		System.out.println("Started....");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Counting Down the value by 1
		latch.countDown();
		System.out.println("Latch : "+latch);
	}
}

public class App {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(6);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i=0;i<6;i++) {
			executor.submit(new Processor(latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed.");
	}

}
