package Visualisation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class IntroWindow extends JFrame implements ActionListener {

    static JTextField tekst;

    static JFrame frame;

    static JButton button;

    static JLabel label;

    public IntroWindow(){
    }

    public static void main(String[] args){
        frame = new JFrame("Podaj wielkość pola bitwy");
        label = new JLabel("Podaj liczbę w zakresie 6 wzwyż, ale nie za dużą");
        button = new JButton("Potwierdź");
        IntroWindow intro = new IntroWindow();
        
        button.addActionListener(intro);

        tekst = new JTextField(10);

        JPanel panel = new JPanel();

        panel.add(tekst);
        panel.add(button);
        panel.add(label);
        frame.add(panel);
        frame.setSize(300,300);
        frame.show();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        //label.setText(tekst.getText());
        System.out.println(Integer.parseInt(tekst.getText())+1);
        //tekst.setText("  ");
    }
}
