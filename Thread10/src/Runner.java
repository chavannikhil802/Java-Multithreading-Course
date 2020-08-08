import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private void increment() {
		for(int i=0;i<10000;i++) {
			count++;
		}
	}

	public void firstThread() throws InterruptedException {
		lock.lock();
		System.out.println("Thread 1 waiting.....");
		
		// await() will make the thread wait and release the lock
		cond.await();
		System.out.println("Thread 1 Woken Up!!!!!");
		
		// Here, try-finally block is used
		// so that when the thread throws an excepetion
		// it will atleast release the lock
		try {
			// Once entered, will run for 10000 times
			increment();
		}
		finally {
			lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException {
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("Thread 2 : Press Return Key.....");
		new Scanner(System.in).nextLine();
		System.out.println("Thread 2 : Return Key Pressed!!!!!");
		
		// signal() notifies the thread to wake up and be ready to acquire the lock
		cond.signal();
		// Here, try-finally block is used
		// so that when the thread throws an excepetion
		// it will atleast release the lock
		try {
			// Once entered, will run for 10000 times
			increment();
		}
		finally {
			lock.unlock();
		}
	}
	
	public void finished() {
	System.out.println("Count is : "+count);	
	}
}
