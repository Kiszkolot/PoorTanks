package SwingTests;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame(){
        super("Czołgi");
        // Rozmiar i lokalizacja
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.getHeight();
        screenSize.getWidth();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation((screenWidth - 720)/2,(screenHeight-480)/2);
        setSize(720,480);
        //Przyciski
        setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        add(new JButton("Naprzód"));
        add(new JButton("Ognia"));
        add(new JButton("Obrót wieży w Lewo"));
        add(new JButton("Obrót wieży w Prawo"));

        setVisible(true);
    }
}
