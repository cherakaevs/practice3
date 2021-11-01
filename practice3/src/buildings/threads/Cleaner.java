package buildings.threads;

import buildings.Floor;

public class Cleaner extends Thread{
    private Floor floor;

    public Cleaner(Floor obj){
        floor = obj;
    }

    public void run(){
        for (int i = 0; i < floor.getSpacesNum(); i++){
            System.out.printf("Cleaning room number %d with total area %d square meters\n", i, floor.getSpace(i).getSquare());
        }
        System.out.println("End of cleaning");
    }
}
