package Classes;

import EnumClasses.MapDirection;
import Interfaces.IHittable;
import Interfaces.IMovable;
import Interfaces.IPlacable;
import WorldMap.WMap;
import Classes.*;

public class Tonk implements IPlacable, IHittable, IMovable {
    private Vector2D position;
    private int health;
    private MapDirection heading;
    private WMap worldMap;

    public Tonk(Vector2D position, int health, MapDirection heading){
        this.position = position;
        this.health = health;
        this.heading= heading;
    }

    public MapDirection getHeading(){
        return this.heading;
    }

    public boolean setWorldMap(WMap worldMap){
        this.worldMap = worldMap;
        return true;
    }

    public WMap getWorldMap(){
        return this.worldMap;
    }

    public void fireABullet(){
        Vector2D place = this.getHeading().toUnitVector().add(this.getPosition());
        //System.out.println("fired");
        //System.out.println(place.getX());
        Bullet przeciwpancernym = new Bullet(place,this.getHeading());
        this.getWorldMap().placeBullet(przeciwpancernym);
        przeciwpancernym.setWorldMap(this.getWorldMap());
    }

    public void setPosition(Vector2D position){
        this.position = position;
    }

    public void setHeading(MapDirection dir){
        this.heading=dir;
    }

    @Override
    public void getHit(int shell) { this.health-=shell; }

    @Override
    public void die() {
        //this.getWorldMap().
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public Vector2D getPosition() {
        return this.position;
    }

    @Override
    public boolean moveTo(Vector2D newPosition) {
        if(this.getWorldMap().canMoveTo(newPosition,this)){
            this.setPosition(newPosition);
            return true;
        }
        return false;
    }

    public void changeHeading(MapDirection newDirection){
        if(newDirection==MapDirection.NONEATALL){
            return;
        }
        else{
            this.heading = newDirection;
        }
    }

    public Vector2D getMapPlacement(){
      return new Vector2D( (this.getPosition().getX())*25, (this.getWorldMap().getUpperRight().getX()-this.getPosition().getY())*25);
        //return new Vector2D( (this.getPosition().getX())*25, this.getPosition().getY()*25);

    }
}
