package buildings;

public interface BuildingFactory {
    Space createSpace(int area);
    Space createSpace(int rooms, int area);
    Floor createFloor(int spacesCount);
    Floor createFloor(Space[] spaces);
    Building createBuilding(int floorsCount, int[] spacesCount);
    Building createBuilding(Floor[] floors);
}
