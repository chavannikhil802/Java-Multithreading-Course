public class App {

	public static void main(String[] args) throws Exception {
		
		Runner runner = new Runner();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					runner.firstThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					runner.secondThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		runner.finished();
	}

}


//OUTPUT:-
//
//Thread 1 waiting.....
//Thread 2 : Press Return Key.....
//
//Thread 2 : Return Key Pressed!!!!!
//Thread 1 Woken Up!!!!!
//Count is : 20000