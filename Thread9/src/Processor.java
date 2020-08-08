import java.util.LinkedList;
import java.util.Random;

public class Processor {
	
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	// Add data to the linkedlist
	public void produce() throws InterruptedException {
		int value = 0;
		while(true) {
			synchronized(lock) {
				// If there are 10 elements in the list, then wait()
				while(list.size() == LIMIT) {
					lock.wait();
					// control goes to consume() thread to remove elements
				}
				list.add(value++);
				// notify() if the consumer thread is waiting 
				lock.notify();
			}
		}
		
	}
	// remove data from the linkedlist
	public void consume() throws InterruptedException {
		Random random = new Random();
		while(true) {
			synchronized(lock) {
				// If there are 0 elements in the list, then wait()
				while(list.size() == 0) {
					lock.wait();
					// control goes back to produce() thread to add elements
				}
				System.out.print("List Size : "+list.size());
				int value = list.removeFirst();
				System.out.println(" and Value: "+value);
				// notify(0 if the producer thread is waiting
				lock.notify();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}
}
