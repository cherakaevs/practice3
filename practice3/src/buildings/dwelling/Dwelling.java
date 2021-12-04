package buildings.dwelling;

import buildings.*;

import java.io.Serializable;
import java.util.Iterator;

public class Dwelling implements Building, Serializable, Cloneable {
    private Floor[] dwelling;

    public Dwelling(int numFloors, int[] numsFlats){
        dwelling = new DwellingFloor[numFloors];
        for (int i = 0; i < numFloors; i++){
            dwelling[i] = new DwellingFloor(numsFlats[i]);
        }
    }

    public Dwelling(Floor[] newDwelling){
        dwelling = newDwelling;
    }

    public int getFloorsNum(){
        return dwelling.length;
    }

    public int getSpacesNum(){
       int numFlats = 0;
       for (int i = 0; i < dwelling.length; i++){
           numFlats += dwelling[i].getSpacesNum();
       }
       return numFlats;
    }

    public double getSumSquare(){
        int square = 0;
        for (int i = 0; i < dwelling.length; i++){
            for (int j = 0; j < dwelling[i].getSpacesNum(); j++){
                square += dwelling[i].getSumSquare();
            }
        }
        return square;
    }

    public int getRoomsNum(){
        int rooms = 0;
        for (int i = 0; i < dwelling.length; i++){
            for (int j = 0; j < dwelling[i].getSpacesNum(); j++){
                rooms += dwelling[i].getRoomsNum();
            }
        }
        return rooms;
    }

    public Floor[] getFloorsArray() {
        return dwelling;
    }

    public Floor getFloor(int num) {
        if (num <= dwelling.length) {
            return dwelling[num];
        }
        else{
            System.out.println("Error: invalid floor number");
            System.exit(1);
        }
        return null;
    }

    public void setFloor(int num, Floor newFloor){
        if (num <= dwelling.length) {
            dwelling[num] = newFloor;
        }
        else{
            System.out.println("Error: invalid floor number");
            System.exit(1);
        }
    }

    public Space getSpace(int num){
        int tmp = 0;
        Space tmpFlat = new Flat();
        for(int i = 0; i < dwelling.length; i++) {
            for (int j = 0; j < dwelling[i].getSpacesNum(); j++, tmp++) {
                if (tmp == num) {
                    tmpFlat.setSquare(dwelling[i].getSpace(j).getSquare());
                    tmpFlat.setRoomsNum(dwelling[i].getSpace(j).getRoomsNum());
                }
            }
        }
        return tmpFlat;
    }

    public void setSpace(int num, Space newFlat){
        int tmp = 0;
        for(int i = 0; i < dwelling.length; i++) {
            for (int j = 0; j < dwelling[i].getSpacesNum(); j++, tmp++) {
                if (tmp == num) {
                    dwelling[i].setSpace(j, newFlat);
                }
            }
        }
    }

    public void addSpace(int num, Space newFlat){
        int tmp = 0;
        for (int i=0; i < dwelling.length; i++){
            for(int j = 0; j < dwelling[i].getSpacesNum() ; j++, tmp++){
                if(tmp == num){
                    dwelling[i].addSpace(j, newFlat);
                }
            }
        }
    }

    public void removeSpace(int num){
        int tmp = 0;
        for (int i = 0; i < dwelling.length; i++){
            for (int j = 0; j < dwelling[i].getSpacesNum(); j++, tmp++){
                if(tmp == num)
                    dwelling[i].removeSpace(j);
            }
        }
    }

    public Space getBestSpace(){
        Space bestSpaceFlat = new Flat();
        for (int i = 0; i<dwelling.length; i++){
            for (int j = 0; j<dwelling[i].getSpacesNum(); j++){
                if (bestSpaceFlat.getSquare() < dwelling[i].getSpace(j).getSquare())
                    bestSpaceFlat = dwelling[i].getSpace(j);
            }
        }
        return bestSpaceFlat;
    }

    public Space[] getSortedSpaceArray() {
        int flatsCounter = 0;
        for (int i = 0; i < dwelling.length; i++) {
            for (int j = 0; j < dwelling[i].getSpacesNum(); j++) {
                flatsCounter++;
            }
        }

        Space[] arrFlats = new Space[flatsCounter];
        int tmp = 0;
        for (int i = 0; i < dwelling.length; i++) {
            for (int j = 0; j < dwelling[i].getSpacesNum(); j++, tmp++) {
                arrFlats[tmp] = dwelling[i].getSpace(j);
            }
        }

        boolean isSorted = false;
        Space buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < arrFlats.length - 1; i++) {
                if (arrFlats[i].getSquare() > arrFlats[i + 1].getSquare()) {
                    isSorted = false;

                    buf = arrFlats[i];
                    arrFlats[i] = arrFlats[i + 1];
                    arrFlats[i + 1] = buf;
                }
            }
        }
        return arrFlats;
    }

    @Override
    public String toString(){
        String str = new String("Dwelling (" + this.getFloorsNum()+ ",");
        for (int i = 0; i < this.getFloorsNum(); i++){
            str +=" " + this.getFloor(i).toString();
        }
        str += ")";
        return str;
    }

    @Override
    public boolean equals(Object object){
        boolean res = false;
        if ((object instanceof Dwelling) && (((Dwelling) object).getFloorsNum() == this.getFloorsNum())){
            for (int i = 0; i < this.getFloorsNum(); i++){
                if (this.getFloor(i).equals(((Dwelling)object).getFloor(i)) == false){
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
        for(int i = 0; i < getFloorsNum(); i++){
            result += getFloorsNum() ^ getFloor(i).hashCode();
        }
        return result;
    }

    @Override
    public Object clone(){
        Object result = null;
        try{
            result = super.clone();
            ((Dwelling)result).dwelling = dwelling.clone();
            for (int i = 0; i < dwelling.length; i++){
                ((Dwelling)result).dwelling[i] = (Floor)dwelling[i].clone();
            }
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Iterator<Floor> iterator(){
        Iterator<Floor> iterator = new Iterator<Floor>() {

            private int index = 0;
            @Override
            public boolean hasNext() {
                if(index < getFloorsNum()){
                    return true;
                }
                else return false;
            }

            @Override
            public Floor next() {
                return getFloor(index++);
            }
        };
        return iterator;
    }
}