package buildings.net.server.parallel;

import buildings.*;
import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.Hotel;
import buildings.exceptions.BuildingUnderArrestException;
import buildings.office.OfficeBuilding;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {
    public static double priceCheck(Building building) throws BuildingUnderArrestException {
        if (arrestedBuilding(building))
            throw new BuildingUnderArrestException();

        double squarePrice;
        double square = building.getSumSquare();
        if (building instanceof Hotel) {
            squarePrice = 2000;
        } else if (building instanceof OfficeBuilding) {
            squarePrice = 1500;
        } else {
            squarePrice = 1000;
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

        try (ServerSocket server = new ServerSocket(1234)) {

            System.out.println("Server started");

            while (true) {
                Socket socket = server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try (
                                DataInputStream dis = new DataInputStream(socket.getInputStream());
                                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                        ) {
                            String type;
                            Building building;
                            Double price;
                            String result;

                            while (!(type = dis.readUTF()).equals("Exit")) {
                                System.out.println("\nBuilding type: " + type + "\n");

                                switch (type) {
                                    case "Dwelling":
                                        Buildings.setBuildingFactory(new DwellingFactory());
                                        break;
                                    case "OfficeBuilding":
                                        Buildings.setBuildingFactory(new OfficeFactory());
                                        break;

                                }

                                building = Buildings.inputBuilding(dis);

                                try {
                                    System.out.println("Building building: " + building);
                                    price = priceCheck(building);
                                    System.out.println("Price: " + price);
                                    result = price.toString();
                                } catch (BuildingUnderArrestException e) {
                                    result = "Building is arrested!";
                                }

                                dos.writeUTF(result);
                                dos.flush();
                                System.out.println("\n---------------------------------------------------------------------------");
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

