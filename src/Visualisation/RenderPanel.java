package Visualisation;

import Classes.*;
import SwingTests.ObrazPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenderPanel extends JPanel implements ActionListener {
    private Symulation symulation;
    private int size;
    private JButton moveButton;
    private JButton shootButton;
    private JButton LTurnButton;
    private JButton RTurnButton;
    private JPanel grafiki;

    public RenderPanel(Symulation symulation){
        this.symulation = symulation;
        this.size = this.getSymulation().getWorldMap().getUpperRight().getX();

        moveButton = new JButton("Naprzód");
        shootButton = new JButton("Ognia!");
        LTurnButton = new JButton("Obrót w Lewo");
        RTurnButton = new JButton("Obrót w Prawo");


        moveButton.addActionListener(this);
        shootButton.addActionListener(this);
        LTurnButton.addActionListener(this);
        RTurnButton.addActionListener(this);

        //grafiki = new ObrazPanel();
        //add(grafiki);

        grafiki = new DrawComponents(symulation);
        add(grafiki);

        GroupLayout lay = new GroupLayout(this);
        lay.setAutoCreateGaps(true);
        lay.setAutoCreateContainerGaps(true);


        lay.setHorizontalGroup(
                lay.createSequentialGroup()
                        .addComponent(grafiki)
                        .addGroup(lay.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(moveButton)
                                .addComponent(shootButton)
                                .addComponent(LTurnButton)
                                .addComponent(RTurnButton)
                        )
        );

        lay.setVerticalGroup(
                lay.createSequentialGroup()
                        .addComponent(grafiki)
                        .addGroup(lay.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(moveButton)
                                .addComponent(shootButton)
                                .addComponent(LTurnButton)
                                .addComponent(RTurnButton)
                        )
        );
        //add(moveButton);
        // add(shootButton);
        //add(LTurnButton);
        //add(RTurnButton);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source==moveButton){
            Player hero = this.getSymulation().getWorldMap().getHero();
            Vector2D newposition = hero.getTonk().getPosition().add(hero.getTonk().getHeading().toUnitVector());
            if(this.getSymulation().getWorldMap().canMoveTo(newposition, hero.getTonk())){
                this.getSymulation().getWorldMap().getHero().moveTo(newposition);
                grafiki.repaint();
                this.getSymulation().runerrands();
                grafiki.repaint();

            }

        }

        else if(source == shootButton){
            Player hero = this.getSymulation().getWorldMap().getHero();
            hero.getTonk().fireABullet();
            grafiki.repaint();
            this.getSymulation().runerrands();
            grafiki.repaint();
        }
        else if(source == LTurnButton){
            Player hero = this.getSymulation().getWorldMap().getHero();
            hero.getTonk().setHeading(hero.getTonk().getHeading().previous());
            grafiki.repaint();
        }
        else if(source==RTurnButton){
            Player hero = this.getSymulation().getWorldMap().getHero();
            hero.getTonk().setHeading(hero.getTonk().getHeading().next());
            grafiki.repaint();
        }

    }

    public Symulation getSymulation(){
        return this.symulation;
    }
}
