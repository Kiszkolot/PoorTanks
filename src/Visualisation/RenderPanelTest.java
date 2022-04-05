package Visualisation;

import Classes.*;
import EnumClasses.MapDirection;
import SwingTests.ObrazFrame;
import WorldMap.WMap;

import java.awt.EventQueue;

public class RenderPanelTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                WMap mapa = new WMap(new Vector2D(0,0), new Vector2D(10,10));
                Tonk hero = new Tonk(new Vector2D(5,5),3, MapDirection.NORTH);
                Player pl = new Player(hero);
                mapa.setHero(pl);
                Symulation symulacja = new Symulation(mapa);
                new RenderPanelFrame(symulacja);
            }
        });
    }

}
