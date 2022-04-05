package Visualisation;

import javax.swing.*;

public class AltMenu {
    private JFrame menuFrame;

    public AltMenu(){
        this.menuFrame = new JFrame("Tanks ");
        this.menuFrame.setSize(300, 300);
        this.menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.menuFrame.setLocationRelativeTo(null);
    }

    public void startSimulation(){
        menuFrame.add(new AltIntroWindow());
        menuFrame.setVisible(true);
    }

    public static void main(String[] args){
        AltMenu nowe = new AltMenu();
        nowe.startSimulation();
    }
}
