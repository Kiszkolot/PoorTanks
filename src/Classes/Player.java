package Classes;

import Classes.*;
import EnumClasses.MapDirection;
import Visualisation.EndMessage;

public class Player {
    private Tonk RudyTheTank;

    public Player(Tonk tank){
        this.RudyTheTank = tank;
    }

    public void getHit(int shell){
        this.RudyTheTank.getHit(shell);
        if(this.getTonk().getHealth()<=0){
            EndMessage endmess = new EndMessage();
            endmess.Goodbye(this.getTonk().getWorldMap().getSymulation().getResult());
        }
    }

    public Tonk getTonk(){
        return this.RudyTheTank;
    }

    public boolean safeZone(Vector2D invader){
        Vector2D ourpos = this.getTonk().getPosition();
        Vector2D distance = new Vector2D(2,2);
        Vector2D ll = ourpos.subtract(distance);
        //System.out.println(ll.getX());
        Vector2D ur = distance.add(ourpos);
        return invader.inArea(ur, ll);
    }

    public void moveTo(Vector2D newPosition){
        this.getTonk().moveTo(newPosition);
    }

    public String getPictureFile(){
        MapDirection head = this.getTonk().getHeading();
        switch(head){
            case NORTH:
                return("D:\\Java\\Czołgi\\Graphicss\\Hero-0.png");

            case NORTHEAST:
                return("D:\\Java\\Czołgi\\Graphicss\\Hero-1.png");

            case EAST:
                return("D:\\Java\\Czołgi\\Graphicss\\Hero-2.png");

            case SOUTHEAST:
                return("D:\\Java\\Czołgi\\Graphicss\\Hero-3.png");

            case SOUTH:
                return("D:\\Java\\Czołgi\\Graphicss\\Hero-4.png");

            case SOUTHWEST:
                return("D:\\Java\\Czołgi\\Graphicss\\Hero-5.png");

            case WEST:
                return("D:\\Java\\Czołgi\\Graphicss\\Hero-6.png");

            case NORTHWEST:
                return("D:\\Java\\Czołgi\\Graphicss\\Hero-7.png");

            case NONEATALL:
                return("D:\\Java\\Czołgi\\Graphicss\\Hero-5.png");

            default:
                throw new IllegalStateException("Unexpected value: " + head);
        }
    }
}
