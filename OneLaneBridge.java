/*
	Name: Nicholas Prater
	Course: CS 481 OS
	Professor: Dr. Chiu
	Date: 4/14/23
*/

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OneLaneBridge extends Bridge{

    private int bridgeLimit;
    private Object cv;

    // Constructor
    public OneLaneBridge(int bL)
    {
        bridgeLimit = bL;
        cv = new Object();
    }


    /*
     * Method to see if the car can cross the bridge
     * @param -- the car object
     */
    public void arrive(Car car) throws InterruptedException
    {
        synchronized(cv)
        {
            // Checks to see if the car can get on the bridge
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
            currentTime++;
        }

    }

    /*
     * Method to see if the car exit cross the bridge
     * @param -- the car object
     */
    public void exit(Car car) throws InterruptedException
    {
        synchronized(cv)
        {
            // Check to see if the car is at the from of the list
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
            cv.notifyAll();
        }
    }
}