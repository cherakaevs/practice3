package buildings.office;

import buildings.Floor;
import buildings.Space;
import buildings.exceptions.SpaceIndexOutOfBoundsException;
import buildings.lists.ArrList;
import buildings.lists.ArrListNode;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeFloor implements Floor, Serializable, Cloneable {
    private ArrList floor;

    private ArrListNode getNodeByNum(int num){
        if (num < 0 || num > getSpacesNum()){
            throw new SpaceIndexOutOfBoundsException(num, getSpacesNum());
        }
        return floor.getNode(num);
    }

    private void addByNum(Space newSpace, int num){
        if (num < 0 || num > getSpacesNum()){
            throw new SpaceIndexOutOfBoundsException(num, getSpacesNum());
        }
        floor.insert(newSpace, num);
    }

    private void removeByNum(int num){
        if (num < 0 || num > getSpacesNum()){
            throw new SpaceIndexOutOfBoundsException(num, getSpacesNum());
        }
        floor.remove(num);
    }

    public OfficeFloor(int num){
        floor = new ArrList(num);
    }

    public OfficeFloor(Space[] offices){
        floor = new ArrList();
        for (int i = 0; i < offices.length; i++){
            floor.pushBack(offices[i]);
        }
    }

    public int getSpacesNum(){
        return floor.length();
    }

    public double getSumSquare(){
        double square = 0;
        for (int i = 0; i < floor.length(); i++){
            square += floor.getNode(i).getSpace().getSquare();
        }
        return square;
    }

    public int getRoomsNum(){
        int rooms = 0;
        for (int i = 0; i < floor.length(); i++){
            rooms += floor.getNode(i).getSpace().getRoomsNum();
        }
        return rooms;
    }

    public Space[] getSpacesArray(){
        Space[] tmp = new Space[floor.length()];
        for (int i = 0; i < floor.length(); i++){
            tmp[i] = floor.getNode(i).getSpace();
        }
        return tmp;
    }

    public Space getSpace(int num){
        return floor.getNode(num).getSpace();
    }

    public void setSpace(int num, Space newOffice){
        floor.getNode(num).space = newOffice;
    }

    public void addSpace(int num, Space newOffice){
        addByNum(newOffice, num);
    }

    public void removeSpace(int num){
        removeByNum(num);
    }

    public Space getBestSpace(){
        Space tmp = new Office();
        for (int i = 0; i < floor.length(); i++){
            if (tmp.getSquare() < floor.getNode(i).getSpace().getSquare())
                tmp = floor.getNode(i).getSpace();
        }
        return tmp;
    }

    @Override
    public String toString(){
        String str = new String("OfficeFloor (" + getSpacesNum() +",");
        for (int i = 0; i < getSpacesNum(); i++){
            str += " " + this.getSpace(i).toString();
        }
        str += ")";
        return str;
    }

    @Override
    public boolean equals(Object object){
        boolean res = false;
        if ((object instanceof OfficeFloor) && (((OfficeFloor) object).getSpacesNum() == this.getSpacesNum())){
            for (int i = 0; i < this.getSpacesNum(); i++){
                if (this.getSpace(i).equals(((OfficeFloor)object).getSpace(i)) == false){
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
        Object result = null;
        Space[] tmp = this.getSpacesArray();

        for (int i = 0; i < tmp.length; i++) {
                tmp[i] = (Space) this.getSpace(i).clone();
        }
        result = new OfficeFloor(tmp);
        return result;
    }

    @Override
    public Iterator<Space> iterator(){
        Iterator<Space> iterator = new Iterator<Space>() {

            private int index = 0;

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