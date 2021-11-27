package buildings;

public interface Space extends Cloneable, Comparable<Space>{
    int getRoomsNum();
    double getSquare();
    void setRoomsNum(int num);
    void setSquare(double square);
    Object clone();
}
