package buildings.office;

import buildings.Building;
import buildings.Floor;
import buildings.Space;
import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.lists.LinkList;
import buildings.lists.LinkListNode;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeBuilding  implements Building, Serializable, Cloneable {
    private LinkList officeBuilding;

    private LinkListNode getNodeByNum(int num){
        if (num < 0 || num > getSpacesNum()){
            throw new SpaceIndexOutOfBoundsException(num, getSpacesNum());
        }
        return officeBuilding.getNode(num);
    }

    private void addByNum(Floor newFloor, int num){
        if (num < 0 || num > getSpacesNum()){
            throw new SpaceIndexOutOfBoundsException(num, getSpacesNum());
        }
        officeBuilding.insert(newFloor, num);
    }

    private void removeByNum(int num){
        if (num < 0 || num > getSpacesNum()){
            throw new SpaceIndexOutOfBoundsException(num, getSpacesNum());
        }
        officeBuilding.remove(num);
    }

    public OfficeBuilding(int floorsQuantity, int[] officesQuantity){
        officeBuilding = new LinkList();
        for(int i = 0; i < floorsQuantity; i++){
            officeBuilding.pushBack(new OfficeFloor(officesQuantity[i]));
        }
    }

    public OfficeBuilding(Floor[] floors){
        officeBuilding = new LinkList();
        for (int i = 0; i < floors.length ; i++){
            officeBuilding.pushBack(floors[i]);
        }
    }

    public int getFloorsNum(){
        return officeBuilding.length();
    }

    public int getSpacesNum(){
        int sum = 0;
        for (int i = 0; i < officeBuilding.length(); i++) {
           sum += officeBuilding.getNode(i).floor.getSpacesNum();
        }
        return sum;
    }

    public int getSumSquare(){
        int square = 0;
        for (int i = 0; i < officeBuilding.length(); i++){
            square += officeBuilding.getNode(i).floor.getSumSquare();
        }
        return square;
    }

    public int getRoomsNum(){
        int rooms = 0;
        for (int i = 0; i < officeBuilding.length(); i++){
            rooms += officeBuilding.getNode(i).floor.getRoomsNum();
        }
        return rooms;
    }

    public Floor[] getFloorsArray(){
        Floor[] tmpFloors = new Floor[officeBuilding.length()];
        for (int i = 0; i < officeBuilding.length(); i++){
            tmpFloors[i] = officeBuilding.getNode(i).floor;
        }
        return tmpFloors;
    }

    public Floor getFloor(int num){
        if(num < 0 || num > officeBuilding.length()) {
            throw new SpaceIndexOutOfBoundsException(num, officeBuilding.length());
        }
        return officeBuilding.getNode(num).floor;
    }

    public void setFloor(int num, Floor newFloor){

        officeBuilding.getNode(num).floor = newFloor;
    }

    public Space getSpace(int num){
        if(num < 0 || num > getSpacesNum()) {
            throw new SpaceIndexOutOfBoundsException(num, getSpacesNum());
        }
        int count = 0;
        Space tmp = null;
        for (int i = 0; i < officeBuilding.length(); i++){
            for (int j = 0; j < officeBuilding.getNode(i).floor.getSpacesNum(); j++){
                if (count == num){
                    tmp = officeBuilding.getNode(i).floor.getSpace(j);
                }
                else count++;
            }
        }
        return  tmp;
    }

    public void setSpace(int num, Space newOffice){
        if(num < 0 || num > getSpacesNum()) {
            throw new SpaceIndexOutOfBoundsException(num, getSpacesNum());
        }
        int count = 0;
        for (int i = 0; i < officeBuilding.length(); i++){
            for (int j = 0; j < officeBuilding.getNode(i).floor.getSpacesNum(); j++){
                if (count == num){
                   officeBuilding.getNode(i).floor.setSpace(j, newOffice);
                }
                else count++;
            }
        }
    }

    public void addSpace(int num, Space newOffice){
        if(num < 0 || num > officeBuilding.length()) {
            throw new SpaceIndexOutOfBoundsException(num, officeBuilding.length());
        }
        int count = 0;
        for (int i = 0; i < officeBuilding.length(); i++){
            for (int j = 0; j < officeBuilding.getNode(i).floor.getSpacesNum(); j++){
                if (count == num){
                    officeBuilding.getNode(i).floor.addSpace(j, newOffice);
                }
                else count++;
            }
        }
    }

    public void removeSpace(int num){
        if(num < 0 || num > officeBuilding.length()) {
            throw new SpaceIndexOutOfBoundsException(num, officeBuilding.length());
        }
        int count = 0;
        for (int i = 0; i < officeBuilding.length(); i++){
            for (int j = 0; j < officeBuilding.getNode(i).floor.getSpacesNum(); j++){
                if (count == num){
                    officeBuilding.getNode(i).floor.removeSpace(j);
                }
                else count++;
            }
        }
    }

    public Space getBestSpace(){
        Space tmp = new Office();
        for (int i = 0; i < officeBuilding.length(); i++){
            if (officeBuilding.getNode(i).floor.getBestSpace().getSquare() > tmp.getSquare())
                tmp = officeBuilding.getNode(i).floor.getBestSpace();
        }
        return tmp;
    }

   public Space[] getSortedSpaceArray(){
        int count = 0;

        Space[] sortedArray = new Office[getSpacesNum()];
        for (int i = 0; i < officeBuilding.length(); i++){
            for (int j = 0; j < officeBuilding.getNode(i).floor.getSpacesNum(); j++, count++){
                sortedArray[count] = officeBuilding.getNode(i).floor.getSpace(j);
            }
        }

        boolean isSorted = false;
        Space buf;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < sortedArray.length - 1; i++) {
                if (sortedArray[i].getSquare() > sortedArray[i + 1].getSquare()) {
                    isSorted = false;

                    buf = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = buf;
                }
            }
        }
        return sortedArray;
    }

    public String toString(){
        String str = new String("OfficeBuilding (" + this.getFloorsNum()+ ",");
        for (int i = 0; i < this.getFloorsNum(); i++){
            str +=" " + this.getFloor(i).toString();
        }
        str += ")";
        return str;
    }

    public boolean equals(Object object){
        boolean res = false;
        if ((object instanceof OfficeBuilding) && (((OfficeBuilding) object).getFloorsNum() == this.getFloorsNum())){
            for (int i = 0; i < this.getFloorsNum(); i++){
                if (this.getFloor(i).equals(((OfficeBuilding)object).getFloor(i)) == false){
                    res = false;
                }
            }
            res = true;
        }
        return res;
    }

    public int hashCode(){
        int result = 0;
        for (int i = 0; i < getFloorsNum(); i++){
            result += getFloorsNum() ^ getFloor(i).hashCode();
        }
        return result;
    }

    public Object clone(){
        Object result = null;
        try{
            result = super.clone();
            ((OfficeBuilding)result).officeBuilding = (LinkList)officeBuilding.clone();
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
                if (index < getFloorsNum()){
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
