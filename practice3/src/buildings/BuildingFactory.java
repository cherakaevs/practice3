package buildings;

public interface BuildingFactory {
    Space createSpace(double area);
    Space createSpace(int rooms, double area);
    Floor createFloor(int spacesCount);
    Floor createFloor(Space[] spaces);
    Building createBuilding(int floorsCount, int[] spacesCount);
    Building createBuilding(Floor[] floors);
}
