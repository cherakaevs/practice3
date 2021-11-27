package buildings.net.server.parallel;

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
        if (arrestedBuilding(building))
            throw new BuildingUnderArrestException();

        double squarePrice;
        double square = building.getSumSquare();
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

    public static boolean arrestedBuilding(Building building) {

        int chance = (int) (Math.random() * 10);
        if (chance > 8) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(1234)){

            System.out.println("Server started");

            while (true){
                try{
                    Socket socket = server.accept();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try (ServerSocket socket1 = new ServerSocket(1235)) {
                                System.out.println("Server started");

                                while (true) {
                                    Socket socket2 = socket1.accept();
                                    DataOutputStream dos = new DataOutputStream(socket2.getOutputStream());
                                    DataInputStream dis = new DataInputStream(socket2.getInputStream());

                                    try {
                                        int flag;
                                        Building building;

                                        while ((flag = dis.readInt()) == 1) {
                                            building = Buildings.deserializeBuilding(dis);
                                            Object price;

                                            System.out.println("Building: " + building);

                                            try {
                                                price = priceCheck(building);
                                                System.out.println("Price: " + price);
                                                new ObjectOutputStream(dos).writeObject(price);
                                            } catch (BuildingUnderArrestException e) {
                                                new ObjectOutputStream(dos).writeObject(new BuildingUnderArrestException());
                                            }
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e){
            new RuntimeException(e);
        }
    }
}
