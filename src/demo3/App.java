// Program to run thread using an Anonymous Class

package demo3;

public class App {
	public static void main(String[] args) {
		// New Anonymous Thread class
		Thread t1 = new Thread() {
			@Override
			// Implementing run()
			public void run() {
				for(int i=0;i<10;i++) {
					System.out.println("Hello t1 "+i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		// Starting the thread
		t1.start();
		
		// New Anonymous Thread Class
		// Here, we have passed the name of the class as Runnable in Constructor
		Thread t2 = new Thread(new Runnable() {
			@Override
			// Implementing run()
			public void run() {
				for(int i=0;i<10;i++) {
					System.out.println("Hello t2"+i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		// Starting the thread
		t2.start();
	}

}
