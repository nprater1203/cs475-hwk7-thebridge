import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs all threads
 */


public class BridgeRunner {
	//private static final String[] test = {"Hi","I","am","testing"};

	public static void main(String[] args) {

		// TODO - check command line inputs
		if(args.length != 2)
		{
			System.out.println("Usage: javac BridgeRunner <bridge limit> <num cars>");
		}
		else
			{
			int numOfThreads = Integer.parseInt(args[1]);
			int bridgeLimit = Integer.parseInt(args[0]);
			if(numOfThreads < 0 || bridgeLimit <= 0)
			{
				System.out.println("Error: bridge limit and/or num cars must be positive.");
				
			}
			else
			{
				System.out.println("Num of threads = " + numOfThreads);
			// for(int i = 0; i < 3; i++)
			// {
			// 	System.out.println(args[i] + "\n");
			// }
			// TODO - instantiate the bridge
				System.out.println("Instansiating Bridge");
				Bridge b = new OneLaneBridge();


			
				// TODO - allocate space for threads
				//Thread[] arrayThreads = new Thread[numOfThreads];
				//List<Thread> arrayThreads = new ArrayList<>();
				ArrayList<Thread> arrayThreads = new ArrayList<Thread>(numOfThreads);

				// TODO - start then join the threads

				// for(int i = 0; i < numOfThreads; i++)
				// {
				// 	System.out.println("Trying to get thread " + (i+1));
				// 	//arrayThreads[i]
				//     arrayThreads[i] = new Thread(new Car(i, b));
				// 	arrayThreads[i].start();
				// }
				int i = 0;
				for (Thread thread : arrayThreads)
				{
					System.out.println("Trying to get thread " + (i+1));
					thread = new Thread(new Car(i, b));
					thread.start();
					i++;
				}
				


				for (Thread thread : arrayThreads)
				{
					try 
					{
						thread.join();
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}

				System.out.println("All cars have crossed!!");
			}
		}
	}

}
