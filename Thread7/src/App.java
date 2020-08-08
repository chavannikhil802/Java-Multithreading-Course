/*	Producer - Consumer Problem
 *  The problem in which the producer produces the resource and the consumer consumes the resource.
 *  The Production and Consumption processes must be in sync, or else there will be over production or over consumption.
 *  In order to solve this issue, we have used  BlockingQueue DS, which is Thread Safe.
 *  For a Thread Safe DS, we do not have to explicitly handle Synchronization. 
 *  The DS handles it itself. 
 */

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	
	// Data Structure which is Thread Safe
	// Size of DS is 10
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

	public static void main(String[] args) {
		// Producer Thread calling producer()
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				producer();
			}
		});
		// Consumer Thread calling consumer()
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				consumer();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Method which produces the Resources
	public static void producer() {
		Random random = new Random();
		while(true) {
			// Stores random numbers upto 100 in queue
			try {
				int value = random.nextInt(100);
				queue.put(value);
				System.out.println("Value added : "+value);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// Method which consumes the Resources
	public static void consumer() {
		Random random = new Random();
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(random.nextInt(10) == 0) {
				Integer value = 0;
				try {
					value = queue.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Taken Value: "+value+" and Queue Size: "+queue.size());
			}
		}
	}

}
