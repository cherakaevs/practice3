package buildings.app;

import buildings.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BuildingApp {

    static JFrame mainFrame = createFrame();

    static JPanel root = new JPanel();


    public static void main(String[] args) {
        mainFrame.setJMenuBar(getMenu());
        mainFrame.revalidate();
    }

    public static JFrame createFrame(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = dimension.width/2;
        int height = dimension.height/2;

        frame.setSize(width, height);
        frame.setTitle("Building App");

        return frame;
    }

    public static JMenuBar getMenu(){
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu look = new JMenu("Look & Feel");

        menu.add(file, look);

        JMenuItem dwelling = new JMenuItem("Open Dwelling");
        JMenuItem office = new JMenuItem("Open Office Building");
        JMenuItem hotel = new JMenuItem("Open Hotel");

        file.add(dwelling).addActionListener(new OpenBuilding(new DwellingFactory()));
        file.add(office).addActionListener(new OpenBuilding(new DwellingFactory()));
        file.add(hotel).addActionListener(new OpenBuilding(new HotelFactory()));

        return menu;
    }

    static class OpenBuilding extends AbstractAction{
        private BuildingFactory factory;

        public OpenBuilding(BuildingFactory factory){
            this.factory = factory;
        }

        public void actionPerformed(ActionEvent a){
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new File("/Users/karmikfeels/IdeaProjects/practice3/practice3/src/buildings/testBuildings"));
            jFileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if(f.getName().endsWith("txt")){
                        return true;
                    }
                    return false;
                }

                @Override
                public String getDescription() {
                    return "TXT files only";
                }
            });

            jFileChooser.showOpenDialog(mainFrame);
            File file = jFileChooser.getSelectedFile();
            Buildings.setBuildingFactory(factory);

            FileReader reader = null;
            try {
                reader = new FileReader(file);
            }
            catch (IOException e){
                e.printStackTrace();
            }

            Building building = Buildings.readBuilding(reader);
            if(building == null){
                JOptionPane.showMessageDialog(root,
                        "Can't read building from that file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("Read complete");
        }
    }
}

