package buildChallenge;


import edu.wpi.first.wpilibj.Spark;



//You need to remember this "implements Runnable" part or the code won't work!
//This is going to force you to add a run() method because it is an interface. Don't worry too much about that; it's a concept we'll cover later.
public class Wheel implements Runnable {
	/*
	 * Create sparks here for however many wheels your teams is using (should be at
	 * most 2) Remember that this is private, meaning that it is particular to this
	 * class, aka Wheel.java
	 */
	private Spark myWheel;
	private Spark myWheel2; 

	
	
	public Wheel(int channelOne, int channelTwo) {
		myWheel = new Spark(channelOne);
		myWheel2 = new Spark(channelTwo);
		xbox = new XboxController(0);
		startWheel();
	}

	/*
	 * Generally, running would be instantiated up top by the Sparks, but it is here
	 * for convenience.
	 */
	private boolean running = false;

	public void startWheel() {
		// Synchronized ensures that, in a multi-threaded project, two threads can't
		// access the same resource at the same time, preventing errors and other issues
		synchronized (this) {
			/*
			 * If running is already true, we do not want to create a second Thread, so we
			 * simply return out of the method and do nothing.
			 * 
			 * Remember, you may see this elsewhere written as:
			 * 
			 * if(running)
			 * 
			 * return;
			 * 
			 * This does the same thing! After an if statement, if there are no braces (aka
			 * { and } ) then it will consider the next line to be part of the conditional.
			 * Be careful about this! Don't accidentally exclude a line of the code from the
			 * conditional because there are no braces; add them!
			 */
			if (running) {
				return;
			}

			running = true;
			/*
			 * This creates a new Thread.
			 * 
			 * The first parameter, "this" refers to this class: Wheel.java
			 * 
			 * The second parameter, "wheelThread", is a String which is used as the
			 * thread's name
			 */
			new Thread(this, "wheelThread").start();
		}
	}

	/**
	 * This method is ran when the Thread is started. Essentially, once line 108 is
	 * ran it goes here
	 */
	@Override
	public void run() {
		while (running) {
			/*
			 * This is where your code to use the Sparks goes!
			 */

			// As an example, this sets the motors to run at half power forever.
			myWheel.set(0.9);
			myWheel2.set(0.9);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			
			

			/*
			 * Every thread should have a Thread.sleep which makes it wait for some
			 * specified amount of milliseconds. Please note this is NOT seconds so please
			 * don't write Thread.sleep(1); All Thread.sleep calls have to be surrounded by
			 * a try/catch, which essentially acts as a failsafe if something breaks
			 */
			
			stop();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public boolean getRunning() {
		return running;
	}
	
	public void stop() {
		myWheel.set(0);
		myWheel2.set(0);
	}
}

	