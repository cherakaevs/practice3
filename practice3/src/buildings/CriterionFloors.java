package buildings;

import java.util.Comparator;

public class CriterionFloors implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2){
        if(o1.getSquare() < o2.getSquare())
            return 1;
        if (o1.getSquare() > o2.getSquare())
            return -1;
        return 0;
    }
}
