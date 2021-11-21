package buildings.net.client;

import buildings.Building;
import buildings.Buildings;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BinatyClient {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("127.0.0.1", 0);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            Building building;
            String type;
            String result;

            String path = "result.txt";
            String buildingsInfo = "buildings.txt";
            String buildingsTypes = "types.txt";

            FileWriter writer = new FileWriter(new File(path));
            Scanner scanner = new Scanner(new FileReader(buildingsTypes));
            BufferedReader reader = new BufferedReader(new FileReader(buildingsInfo));

            while (scanner.hasNext()) {
                type = scanner.nextLine();
                System.out.println(type);
                Thread.sleep(1000);
                dos.writeUTF(type);
                building = Buildings.readBuilding(reader);
                Thread.sleep(1000);
                Buildings.outputBuilding(building, dos);
                result = dis.readUTF();
                writer.write(result + "\n");
                System.out.println(result);
            }

            writer.close();
            scanner.close();
            reader.close();
            dos.writeUTF("Exit");
            dos.flush();

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
