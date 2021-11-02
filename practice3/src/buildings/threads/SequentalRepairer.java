package buildings.threads;

import buildings.Floor;
import buildings.Semaphore;

public class SequentalRepairer extends Thread implements Runnable{
    private Floor floor;
    private Semaphore semaphore;

    public SequentalRepairer(Floor obj, Semaphore sem){
        floor = obj;
        semaphore = sem;
    }

    @Override
    public void run() {
        try{
            semaphore.acquire();
            for (int i = 0; i < floor.getSpacesNum(); i++){
                System.out.printf("Repairing space number %d with total area %d square meters\n", i, floor.getSpace(i).getSquare());
                Thread.sleep(1000);
            }
            System.out.println("End of repairing");
            semaphore.release();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
