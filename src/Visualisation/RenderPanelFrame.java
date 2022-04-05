package Visualisation;
import Classes.Symulation;
import SwingTests.ObrazPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class RenderPanelFrame extends JFrame {

    public RenderPanelFrame(Symulation symulacja){
        super("Czołgi");
        JPanel renderPanel = new RenderPanel(symulacja);
        add(renderPanel);
        this.setLocationRelativeTo(null);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
