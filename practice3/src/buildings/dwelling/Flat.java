package buildings.dwelling;


import buildings.Space;
import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class Flat implements Space, Serializable, Cloneable{
    private int roomsNum;
    private int flatSquare;

    private static final int defaultRoomNum = 2;
    private static final int defaultFlatSquare = 50;

    public int getRoomsNum() {
        return roomsNum;
    }

    public int getSquare(){
        return flatSquare;
    }

    public void setRoomsNum(int num){
        if (num < 1 || num > 10){
            throw new InvalidRoomsCountException(num);
        }
        roomsNum = num;
    }

    public void setSquare(int square){
        if (square < 20 || square > 500){
            throw new InvalidSpaceAreaException(square);
        }
        flatSquare = square;
    }

    public Flat() {
        roomsNum = defaultRoomNum;
        flatSquare = defaultFlatSquare;
    }

    public Flat(int square){
        if (square < 20 || square > 500){
            throw new InvalidSpaceAreaException(square);
        }
        roomsNum = defaultRoomNum;
        flatSquare = square;
    }

    public Flat(int rNum, int square){
        if (square < 20 || square > 500){
            throw new InvalidSpaceAreaException(square);
        }
        if (rNum < 1 || rNum > 10){
            throw new InvalidRoomsCountException(rNum);
        }
        roomsNum = rNum;
        flatSquare = square;
    }

    public String toString(){
        String str = new String("Flat (" + roomsNum + ", " + flatSquare + ")");
        return str;
    }

    public boolean equals(Object object){
        if ((object instanceof Flat) && (((Flat) object).roomsNum == this.roomsNum) && (((Flat) object).flatSquare == this.flatSquare)) {
            return true;
        }
        return false;
    }

    public int hashCode(){
        int result;
        byte [] b = ByteBuffer.allocate(4).putInt(flatSquare).array();
        int x1 = b[0] + 256 * b[1];
        int x2 = b[2] + 256 * b[3];
        result = roomsNum ^ x1 ^ x2;
        return result;
    }

    public Object clone(){
        Object result = null;
        try{
            result = super.clone();
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int compareTo(Space o){
        if(flatSquare < o.getSquare())
            return -1;
        if(flatSquare > o.getSquare())
            return 1;
        return 0;
    }
}
