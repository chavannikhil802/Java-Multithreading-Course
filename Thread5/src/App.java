import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
	
	private int id;
	
	public Processor(int id) {
		this.id = id;
	}
	
	public void run() {
		System.out.println("Starting : "+id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finishing : "+id);
	}
}


public class App {

	public static void main(String[] args) {
		// java.util.concurrent.Executorservice interface is used to create a Thread Pool.
		// A THreadPool consists of n number of threads.
		// The threads in the ThreadPool performs task with minimum ideal time.
		// It a thread in the ThreadPool finishes executing its task,
		// then another task is assigned to it immediately.
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for(int i=0;i<5;i++) {
			executor.submit(new Processor(i));
		}
		executor.shutdown();
		
		System.out.println("All Tasks Submitted.");
		
		// IN CASE THE TASKS ARE NOT FINISHED IN TIME,
		// THEN THE FOLLOWING CODE WILL WAIT FOR 1 DAY TO FINISH THE TASK.
		// IF THE TASK IS NOT FINISHED, THE THE THREAD WILL BE TERMINATED.
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All taks completed");
	}

}
