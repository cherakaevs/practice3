package buildings;

import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

public class OfficeFactory implements BuildingFactory{

    public Space createSpace(double area) {
        Space space = new Office(area);
        return space;
    }

    public Space createSpace(int rooms, double area){
        Space space = new Office(rooms, area);
        return space;
    }

    public Floor createFloor(int spacesCount){
        Floor floor = new OfficeFloor(spacesCount);
        return floor;
    }

    public Floor createFloor(Space[] spaces){
        Floor floor = new OfficeFloor(spaces);
        return floor;
    }

    public Building createBuilding(int floorsCount, int[] spacesCount){
        Building building = new OfficeBuilding(floorsCount, spacesCount);
        return building;
    }

    public Building createBuilding(Floor[] floors){
        Building building = new OfficeBuilding(floors);
        return building;
    }
}
