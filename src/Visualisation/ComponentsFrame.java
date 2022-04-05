package Visualisation;
import Classes.Symulation;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ComponentsFrame extends JFrame{
    public ComponentsFrame(Symulation sym){
        JPanel nowy = new DrawComponents(sym);
        add(nowy);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
