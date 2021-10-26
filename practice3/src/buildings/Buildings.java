package buildings;

import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

import java.io.*;
import java.util.Scanner;

public class Buildings {
    public static void outputBuilding (Building building, OutputStream out){
        DataOutputStream outStream = new DataOutputStream(out);
        try {
            outStream.writeInt(building.getFloorsNum());
            for (int i=0; i < building.getFloorsNum(); i++){
                outStream.writeInt(building.getFloor(i).getSpacesNum());
                for (int j = 0; j < building.getFloor(i).getSpacesNum(); j++){
                    outStream.writeInt(building.getFloor(i).getSpace(j).getRoomsNum());
                    outStream.writeInt(building.getFloor(i).getSpace(j).getSquare());
                }
            }
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static Building inputBuilding(InputStream in){
        DataInputStream inputStream = new DataInputStream(in);
        Building newBuildind = null;
        try {
            int floorsNum = inputStream.readInt();
            Floor[] floors = new Floor[floorsNum];
            int spacesNum;
            for (int i = 0; i < floorsNum; i++){
                spacesNum = inputStream.readInt();
                floors[i] = new OfficeFloor(spacesNum);
                int rooms;
                int square;
                for (int j = 0; j < spacesNum; j++){
                    rooms = inputStream.readInt();
                    square = inputStream.readInt();
                    floors[i].getSpace(j).setRoomsNum(rooms);
                    floors[i].getSpace(j).setSquare(square);
                }
                newBuildind = new OfficeBuilding(floors);
            }
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        return newBuildind;
    }

    public static void writeBuilding(Building building, Writer out){
        try {
            out.write(building.getFloorsNum() + " ");
            for (int i = 0; i < building.getFloorsNum(); i++){
                out.write(building.getFloor(i).getSpacesNum());
                for (int j = 0; j < building.getFloor(i).getSpacesNum(); j++){
                    out.write(building.getFloor(i).getSpace(j).getRoomsNum() + " ");
                    out.write(building.getFloor(i).getSpace(j).getSquare() + " ");
                }
            }
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static Building readBuilding(Reader in){
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        try{
            tokenizer.nextToken();
        }
        catch (IOException exception){
            exception.printStackTrace();
        }

        int floorsNum = (int)tokenizer.nval;
        if (floorsNum == 0){
            return null;
        }
        else {
            Floor[] floors = new Floor[floorsNum];
            int spacesNum;
            int roomsNum;
            int square;
            for (int i = 0; i < floorsNum; i++){
                try {
                    tokenizer.nextToken();
                }
                catch (IOException exception){
                    exception.printStackTrace();
                }
                spacesNum = (int) tokenizer.nval;
                floors[i] = new OfficeFloor(spacesNum);
                for (int j = 0; j < spacesNum; j++){
                    try {
                        tokenizer.nextToken();
                    }
                    catch(IOException exception){
                        exception.printStackTrace();
                    }
                    roomsNum = (int) tokenizer.nval;
                    try {
                        tokenizer.nextToken();
                    }
                    catch(IOException exception){
                        exception.printStackTrace();
                    }
                    square = (int) tokenizer.nval;
                    floors[i].setSpace(j, new Office(roomsNum, square));
                }
            }
            Building newBuilding = new OfficeBuilding(floors);
            return newBuilding;
        }
    }

    public static void serializeBuilding(Building building, OutputStream out){
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(building);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static Building deserializeBuilding(InputStream in){
        Building building = null;
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(in);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        try{
            building = (Building)inputStream.readObject();
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return building;
    }

    public static void writeBuildingFormat (Building building, Writer out){
        ((PrintWriter)out).printf("\tBuilding");
        int floorsNum = building.getFloorsNum();
        ((PrintWriter)out).printf("\nFloors number %d\n", floorsNum);
        int spacesNum;
        int roomsNum;
        int square;
        for (int i = 0; i < floorsNum; i++){
            spacesNum = building.getFloor(i).getSpacesNum();
            ((PrintWriter)out).printf("\nSpaces number %d", spacesNum);
            for (int j = 0; j < spacesNum; j++){
                roomsNum = building.getFloor(i).getSpace(j).getRoomsNum();
                square = building.getFloor(i).getSpace(j).getSquare();
                ((PrintWriter)out).printf("\nRooms %d Square %d",roomsNum, square);
            }
        }
    }

    public static Building readBuilding(Scanner scanner){
        scanner.skip("\tBuilding");
        scanner.skip("\nFloors number");
        int floorsNum = scanner.nextInt();
        Floor[] floors = new Floor[floorsNum];

        int spacesNum;
        int roomsNum;
        int square;
            scanner.skip("\n");
        for (int i = 0; i < floorsNum; i++){
            scanner.skip("\nSpaces number");
            spacesNum = scanner.nextInt();
            floors[i] = new OfficeFloor(spacesNum);
            for (int j = 0; j < spacesNum; j++){
                scanner.skip("\nRooms");
                roomsNum = scanner.nextInt();
                scanner.skip(" Square");
                square = scanner.nextInt();
                floors[i].setSpace(j, new Office(roomsNum, square));
            }
        }
        Building building = new OfficeBuilding(floors);
        return building;
    }
}
