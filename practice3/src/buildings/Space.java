package buildings;

public interface Space extends Cloneable{
    int getRoomsNum();
    int getSquare();
    void setRoomsNum(int num);
    void setSquare(int square);
    Object clone();
}
