package buildings;

import java.util.Iterator;

public class SynchonizedFloor implements Floor{
    private Floor synchronizedFloor;

    public SynchonizedFloor(Floor floor){
        synchronizedFloor = floor;
    }

    @Override
    public int getSpacesNum() {
        return synchronizedFloor.getSpacesNum();
    }

    @Override
    public int getSumSquare() {
        return synchronizedFloor.getSumSquare();
    }

    @Override
    public int getRoomsNum() {
        return synchronizedFloor.getRoomsNum();
    }

    @Override
    public Space[] getSpacesArray(){
        return synchronizedFloor.getSpacesArray();
    }

    @Override
    public Space getSpace(int num){
        return synchronizedFloor.getSpace(num);
    }

    @Override
    public void setSpace(int num, Space newSpace) {
        synchronizedFloor.setSpace(num, newSpace);
    }

    @Override
    public void addSpace(int num, Space newSpace) {
        synchronizedFloor.addSpace(num, newSpace);
    }

    @Override
    public void removeSpace(int num) {
        synchronizedFloor.removeSpace(num);
    }

    @Override
    public Space getBestSpace() {
        return synchronizedFloor.getBestSpace();
    }

    @Override
    public Object clone() {
        return synchronizedFloor.clone();
    }

    @Override
    public Iterator<Space> iterator(){
        return null;
    }

    @Override
    public int compareTo(Floor o) {
        return synchronizedFloor.compareTo(o);
    }

}
