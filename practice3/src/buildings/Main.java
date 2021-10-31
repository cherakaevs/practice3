package buildings;

import buildings.dwelling.Flat;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

import java.util.Iterator;


public class Main {
    public static void main(String[] args) {
        int[] rooms = {1, 2, 3, 4};
        Building testBuilding = new OfficeBuilding(4, rooms);
        Building test2 = null;

        Floor testFloor = new OfficeFloor(4);

        Space testSpace = new Office(4,200);

        System.out.println(testBuilding.getSpacesNum());

       /* System.out.println(testBuilding.getFloorsNum());

        System.out.println(testBuilding.getSpacesNum());

        System.out.println(testBuilding.getSpace(6).getSquare());
        testBuilding.getSpace(6).setSquare(400);
        System.out.println(testBuilding.getSpace(6).getSquare());

        testBuilding.setSpace(2, new Office());
        System.out.println(testBuilding.getSpace(2).getSquare());

        System.out.println(testBuilding.getFloor(1).getSpacesArray()[1].getSquare());

        System.out.println(testBuilding.getSortedSpaceArray()[0].getSquare());

        System.out.println("[0] Floor square: " + testBuilding.getFloor(0).getSumSquare() + "\nRooms: " +testBuilding.getFloor(0).getRoomsNum() );
        System.out.println("[1] Floor square: " + testBuilding.getFloor(1).getSumSquare() + "\nRooms: " +testBuilding.getFloor(1).getRoomsNum() );
        System.out.println("[2] Floor square: " + testBuilding.getFloor(2).getSumSquare() + "\nRooms: " +testBuilding.getFloor(2).getRoomsNum() );
        System.out.println("[3] Floor square: " + testBuilding.getFloor(3).getSumSquare() + "\nRooms: " +testBuilding.getFloor(3).getRoomsNum() );

        testBuilding.getFloor(1).removeSpace(1);
        System.out.println("After remove");

        System.out.println("[0] Floor square: " + testBuilding.getFloor(0).getSumSquare() + "\nRooms: " +testBuilding.getFloor(0).getRoomsNum() );
        System.out.println("[1] Floor square: " + testBuilding.getFloor(1).getSumSquare() + "\nRooms: " +testBuilding.getFloor(1).getRoomsNum() );
        System.out.println("[2] Floor square: " + testBuilding.getFloor(2).getSumSquare() + "\nRooms: " +testBuilding.getFloor(2).getRoomsNum() );
        System.out.println("[3] Floor square: " + testBuilding.getFloor(3).getSumSquare() + "\nRooms: " +testBuilding.getFloor(3).getRoomsNum() );
*/
       /* try {
            FileOutputStream fos = new FileOutputStream("building1.bin");
            FileInputStream fis = new FileInputStream("building1.bin");
            Buildings.serializeBuilding(testBuilding, fos);
            test2 =  Buildings.deserializeBuilding(fis);
            fos.close();
            fis.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(test2.getSpacesNum());

        try{
            PrintWriter w = new PrintWriter("formatBuilding.txt");
            Buildings.writeBuildingFormat(testBuilding, w);
            w.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        try {
            Path path = Paths.get("formatBuilding.txt");
            Scanner scanner = new Scanner(path);
            test2 = Buildings.readBuilding(scanner);
            scanner.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }*/

       /* Flat testFlat = new Flat(5, 228);
        System.out.println(testFlat.toString());
        OfficeFloor testFloor = new OfficeFloor(2);
        System.out.println(testFloor.toString());
        System.out.println(testBuilding.toString());
        */

        System.out.println(testBuilding.toString());

        Iterator<Floor> it = testBuilding.iterator();

    }
}
