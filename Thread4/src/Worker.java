import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// INTRINSIC LOCK OF THE WORKER OBJECT WILL BE ACCESSED BY THE THREADS
public class Worker {
	
	private Random random = new Random();
	
	// THE OBJECTS WILL BE USED AS LOCK TO THE METHOD LOGIC
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	// DUE TO THE IMPLEMENTATION OF THESE OBJECT LOCKS,
	// BOTH THE THREADS CAN ACCESS THE WORKER CLASS AT THE SAME TIME,
	// BUT ONLY ONE THREAD CAN ACCESS ONE METHOD AT A TIME.
	// HOWEVER, 2 DIFFERENT THREADS CAN ACCESS TWO DIFFERENT METHODS AT THE SAME TIME
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	
	// WHEN T1 OR T2 CALL THE SYNCHRONIZED METHOD,
	// THEY WILL ACQUIRE THE INTRINSIC LOCK lock1.
	// IT T1 ACQUIRES THE lock1, T2 HAVE TO WAIT TILL THE LOCK IS RELEASED
	// AND VICE VERSA
	public void stageOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	// WHEN T1 OR T2 CALL THE SYNCHRONIZED METHOD,
	// THEY WILL ACQUIRE THE INTRINSIC LOCK lock2.
	// IT T1 ACQUIRES THE lock2, T2 HAVE TO WAIT TILL THE LOCK IS RELEASED
	// AND VICE VERSA
	public void stageTwo() {
		synchronized (lock2) {
	 		try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}
	
	public void process() {
		for(int i=0;i<1000;i++) {
			stageOne();
			stageTwo();
		}
	}
	
	public void main() {
		System.out.println("Starting........");
		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				process();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				process();
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
		
		long end = System.currentTimeMillis();
		System.out.println("Time Taken: "+(end-start));
		System.out.println("List 1 : "+list1.size());
		System.out.println("List 2 : "+list2.size());
	}
}
