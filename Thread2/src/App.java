import java.util.Scanner;

// PROGRAM TO DISPLAY SYNCHRONIZATION

class Processor extends Thread {
	
	// WE USE VOLATILE SO THAT THE THREAD PREVENTS CACHING
	private volatile boolean running = true;
	
	public void run() {
		while(running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void shutdown() {
		running = false;
	}
}

public class App {

	public static void main(String[] args) {
		
		Processor proc1 = new Processor();
		proc1.start();
		
		// CODE TO STOP THE THREAD EXECUTION WHEN THE USER PRESSES ENTER KEY
		System.out.println("Press return to Stop!!!!");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		proc1.shutdown();
		scanner.close();
	}

}
