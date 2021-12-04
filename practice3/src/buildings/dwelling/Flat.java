package buildings.dwelling;


import buildings.Space;
import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class Flat implements Space, Serializable, Cloneable{
    private int roomsNum;
    private double flatSquare;

    private static final int defaultRoomNum = 2;
    private static final double defaultFlatSquare = 50.0;

    public int getRoomsNum() {
        return roomsNum;
    }

    public double getSquare(){
        return flatSquare;
    }

    public void setRoomsNum(int num){
        if (num < 1 || num > 10){
            throw new InvalidRoomsCountException(num);
        }
        roomsNum = num;
    }

    public void setSquare(double square){
        if (square < 20 || square > 500){
            throw new InvalidSpaceAreaException(square);
        }
        flatSquare = square;
    }

    public Flat() {
        roomsNum = defaultRoomNum;
        flatSquare = defaultFlatSquare;
    }

    public Flat(double square){
        if (square < 20 || square > 500){
            throw new InvalidSpaceAreaException(square);
        }
        roomsNum = defaultRoomNum;
        flatSquare = square;
    }

    public Flat(int rNum, double square){
        if (square < 20 || square > 500){
            throw new InvalidSpaceAreaException(square);
        }
        if (rNum < 1 || rNum > 10){
            throw new InvalidRoomsCountException(rNum);
        }
        roomsNum = rNum;
        flatSquare = square;
    }

    @Override
    public String toString(){
        String str = new String("Flat (" + roomsNum + ", " + flatSquare + ")");
        return str;
    }

    @Override
    public boolean equals(Object object){
        if ((object instanceof Flat) && (((Flat) object).roomsNum == this.roomsNum) && (((Flat) object).flatSquare == this.flatSquare)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result;
        byte [] b = ByteBuffer.allocate(8).putDouble(flatSquare).array();
        int x1 = b[0] + 256 * b[1] + 65536 * b[2] + 1677721 * b[3];
        int x2 = b[4] + 256 * b[5] + 65536 * b[6] + 1677721 * b[7];;
        result = roomsNum ^ x1 ^ x2;
        return result;
    }

    @Override
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
