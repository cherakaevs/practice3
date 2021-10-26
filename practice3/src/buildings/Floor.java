package buildings;

import java.util.Iterator;

public interface Floor extends Cloneable{
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
}