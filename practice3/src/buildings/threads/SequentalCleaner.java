package buildings.threads;

import buildings.Floor;
import buildings.Semaphore;

public class SequentalCleaner extends Thread implements Runnable{
    private Floor floor;
    private Semaphore semaphore;

    public SequentalCleaner(Floor obj, Semaphore sem){
        floor = obj;
        semaphore = sem;
    }

    @Override
    public void run() {
        try{
            if(semaphore.isEmpty() != false)
                Thread.sleep(1000);
            semaphore.acquire();
            for (int i = 0; i < floor.getSpacesNum(); i++){
                System.out.printf("Cleaning room number %d with total area %d square meters\n", i, floor.getSpace(i).getSquare());
                Thread.sleep(1000);
            }
            System.out.println("End of cleaning");
            semaphore.release();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
