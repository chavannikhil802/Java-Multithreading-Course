import java.util.Scanner;

public class Processor {
	public void produce() throws InterruptedException {
		// Synchronize on the Intrinsic Lock of the Processor (this)
		synchronized(this) {
			System.out.println("Producer Thread Running.....");
			// wait() relinquishes the lock
			// the control moves to another thread calling consume() method
			wait();
			System.out.println("Resumed.....");
		}
	}
	
	public void consume() throws InterruptedException {
		Thread.sleep(200);
		
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized(this) {
			System.out.println("Waiting for Return key.....");
			scanner.nextLine();
			System.out.println("Return key pressed.....");
			// After pressing return key, notify() is executed
			// here, notify() will notify the producer thread to wake up
			// and be ready to occupy the lock
			// If notify is not called, then consume() will keep on running
			// and the control will never move back to produce()
			notify();
			// NOTE: notify() does not relinquishes the lock
			Thread.sleep(5000);
		}
	}
}
