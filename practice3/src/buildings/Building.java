package buildings;

import java.util.Iterator;

public interface Building extends Cloneable, Iterable<Floor> {
    int getFloorsNum();
    int getSpacesNum();
    int getSumSquare();
    int getRoomsNum();
    Floor[] getFloorsArray();
    Floor getFloor(int num);
    void setFloor(int num, Floor floor);
    Space getSpace(int num);
    void setSpace(int num, Space space);
    void addSpace(int num, Space space);
    void removeSpace(int num);
    Space getBestSpace();
    Space[] getSortedSpaceArray();
    Object clone();
    Iterator<Floor> iterator();
}
