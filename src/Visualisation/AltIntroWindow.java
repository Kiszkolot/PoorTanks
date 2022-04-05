package Visualisation;

import Classes.Player;
import Classes.Symulation;
import Classes.Tonk;
import Classes.Vector2D;
import EnumClasses.MapDirection;
import WorldMap.WMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltIntroWindow extends JPanel implements ActionListener {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 300;

    private JTextField mapSize;
    private JTextField level;

    private JLabel mapSizeLabel;
    private JLabel levelLabel;

    private JButton startButton;

    public AltIntroWindow(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        startButton = new JButton("Start Game");
        startButton.addActionListener(this);

        mapSizeLabel = new JLabel("Podaj wielkość mapy");
        levelLabel = new JLabel("Podaj poziom trudności 1/2/3");

        mapSize = new JTextField();
        mapSize.setColumns(3);
        mapSize.setText("10");

        level = new JTextField();
        level.setColumns(3);
        level.setText("1");

        mapSizeLabel.setLabelFor(mapSize);
        levelLabel.setLabelFor(level);

        JPanel mapSizePanel = new JPanel();
        JPanel levelPanel = new JPanel();
        
        mapSizePanel.add(mapSizeLabel);
        levelPanel.add(levelLabel);

        mapSizePanel.add(mapSize);
        levelPanel.add(level);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        add(new JLabel("Ustawienia"));
        add(mapSizePanel);
        add(levelPanel);
        add(buttonPanel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int size = Integer.parseInt(mapSize.getText());
        int lev = Integer.parseInt(level.getText());
        WMap mapa = new WMap(new Vector2D(0,0), new Vector2D(size,size));
        Tonk hero = new Tonk(new Vector2D(size/2,size/2),3, MapDirection.NORTH);
        Player pl = new Player(hero);
        mapa.setHero(pl);
        if(lev ==1){
            Symulation symulacja = new Symulation(mapa,6,7,1);
            new RenderPanelFrame(symulacja);
        }
        else if(lev ==2){
            Symulation symulacja = new Symulation(mapa,4,5,2);
            new RenderPanelFrame(symulacja);
        }
        else{
            Symulation symulacja = new Symulation(mapa,3,3,3);
            new RenderPanelFrame(symulacja);
        }



    }

}
