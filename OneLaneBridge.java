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
            while(bridge.size() >= 3 && car.getDirection() != direction)
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
            for (Car cars : bridge) 
            {
                System.out.print(cars.toString() + ", ");
            }
            System.out.println("\n");
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
                for (Car cars : bridge) 
                {
                    System.out.print(cars.toString() + ", ");
                }
                System.out.println("\n");
                cv.notifyAll();
            }
        }

    }

}