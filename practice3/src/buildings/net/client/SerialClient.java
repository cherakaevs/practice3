package buildings.net.client;

import buildings.*;
import buildings.exceptions.BuildingUnderArrestException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SerialClient {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket(InetAddress.getLocalHost(), 1234);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            Building building;
            String type;

            String path = "serialResult.txt";

            String buildingsInfo = "/Users/karmikfeels/IdeaProjects/practice3/practice3/src/buildings.txt";
            String buildingsTypes = "/Users/karmikfeels/IdeaProjects/practice3/practice3/src/types.txt";

            FileWriter writer = new FileWriter(new File(path));
            Scanner scanner = new Scanner(new FileReader(buildingsTypes));
            BufferedReader reader = new BufferedReader(new FileReader(buildingsInfo));

            Object price;

            while (scanner.hasNext()){
                type = scanner.nextLine();
                System.out.println(type);

                switch (type) {
                    case "Dwelling":
                        Buildings.setBuildingFactory(new DwellingFactory());
                        break;
                    case "OfficeBuilding":
                        Buildings.setBuildingFactory(new OfficeFactory());
                        break;
                    case "Hotel":
                        Buildings.setBuildingFactory(new HotelFactory());
                        break;
                }

                dos.writeInt(1);
                building = Buildings.readBuilding(reader);
                System.out.println(building);
                Buildings.serializeBuilding(building, dos);

                price = new ObjectInputStream(dis).readObject();

                if(price instanceof Double){
                    System.out.println("Price: " + price + "\n");
                    writer.write(price + "\n");
                }

                if (price instanceof BuildingUnderArrestException){
                    System.out.println("Building is arrested!\n");
                    writer.write("Building is arrested!\n");
                }
            }

            dos.writeInt(0);
            writer.close();
            dos.flush();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
