package buildings;

import java.util.Iterator;

public class SynchonizedFloor implements Floor{
    private Floor synchronizedFloor;

    public SynchonizedFloor(Floor floor){
        synchronizedFloor = floor;
    }

    @Override
    public synchronized int getSpacesNum() {
        return synchronizedFloor.getSpacesNum();
    }

    @Override
    public synchronized double getSumSquare() {
        return synchronizedFloor.getSumSquare();
    }

    @Override
    public synchronized int getRoomsNum() {
        return synchronizedFloor.getRoomsNum();
    }

    @Override
    public synchronized Space[] getSpacesArray(){
        return synchronizedFloor.getSpacesArray();
    }

    @Override
    public synchronized Space getSpace(int num){
        return synchronizedFloor.getSpace(num);
    }

    @Override
    public synchronized void setSpace(int num, Space newSpace) {
        synchronizedFloor.setSpace(num, newSpace);
    }

    @Override
    public synchronized void addSpace(int num, Space newSpace) {
        synchronizedFloor.addSpace(num, newSpace);
    }

    @Override
    public synchronized void removeSpace(int num) {
        synchronizedFloor.removeSpace(num);
    }

    @Override
    public synchronized Space getBestSpace() {
        return synchronizedFloor.getBestSpace();
    }

    @Override
    public synchronized Object clone() {
        return synchronizedFloor.clone();
    }

    @Override
    public Iterator<Space> iterator(){
        return null;
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return synchronizedFloor.compareTo(o);
    }

}
