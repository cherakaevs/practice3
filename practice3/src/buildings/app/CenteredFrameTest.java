package buildings.app;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;

public class CenteredFrameTest {
    public static void main(String[] args) {
        CenteredFrame app = new CenteredFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}


class CenteredFrame extends JFrame{
    public CenteredFrame(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        setSize(width/2 , height/2);
        setLocation(width/4, height/4);
        setTitle("CenteredFrame");
    }
}