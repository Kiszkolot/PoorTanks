package SwingTests;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ObrazPanel extends JPanel {
    private BufferedImage image;

    public ObrazPanel() {
        super();

        File imageFile = new File("D:\\Java\\Czołgi\\Graphicss\\Bullet-0.png");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }

        Dimension dimension = new Dimension(image.getWidth()*12, image.getHeight()*12);

        setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
        g2d.drawImage(image, 25,0, this);
        g2d.drawImage(image, 0,25, this);
        g2d.drawImage(image, 25,0, this);
    }

}
