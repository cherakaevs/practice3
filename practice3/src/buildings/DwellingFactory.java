package buildings;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;

public class DwellingFactory implements BuildingFactory {

    public Space createSpace(double area) {
        Space space = new Flat(area);
        return space;
    }

    public Space createSpace(int rooms, double area) {
        Space space = new Flat(rooms, area);
        return space;
    }

    public Floor createFloor(int spacesCount) {
        Floor floor = new DwellingFloor(spacesCount);
        return floor;
    }

    public Floor createFloor(Space[] spaces) {
        Floor floor = new DwellingFloor(spaces);
        return floor;
    }

    public Building createBuilding(int floorsCount, int[] spacesCount){
        Building building = new Dwelling(floorsCount, spacesCount);
        return building;
    }

    public Building createBuilding(Floor[] floors){
        Building building = new Dwelling(floors);
        return building;
    }
}
