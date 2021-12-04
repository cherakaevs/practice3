package buildings.office;

import buildings.Space;
import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class Office implements Space, Serializable, Cloneable {
    private int rooms;
    private double square;

    private static final double SQUARE = 250.0;
    private static final int ROOMS = 1;

    public Office(){
        this.rooms = ROOMS;
        this.square = SQUARE;
    }

    public Office(double square){
        if (square < 20 || square > 500){
            throw new InvalidSpaceAreaException(square);
        }
        this.rooms = ROOMS;
        this.square = square;
    }

    public Office(int rooms, double square){
        if (rooms < 1 || rooms > 10){
            throw new InvalidRoomsCountException(rooms);
        }
        if (square < 20 || square > 500){
            throw new InvalidSpaceAreaException(square);
        }
        this.rooms = rooms;
        this.square = square;
    }

    public int getRoomsNum(){
        return this.rooms;
    }

    public double getSquare(){
        return this.square;
    }

    public void setRoomsNum(int num){
        if (num < 1 || num > 10){
            throw new InvalidRoomsCountException(num);
        }
        this.rooms = num;
    }

    public void setSquare(double num){
        if (num < 20 || num > 500){
            throw new InvalidSpaceAreaException(num);
        }
        this.square = num;
    }

    @Override
    public String toString(){
        String str = new String("Office (" + rooms + ", " + square + ")");
        return str;
    }

    @Override
    public boolean equals(Object object){
        if ((object instanceof Office) && (((Office) object).rooms == this.rooms) && (((Office) object).square == this.square)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result;
        byte [] b = ByteBuffer.allocate(8).putDouble(square).array();
        int x1 = b[0] + 256 * b[1] + 65536 * b[2] + 1677721 * b[3];
        int x2 = b[4] + 256 * b[5] + 65536 * b[6] + 1677721 * b[7];;
        result = rooms ^ x1 ^ x2;
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
        if (square < o.getSquare())
            return -1;
        if (square > o.getSquare())
            return 1;
        return 0;
    }
}
