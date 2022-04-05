package Visualisation;

import javax.swing.*;
import java.awt.*;

public class EndMessageWindow extends JPanel  {
    private static final int HEIGHT = 300;
    private static final int WIDTH = 300;

    private JTextField wynik;

    private JLabel wynikLabel;
    private JLabel endlabel;


    public EndMessageWindow(int value){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        wynikLabel = new JLabel("Zdobyte punkty:");
        endlabel = new JLabel("Do zobaczenia ponownie!");

        wynik = new JTextField();
        wynik.setColumns(10);
        wynik.setText(Integer.toString(value));

        wynikLabel.setLabelFor(wynik);

        JPanel wynikLabelPanel = new JPanel();
        JPanel endLabelPanel = new JPanel();

        wynikLabelPanel.add(wynikLabel);
        wynikLabelPanel.add(wynik);
        endLabelPanel.add(endlabel);

        add(new JLabel("Koniec Gry"));
        add(wynikLabelPanel);
        add(endLabelPanel);
    }


}
