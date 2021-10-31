package buildings;

import java.util.Comparator;

public class CriterionSpaces implements Comparator<Space> {
    @Override
    public int compare(Space o1, Space o2){
        if(o1.getSquare() < o2.getSquare())
            return 1;
        if(o1.getSquare() > o2.getSquare())
            return -1;
        return 0;
    }
}
