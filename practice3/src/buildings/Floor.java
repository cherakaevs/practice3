package buildings;

import java.util.Iterator;

public interface Floor extends Cloneable, Iterable<Space> {
    int getSpacesNum();
    int getSumSquare();
    int getRoomsNum();
    Space[] getSpacesArray();
    Space getSpace(int num);
    void setSpace(int num, Space newSpace);
    void addSpace(int num, Space newSpace);
    void removeSpace(int num);
    Space getBestSpace();
    Object clone();
    Iterator<Space> iterator();
}