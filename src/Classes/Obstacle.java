package Classes;

import Interfaces.IHittable;
import Interfaces.IPlacable;
import WorldMap.WMap;
import Classes.*;

public class Obstacle implements IPlacable, IHittable {
    private Vector2D position;
    private int health;
    private WMap worldMap;


    public Obstacle(Vector2D position, int health){
        this.position = position;
        this.health = health;
    }

    public WMap getWorldMap() {
        return worldMap;
    }

    public boolean setWorldMap(WMap world){
        this.worldMap = world;
        return true;
    }

    @Override
    public Vector2D getPosition() {
        return this.position;
    }

    @Override
    public void getHit(int shell) {
        this.health-= shell;
        if(this.getHealth()<=0){
            this.getWorldMap().removeObstacle(this);
        }
    }

    @Override
    public void die() {

    }

    @Override
    public int getHealth() {
        return this.health;
    }

    public Vector2D getMapPlacement(){
        return new Vector2D((this.getWorldMap().getUpperRight().getX()-this.getPosition().getX())*25, this.getPosition().getY()*25);
    }

    public String getPictureFile() {
        if(this.getHealth()==2){
            return("D:\\Java\\Czołgi\\Graphicss\\Obstacle-2.png");
        }
        else if(this.getHealth()==1){
            return("D:\\Java\\Czołgi\\Graphicss\\Obstacle-1.png");
        }
        else{
            return("D:\\Java\\Czołgi\\Graphicss\\MapFloor.png");
        }
    }
}
