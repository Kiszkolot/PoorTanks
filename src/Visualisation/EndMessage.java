package Visualisation;

import javax.swing.*;

public class EndMessage {
    private JFrame endFrame;

    public EndMessage(){
        this.endFrame = new JFrame("Tanks ");
        this.endFrame.setSize(300, 300);
        this.endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.endFrame.setLocationRelativeTo(null);
    }

    public void Goodbye(int value){
        endFrame.add(new EndMessageWindow(value));
        endFrame.setVisible(true);
    }

    public static void main(String[] args){
        EndMessage endmess = new EndMessage();
        endmess.Goodbye(100);
    }

}
