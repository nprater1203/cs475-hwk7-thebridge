/*
	Name: Nicholas Prater
	Course: CS 481 OS
	Professor: Dr. Chiu
	Date: 4/14/23
*/

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Runs all threads
 */


public class OneLaneBridge extends Bridge{

    private int bridgeLimit;
    private Object cv;
    //private int  = 0;

    public OneLaneBridge(int bL)
    {
        bridgeLimit = bL;
        cv = new Object();
    }

    public void arrive(Car car) throws InterruptedException
    {
        synchronized(cv)
        {

            //System.out.println("Bridge limit = " + bridgeLimit);
            while(bridge.size() >= bridgeLimit || car.getDirection() != direction)
            {
                try
                {
                    cv.wait();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                if(bridge.size() == 0)
                {
                    direction = car.getDirection();
                }
            }
            car.setEntryTime(currentTime);
            bridge.add(car);
            System.out.println("Bridge (dir="+ car.getDirection() + "): " + bridge.toString());
            
            // for (Car cars : bridge) 
            // {
            //     System.out.print(cars.toString() + ", ");
            // }
            //System.out.println("]");
            currentTime++;
        }

    }

    public void exit(Car car) throws InterruptedException
    {
        synchronized(cv)
        {
            while(bridge.get(0) != car)
            {
                try
                {
                    cv.wait();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
                bridge.remove(car);
                System.out.println("Bridge (dir="+ car.getDirection() + "): " + bridge.toString());
                // for (Car cars : bridge) 
                // {
                //     System.out.print(cars.toString() + ", ");
                // }
                // System.out.println("]");
 
                cv.notifyAll();
            
        }

    }

}