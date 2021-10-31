package buildings;


import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;

public class HotelFactory {
    public Space createSpace(int area) {
        Space space = new Flat(area);
        return space;
    }

    public Space createSpace(int rooms, int area){
        Space space = new Flat(rooms, area);
        return space;
    }

    public Floor createFloor(int spacesCount){
        Floor floor = new HotelFloor(spacesCount);
        return floor;
    }

    public Floor createFloor(Space[] spaces){
        Floor floor = new HotelFloor(spaces);
        return floor;
    }

    public Building createBuilding(int floorsCount, int[] spacesCount){
        Building building = new Hotel(floorsCount, spacesCount);
        return building;
    }

    public Building createBuilding(Floor[] floors){
        Building building = new Hotel(floors);
        return building;
    }
}
