package buildings.app;

import buildings.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BuildingApp {

    static JFrame mainFrame = createFrame();

    static Building building;

    static JPanel root = new JPanel();
    static JPanel buildingPanel = new JPanel();
    static JPanel floorsPanel = new JPanel();
    static JPanel spacesPanel = new JPanel();
    static JPanel jPanel4 = new JPanel();

    static Label buildingType = new Label("Building Type: ");
    static Label buildingFloors = new Label("Number of Floors: ");
    static Label buildingSquare = new Label("General Square: ");


    public static void main(String[] args) {
        mainFrame.add(root);
        planPanel();
        mainFrame.setJMenuBar(getMenu());
        mainFrame.revalidate();
    }

    public static JFrame createFrame(){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int width = dimension.width;
        int height = dimension.height;

        frame.setSize(width, height);
        frame.setTitle("Building App");

        return frame;
    }

    public static JMenuBar getMenu(){
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu look = new JMenu("Look & Feel");

        menu.add(file);
        menu.add(look);

        JMenuItem dwelling = new JMenuItem("Open Dwelling");
        JMenuItem office = new JMenuItem("Open Office Building");
        JMenuItem hotel = new JMenuItem("Open Hotel");

        ButtonGroup group = new ButtonGroup();

        for (UIManager.LookAndFeelInfo lookAndFeelInfo : UIManager.getInstalledLookAndFeels()) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(lookAndFeelInfo.getName());

            item.addActionListener(click -> {
                try {
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    root.repaint();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
            });
            group.add(item);
            look.add(item);
        }

        file.add(dwelling).addActionListener(new OpenBuilding(new DwellingFactory()));
        file.addSeparator();
        file.add(office).addActionListener(new OpenBuilding(new DwellingFactory()));
        file.addSeparator();
        file.add(hotel).addActionListener(new OpenBuilding(new HotelFactory()));
        file.addSeparator();

        file.add(new JMenuItem("Exit")).addActionListener(e -> System.exit(0));

        return menu;
    }

    static void planPanel(){

        buildingPanel.setLayout(new BoxLayout(buildingPanel,BoxLayout.Y_AXIS));
        floorsPanel.setLayout(new BoxLayout(floorsPanel,BoxLayout.Y_AXIS));
        spacesPanel.setLayout(new BoxLayout(spacesPanel,BoxLayout.Y_AXIS));
        jPanel4.setLayout(new BoxLayout(jPanel4,BoxLayout.Y_AXIS));

        buildingPanel.setBorder(BorderFactory.createEtchedBorder());
        floorsPanel.setBorder(BorderFactory.createEtchedBorder());
        spacesPanel.setBorder(BorderFactory.createEtchedBorder());
        jPanel4.setBorder(BorderFactory.createEtchedBorder());

        //1 Panel
        buildingPanel.add(new Label("Building", 1));
        buildingPanel.add(buildingType);
        buildingPanel.add(buildingFloors);
        buildingPanel.add(buildingSquare);

        //2 Panel
        floorsPanel.add(new Label("Floor", 1));
        floorsPanel.add(new Label("Floor Number: "));
        floorsPanel.add(new Label("Spaces Number: "));
        floorsPanel.add(new Label("General Square: "));

        //3 Panel
        spacesPanel.add(new Label("Space", 1));
        spacesPanel.add(new Label("Space Number: "));
        spacesPanel.add(new Label("Rooms Number: "));
        spacesPanel.add(new Label("Square: "));

        jPanel4.add(new Label("Building Plane: "));
        root.setLayout(new BoxLayout(root, BoxLayout.X_AXIS));
        root.add(buildingPanel);
        root.add(floorsPanel);
        root.add(spacesPanel);
        root.add(jPanel4);
    }


    static void updateBuildingPanel(){
        buildingPanel.removeAll();

        buildingPanel.add(new Label("Building", 1));

        buildingType.setText("Building Type: " + building.getClass().getSimpleName());
        buildingFloors.setText("Number of Floors: " + building.getFloorsNum());
        buildingSquare.setText("General Square: " + building.getSumSquare());

        buildingPanel.add(buildingType);
        buildingPanel.add(buildingFloors);
        buildingPanel.add(buildingSquare);

        buildingPanel.repaint();
        buildingPanel.revalidate();
    }

    static void updateFloorsPanel(Floor floor, int num){
        floorsPanel.removeAll();

        floorsPanel.add(new Label("Floor", 1));
        floorsPanel.add(new Label("Floor Number: " + num));
        floorsPanel.add(new Label("Spaces Number: " + floor.getSpacesNum()));
        floorsPanel.add(new Label("General Square: " + floor.getSumSquare()));

        floorsPanel.repaint();
        floorsPanel.revalidate();
    }

    static void updateSpacesPanel(Space space, int num){
        spacesPanel.removeAll();

        spacesPanel.add(new Label("Space",1 ));

        spacesPanel.add(new Label("Space Number: " + num));
        spacesPanel.add(new Label("Rooms Number: " + space.getRoomsNum()));
        spacesPanel.add(new Label("Square: " + space.getSquare()));

        spacesPanel.repaint();
        spacesPanel.revalidate();
    }


    static void updatePlanPanel(){
        root.removeAll();
        jPanel4.removeAll();
        updateBuildingPanel();
        System.out.println("Update Plan");
        for(int i = 0; i < building.getFloorsNum(); ++i){
            JPanel floorPanel = new JPanel();
            floorPanel.setBorder(BorderFactory.createEtchedBorder());
            floorPanel.add(new Label("Floor #" + i));
            int finalI1 = i;
            floorPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updateFloorsPanel(building.getFloor(finalI1), finalI1);
                }
            });
            for(int j = 0; j < building.getFloor(i).getSpacesNum(); ++j){
                JButton jButton = new JButton("Space #" + j);
                int finalI = i;
                int finalJ = j;
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updateSpacesPanel(building.getFloor(finalI).getSpace(finalJ), finalJ);
                    }
                });
                floorPanel.add(jButton);
            }
            jPanel4.add(floorPanel);
        }
        JScrollPane scrollPane = new JScrollPane(jPanel4, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        root.add(scrollPane);
        root.add(buildingPanel);
        root.add(floorsPanel);
        root.add(spacesPanel);
        mainFrame.revalidate();
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
                    return "txt file";
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
                JOptionPane.showMessageDialog(root,
                        "Can't read building from that file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                e.printStackTrace();
            }

            building = Buildings.readBuilding(reader);
            if(building == null){
                JOptionPane.showMessageDialog(root,
                        "Can't read building from that file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("Read complete");

            updatePlanPanel();
            updateBuildingPanel();
        }
    }
}

