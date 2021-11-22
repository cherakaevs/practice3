package buildings.net.server.sequental;

import buildings.Building;
import buildings.Buildings;
import buildings.dwelling.Dwelling;
import buildings.exceptions.BuildingUnderArrestException;
import buildings.office.OfficeBuilding;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialServer {
    public static double priceCheck(Building building) throws BuildingUnderArrestException {
        if (arrestedBuildong(building))
            throw new BuildingUnderArrestException();

        double squarePrice;
        int square = building.getSumSquare();
        if (building instanceof Dwelling) {
            squarePrice = 1000;
        } else if (building instanceof OfficeBuilding) {
            squarePrice = 1500;
        } else {
            squarePrice = 2000;
        }

        double result = square * squarePrice;
        return result;
    }

    public static boolean arrestedBuildong(Building building) {

        int chance = (int) (Math.random() * 10);
        if (chance > 8) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(0)){

            System.out.println("Server started");

            while (true){
                Socket socket = server.accept();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());


                Building building;
                int flag;
                while ((flag = dis.readInt()) == 1){
                    building = Buildings.deserializeBuilding(dis);
                    Object price;
                    try {
                        System.out.println("Building: " + building);
                        price = priceCheck(building);
                        System.out.println("---------------\nPrice:" + price +"\n-----------------");
                        new ObjectOutputStream(dos).writeObject(price);
                    }
                    catch (BuildingUnderArrestException e){
                        new ObjectOutputStream(dos).writeObject(new BuildingUnderArrestException());
                    }
                }
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
