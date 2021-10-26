package buildings;

import buildings.exceptions.InexchangeableFloorsException;
import buildings.exceptions.InexchangeableSpacesException;

public class PlacementExchanger {
    public static boolean isChangableSpaces(Space s1, Space s2){
        return (s1.getRoomsNum()==s2.getRoomsNum() && s1.getSquare()== s2.getSquare());
    }

    public static boolean isChangableFloors(Floor f1, Floor f2){
        return (f1.getSpacesNum() == f2.getSpacesNum() && f1.getSumSquare() == f2.getSumSquare());
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2){
        if(!isChangableSpaces(floor1.getSpace(index1), floor2.getSpace(index2))){
            throw new InexchangeableSpacesException();
        }
        else{
            Space tmp = floor1.getSpace(index1);
            floor1.setSpace(index1, floor2.getSpace(index2));
            floor2.setSpace(index2, tmp);
        }
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2){
        if (!isChangableFloors(building1.getFloor(index1), building2.getFloor(index2))){
            throw new InexchangeableFloorsException();
        }
        else {
            Floor tmp = building1.getFloor(index1);
            building1.setFloor(index1, building2.getFloor(index2));
            building2.setFloor(index2, tmp);
        }
    }
}
