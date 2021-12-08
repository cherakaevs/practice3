package buildings.dwelling;

import buildings.Space;
import buildings.Floor;
import buildings.exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Iterator;

public class DwellingFloor implements Floor, Serializable, Cloneable {
    private Space[] flats;

    public DwellingFloor(int num) {
        flats = new Space[num];
        for (int i = 0; i < num; i++){
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Space[] newFlats) {
        flats = new Space[newFlats.length];
        for (int i = 0; i < newFlats.length; i++) {
            flats[i] = newFlats[i];
        }
    }

    public int getSpacesNum() {
        return flats.length;
    }

    public double getSquare() {
        double sum = 0.0;
        for (int i = 0; i < flats.length; i++) {
            sum += flats[i].getSquare();
        }
        return sum;
    }

    public int getRoomsNum() {
        int sum = 0;
        for (int i = 0; i < flats.length; i++) {
            sum += flats[i].getRoomsNum();
        }
        return sum;
    }

    public Space[] getSpacesArray() {
        return flats;
    }

    public Space getSpace(int num) {
        if(num < 0 || num > flats.length) {
            throw new SpaceIndexOutOfBoundsException(num, flats.length);
        }
        return flats[num];
    }

    public void setSpace(int num, Space newFlat) {
        if(num < 0 || num > flats.length) {
            throw new SpaceIndexOutOfBoundsException(num, flats.length);
        }
        flats[num].setSquare(newFlat.getSquare());
        flats[num].setRoomsNum(newFlat.getRoomsNum());
    }

    public void addSpace(int num, Space newFlat) {
        if(num < 0 || num > flats.length) {
            throw new SpaceIndexOutOfBoundsException(num, flats.length);
        }
        int newNum = flats.length + 1;
        Space[] tmpFlats = new Space[newNum];

        for (int i = 0; i < num; i++) {
            tmpFlats[i] = flats[i];
        }

        tmpFlats[num] = newFlat;

        for (int i = num + 1; i < flats.length; i++) {
            tmpFlats[i + 1] = flats[i];
        }

        flats = tmpFlats;
    }

    public void removeSpace(int num){
        if(num < 0 || num > flats.length) {
            throw new SpaceIndexOutOfBoundsException(num, flats.length);
        }

        Space[] tmpFlats = new Space[flats.length - 1];

        for (int i = 0; i < num; i++) {
            tmpFlats[i] = flats[i];
        }

        for (int i = num; i < flats.length - 1; i++) {
            tmpFlats[i] = flats[i + 1];
        }

        flats = tmpFlats;
    }

    public Space getBestSpace(){
        Space tmpFlat = null;
        double maxSquare = 0;
        for (Space flat : flats){
            double tmp = flat.getSquare();
            if (tmp > maxSquare){
                tmpFlat = flat;
            }
        }
        return tmpFlat;
    }

    @Override
    public String toString(){
        String str = new String("DwellingFloor (" + getSpacesNum() +",");
        for (int i = 0; i < getSpacesNum(); i++){
            str += " " + this.getSpace(i).toString();
        }
        str += ")";
        return str;
    }

    @Override
    public boolean equals(Object object){
        boolean res = false;
        if ((object instanceof DwellingFloor) && (((DwellingFloor) object).getSpacesNum() == this.getSpacesNum())){
            for (int i = 0; i < this.getSpacesNum(); i++){
                if (this.getSpace(i).equals(((DwellingFloor)object).getSpace(i)) == false){
                    res = false;
                }
            }
            res = true;
        }
        return res;
    }

    @Override
    public int hashCode(){
        int result = 0;
        for (int i = 0; i < getSpacesNum(); i++){
            result += getSpacesNum() ^ getSpace(i).hashCode();
        }
        return result;
    }

    @Override
    public Object clone(){
        DwellingFloor result = null;
        try{
            result = (DwellingFloor)super.clone();
            result.flats = flats.clone();
            for (int i =0; i < flats.length; i++){
                result.flats[i] = (Space)flats[i].clone();
            }
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Iterator<Space> iterator(){
        Iterator<Space> iterator = new Iterator<Space>() {

            private int index;

            @Override
            public boolean hasNext() {
                if (index < getSpacesNum()){
                    return true;
                }
                else return false;
            }

            @Override
            public Space next() {
                return getSpace(index++);
            }
        };
        return iterator;
    }

    @Override
    public int compareTo(Floor o){
        if(this.getSpacesNum() < o.getSpacesNum())
            return -1;
        if(this.getSpacesNum() > o.getSpacesNum())
            return 1;
        return 0;
    }
}
