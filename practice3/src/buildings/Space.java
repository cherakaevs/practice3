package buildings;

public interface Space extends Cloneable, Comparable<Space>{
    int getRoomsNum();
    int getSquare();
    void setRoomsNum(int num);
    void setSquare(int square);
    Object clone();
}
