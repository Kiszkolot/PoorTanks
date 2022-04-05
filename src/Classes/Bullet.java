package Classes;

import EnumClasses.MapDirection;
import Interfaces.IMovable;
import Interfaces.IPlacable;
import WorldMap.WMap;
import Classes.*;

import static EnumClasses.MapDirection.*;
import static EnumClasses.MapDirection.NORTH;
import static EnumClasses.MapDirection.NORTHEAST;

public class Bullet implements IPlacable, IMovable {
    private Vector2D position;
    private MapDirection heading;
    private int damage;
    private int multiplayer;
    private WMap worldMap;

    public Bullet (Vector2D position, MapDirection heading){
        this.position = position;
        this.heading = heading;
        this.damage = 1;
        this.multiplayer =1;
    }

    //Settery
    public boolean setWorldMap(WMap worldmap){
        this.worldMap = worldmap;
        return true;
    }

    public void setPosition(Vector2D position){
        this.position = position;
    }

    //Gettery
    public WMap getWorldMap() {
        return worldMap;
    }

    public MapDirection getHeading(){
        return this.heading;
    }

    public int getDamage(){
        return this.damage*this.multiplayer;
    }

    public Vector2D getFacingVector(){
        return this.heading.toUnitVector();
    }

    @Override
    public Vector2D getPosition() {
        return this.position;
    }

    //Funkcje własne
    @Override
    public boolean moveTo(Vector2D newPosition) {
        if(this.getWorldMap().canMoveToBulet(newPosition)){
            this.setPosition(newPosition);
            return true;
        }
        return false;
    }

    public String getPictureFile(){
       MapDirection head = this.getHeading();
       switch(head){
           case NORTH:
               return("D:\\Java\\Czołgi\\Graphicss\\Bullet-0.png");

           case NORTHEAST:
               return("D:\\Java\\Czołgi\\Graphicss\\Bullet-1.png");

           case EAST:
               return("D:\\Java\\Czołgi\\Graphicss\\Bullet-2.png");

           case SOUTHEAST:
               return("D:\\Java\\Czołgi\\Graphicss\\Bullet-3.png");

           case SOUTH:
               return("D:\\Java\\Czołgi\\Graphicss\\Bullet-4.png");

           case SOUTHWEST:
               return("D:\\Java\\Czołgi\\Graphicss\\Bullet-5.png");

           case WEST:
               return("D:\\Java\\Czołgi\\Graphicss\\Bullet-6.png");

           case NORTHWEST:
               return("D:\\Java\\Czołgi\\Graphicss\\Bullet-7.png");

           case NONEATALL:
               return("D:\\Java\\Czołgi\\Graphicss\\Bullet-5.png");

           default:
               throw new IllegalStateException("Unexpected value: " + head);
       }
    }

    public Vector2D getMapPlacement(){
        return new Vector2D( (this.getPosition().getX())*25, (this.getWorldMap().getUpperRight().getX()-this.getPosition().getY())*25);
        //return new Vector2D( (this.getPosition().getX())*25, this.getPosition().getY()*25);

    }
}
