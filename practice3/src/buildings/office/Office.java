package buildings.office;

import buildings.Space;
import buildings.exceptions.InvalidRoomsCountException;
import buildings.exceptions.InvalidSpaceAreaException;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class Office implements Space, Serializable, Cloneable {
    private int rooms;
    private int square;

    private static final int SQUARE = 250;
    private static final int ROOMS = 1;

    public Office(){
        this.rooms = ROOMS;
        this.square = SQUARE;
    }

    public Office(int square){
        if (square < 30 || square > 500){
            throw new InvalidSpaceAreaException(square);
        }
        this.rooms = ROOMS;
        this.square = square;
    }

    public Office(int rooms, int square){
        if (rooms < 1 || rooms > 10){
            throw new InvalidRoomsCountException(rooms);
        }
        if (square < 30 || square > 500){
            throw new InvalidSpaceAreaException(square);
        }
        this.rooms = rooms;
        this.square = square;
    }

    public int getRoomsNum(){
        return this.rooms;
    }

    public int getSquare(){
        return this.square;
    }

    public void setRoomsNum(int num){
        if (num < 1 || num > 10){
            throw new InvalidRoomsCountException(num);
        }
        this.rooms = num;
    }

    public void setSquare(int num){
        if (num < 30 || num > 500){
            throw new InvalidSpaceAreaException(num);
        }
        this.square = num;
    }

    public String toString(){
        String str = new String("Office (" + rooms + ", " + square + ")");
        return str;
    }

    public boolean equals(Object object){
        if ((object instanceof Office) && (((Office) object).rooms == this.rooms) && (((Office) object).square == this.square)) {
            return true;
        }
        return false;
    }

    public int hashCode(){
        int result;
        byte [] b = ByteBuffer.allocate(4).putInt(square).array();
        int x1 = b[0] + 256 * b[1];
        int x2 = b[2] + 256 * b[3];
        result = rooms ^ x1 ^ x2;
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
        if (square < o.getSquare())
            return -1;
        if (square > o.getSquare())
            return 1;
        return 0;
    }
}
