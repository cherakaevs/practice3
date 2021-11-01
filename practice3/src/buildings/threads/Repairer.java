package buildings.threads;

import buildings.Floor;

public class Repairer extends Thread{
    private Floor floor;

    public Repairer(Floor obj){
        floor = obj;
    }

    public void run(){
        for (int i = 0; i < floor.getSpacesNum(); i++){
            System.out.printf("Repairing space number %d with total area %d square meters\n", i, floor.getSpace(i).getSquare());
        }
        System.out.println("End of repairing");
    }
}
