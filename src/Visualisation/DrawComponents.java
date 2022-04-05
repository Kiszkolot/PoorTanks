package Visualisation;

import Classes.*;
import EnumClasses.MapDirection;
import SwingTests.ObrazPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class DrawComponents extends JPanel {
    private Symulation symulacja;

    private BufferedImage ground;
    private BufferedImage obstacle1;
    private BufferedImage obstacle2;
    private BufferedImage hero0;
    private BufferedImage hero1;
    private BufferedImage hero2;
    private BufferedImage hero3;
    private BufferedImage hero4;
    private BufferedImage hero5;
    private BufferedImage hero6;
    private BufferedImage hero7;
    private BufferedImage enemy0;
    private BufferedImage enemy1;
    private BufferedImage enemy2;
    private BufferedImage enemy3;
    private BufferedImage enemy4;
    private BufferedImage enemy5;
    private BufferedImage enemy6;
    private BufferedImage enemy7;
    private BufferedImage bullet0;
    private BufferedImage bullet1;
    private BufferedImage bullet2;
    private BufferedImage bullet3;
    private BufferedImage bullet4;
    private BufferedImage bullet5;
    private BufferedImage bullet6;
    private BufferedImage bullet7;

    public Symulation getSymulation() {
        return this.symulacja;
    }

    public DrawComponents(Symulation sym) {
        this.symulacja = sym;
        loadComponents();
    }

    private void loadComponents() {
        try {

            ground = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\MapFloor.png"));

            obstacle1 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Obstacle-1.png"));
            obstacle2 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Obstacle-2.png"));

            hero0 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Hero-0.png"));
            hero1 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Hero-1.png"));
            hero2 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Hero-2.png"));
            hero3 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Hero-3.png"));
            hero4 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Hero-4.png"));
            hero5 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Hero-5.png"));
            hero6 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Hero-6.png"));
            hero7 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Hero-7.png"));

            enemy0 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Enemy-0.png"));
            enemy1 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Enemy-1.png"));
            enemy2 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Enemy-2.png"));
            enemy3 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Enemy-3.png"));
            enemy4 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Enemy-4.png"));
            enemy5 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Enemy-5.png"));
            enemy6 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Enemy-6.png"));
            enemy7 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Enemy-7.png"));

            bullet0 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Bullet-0.png"));
            bullet1 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Bullet-1.png"));
            bullet2 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Bullet-2.png"));
            bullet3 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Bullet-3.png"));
            bullet4 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Bullet-4.png"));
            bullet5 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Bullet-5.png"));
            bullet6 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Bullet-6.png"));
            bullet7 = ImageIO.read(new File("D:\\Java\\Czołgi\\Graphicss\\Bullet-7.png"));

        } catch (IOException ex) {

            JOptionPane.showMessageDialog(this,
                    "Could not load images", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        int num = this.getSymulation().getWorldMap().getUpperRight().getX();
        Dimension dimension = new Dimension(bullet0.getWidth()*num, bullet0.getHeight()*num);

        setPreferredSize(dimension);
    }

    private void doGraphics(Graphics g) {
        Symulation sym = this.getSymulation();
        Graphics2D g2d = (Graphics2D) g;
        int mapSize = sym.getWorldMap().getUpperRight().getX();

        //paint floor
        for (int i = 0; i <= mapSize; i++) {
            for (int j = 0; j <= mapSize; j++) {
                g2d.drawImage(ground, (mapSize - i) * 25, j * 25, this);
            }
        }

        //paint Obstacles

        Collection<Obstacle> obstacles = sym.getWorldMap().getObstaclesMap().values();
        Iterator<Obstacle> iter = obstacles.iterator();
        while (iter.hasNext()) {
            Obstacle nekkst = iter.next();
            Vector2D position = nekkst.getPosition();
            if (nekkst.getHealth() == 2) {
                g2d.drawImage(obstacle2,  ( position.getX()) * 25, (mapSize - position.getY()) * 25, this);
            } else if (nekkst.getHealth() == 1) {
                g2d.drawImage(obstacle1, (position.getX()) * 25, (mapSize - position.getY()) * 25, this);
            } else {
                g2d.drawImage(ground, ( position.getX()) * 25, (mapSize - position.getY()) * 25, this);
            }
        }

        //bullets
        LinkedList<Bullet> bullets = sym.getWorldMap().getBullets();
        for (Bullet shell : bullets) {
            Vector2D placement = shell.getMapPlacement();
            MapDirection heading = shell.getHeading();
            switch (heading) {
                case NORTH:
                    g2d.drawImage(bullet0, placement.getX(), placement.getY(), this);
                    break;
                case NORTHEAST:
                    g2d.drawImage(bullet1, placement.getX(), placement.getY(), this);
                    break;
                case EAST:
                    g2d.drawImage(bullet2, placement.getX(), placement.getY(), this);
                    break;
                case SOUTHEAST:
                    g2d.drawImage(bullet3, placement.getX(), placement.getY(), this);
                    break;
                case SOUTH:
                    g2d.drawImage(bullet4, placement.getX(), placement.getY(), this);
                    break;
                case SOUTHWEST:
                    g2d.drawImage(bullet5, placement.getX(), placement.getY(), this);
                    break;
                case WEST:
                    g2d.drawImage(bullet6, placement.getX(), placement.getY(), this);
                    break;
                case NORTHWEST:
                    g2d.drawImage(bullet7, placement.getX(), placement.getY(), this);
                    break;
                case NONEATALL:
                    g2d.drawImage(bullet0, placement.getX(), placement.getY(), this);
                    break;
            }
        }



        //Paint tanks

        //hero

        Tonk heroTank = sym.getWorldMap().getHero().getTonk();
        Vector2D heropos = heroTank.getMapPlacement();
        MapDirection heroheading = heroTank.getHeading();
        switch (heroheading) {
            case NORTH:
                g2d.drawImage(hero0, heropos.getX(), heropos.getY(), this);
                break;
            case NORTHEAST:
                g2d.drawImage(hero1, heropos.getX(), heropos.getY(), this);
                break;
            case EAST:
                g2d.drawImage(hero2, heropos.getX(), heropos.getY(), this);
                break;
            case SOUTHEAST:
                g2d.drawImage(hero3, heropos.getX(), heropos.getY(), this);
                break;
            case SOUTH:
                g2d.drawImage(hero4, heropos.getX(), heropos.getY(), this);
                break;
            case SOUTHWEST:
                g2d.drawImage(hero5, heropos.getX(), heropos.getY(), this);
                break;
            case WEST:
                g2d.drawImage(hero6, heropos.getX(), heropos.getY(), this);
                break;
            case NORTHWEST:
                g2d.drawImage(hero7, heropos.getX(), heropos.getY(), this);
                break;
            case NONEATALL:
                g2d.drawImage(hero0, heropos.getX(), heropos.getY(), this);
                break;
        }

        //enemies

        LinkedList<Enemy> enemies = sym.getWorldMap().getEnemies();
        for (Enemy enemy : enemies) {
            Vector2D enemypos = enemy.getTonk().getMapPlacement();
            MapDirection enemyheading = enemy.getTonk().getHeading();
            switch (enemyheading) {
                case NORTH:
                    g2d.drawImage(enemy0, enemypos.getX(), enemypos.getY(), this);
                    break;
                case NORTHEAST:
                    g2d.drawImage(enemy1, enemypos.getX(), enemypos.getY(), this);
                    break;
                case EAST:
                    g2d.drawImage(enemy2, enemypos.getX(), enemypos.getY(), this);
                    break;
                case SOUTHEAST:
                    g2d.drawImage(enemy3, enemypos.getX(), enemypos.getY(), this);
                    break;
                case SOUTH:
                    g2d.drawImage(enemy4, enemypos.getX(), enemypos.getY(), this);
                    break;
                case SOUTHWEST:
                    g2d.drawImage(enemy5, enemypos.getX(), enemypos.getY(), this);
                    break;
                case WEST:
                    g2d.drawImage(enemy6, enemypos.getX(), enemypos.getY(), this);
                    break;
                case NORTHWEST:
                    g2d.drawImage(enemy7, enemypos.getX(), enemypos.getY(), this);
                    break;
                case NONEATALL:
                    g2d.drawImage(enemy0, enemypos.getX(), enemypos.getY(), this);
                    break;
            }
        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doGraphics(g);
    }
}


