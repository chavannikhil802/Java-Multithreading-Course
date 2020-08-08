// Deadlock occurs when the threads are not locked in the same order.
// In order to lock the threads in the same order and prevent Deadlock,
// we have used a method named as acquireLocks()
// which will acquire the locks no matter what is the sequence of locking the threads

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	// Method used to acquire the locks and unlock them.
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
		while(true) {
			// Acquire the locks
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try {
//				If the resource is not held by any other thread, then call to tryLock() returns true and the hold count is incremented by one. 
//				If the resource is not free then the method returns false and the thread is not blocked but it exits.
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}
			finally {
				// if both the threads are locked
				if(gotFirstLock && gotSecondLock) {
					return;
				}
				// if only one thread is locked, unlock it
				if(gotFirstLock) {
					firstLock.unlock();
				}
				// if only one thread is locked, unlock it
				if(gotSecondLock) {
					secondLock.unlock();
				}
			}
			// locks not acquired
			Thread.sleep(1);
		}
	}
	
	public void firstThread() throws InterruptedException {
		Random random = new Random();
		for(int i=0;i<10000;i++) {
			// Calling acquireLocks()
			acquireLocks(lock1, lock2);
			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			}
			finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void secondThread() throws InterruptedException {
		Random random = new Random();
		for(int i=0;i<10000;i++) {
			// Calling acquireLocks()
			acquireLocks(lock2, lock1);
			try {
				Account.transfer(acc2, acc1, random.nextInt(100));
			}
			finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void finished() {
	System.out.println("Account 1 Balance : "+acc1.getBalance());
	System.out.println("Account 2 Balance : "+acc2.getBalance());
	System.out.println("Total Balance : "+(acc1.getBalance()+acc2.getBalance()));
	}
}
