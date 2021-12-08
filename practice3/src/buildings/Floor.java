package buildings;

public interface Floor extends Cloneable, Iterable<Space>, Comparable<Floor> {
    int getSpacesNum();
    double getSquare();
    int getRoomsNum();
    Space[] getSpacesArray();
    Space getSpace(int num);
    void setSpace(int num, Space newSpace);
    void addSpace(int num, Space newSpace);
    void removeSpace(int num);
    Space getBestSpace();
    Object clone();
}