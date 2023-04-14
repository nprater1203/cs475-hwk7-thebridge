import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Runs all threads
 */


public class OneLaneBridge extends Bridge{

    private Object cv = new Object();
    //private int  = 0;

    public void arrive(Car car) throws InterruptedException
    {
        synchronized(cv)
        {
            if(bridge.size() == 0)
            {
                direction = car.getDirection();
            }
            //System.out.println("Bridge limit = " + bridgeLimit);
            while(bridge.size() >= 3 || car.getDirection() != direction)
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
            car.setEntryTime(currentTime);
            bridge.add(car);
            System.out.print("Bridge (dir="+ car.getDirection() + "): [");
            for (Car cars : bridge) 
            {
                System.out.print(cars.toString() + ", ");
            }
            System.out.println("]");
            currentTime++;
        }

    }

    public void exit(Car car) throws InterruptedException
    {
        synchronized(cv)
        {
            if(bridge.get(0) == car)
            {
                bridge.remove(car);
                System.out.print("Bridge (dir="+ car.getDirection() + "): [");
                for (Car cars : bridge) 
                {
                    System.out.print(cars.toString() + ", ");
                }
                System.out.println("]");
                if(bridge.size() == 0)
                {
                    if(direction == car.getDirection())
                    {
                        if(car.getDirection() == false)
                        {
                            direction = true;
                        }
                        else
                        {
                            direction = false;
                        }
                    }
                    else
                    {
                        direction = car.getDirection();
                    }
                }
                cv.notifyAll();
            }
        }

    }

}