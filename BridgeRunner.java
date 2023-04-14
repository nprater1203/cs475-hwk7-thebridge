/*
	Name: Nicholas Prater
	Course: CS 481 OS
	Professor: Dr. Chiu
	Date: 4/14/23
*/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BridgeRunner {

	public static void main(String[] args) {

		// TODO - check command line inputs
		if(args.length != 2)
		{
			System.out.println("Usage: javac BridgeRunner <bridge limit> <num cars>");
		}
		else
		{
			// Assumes that the two inputs are ints
			int numOfThreads = Integer.parseInt(args[1]);
			int bridgeLimit = Integer.parseInt(args[0]);
			if(numOfThreads <= 0 || bridgeLimit <= 0)
			{
				System.out.println("Error: bridge limit and/or num cars must be positive.");
				
			}
			else
			{
				// TODO - instantiate the bridge
				Bridge b = new OneLaneBridge(bridgeLimit);

				// TODO - allocate space for threads
				Thread[] arrayThreads = new Thread[numOfThreads];

				// TODO - start then join the threads
				for(int i = 0; i < numOfThreads; i++)
				{
				    arrayThreads[i] = new Thread(new Car(i, b));
					arrayThreads[i].start();
				}	

				for(int i = 0; i < numOfThreads; i++)
				{
					try 
					{
						arrayThreads[i].join();
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
