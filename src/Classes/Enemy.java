package Classes;

import Classes.*;
import EnumClasses.MapDirection;
import WorldMap.WMap;

import static java.lang.Integer.min;

public class Enemy {
    private Tonk enemyTank;

    public Enemy (Tonk eTank){
        this.enemyTank = eTank;
    }

    public void getHit(int shell){
        this.getTonk().getHit(shell);
        if(this.getTonk().getHealth()<=0){
           this.getTonk().getWorldMap().removeEnemy(this);
        }
    }

    public Tonk getTonk(){
        return this.enemyTank;
    }

    public MapDirection locateHero(){
        Vector2D ourLocation = this.getTonk().getPosition();
        Vector2D heroLocation = this.getTonk().getWorldMap().getHero().getTonk().getPosition();
        Vector2D difference = heroLocation.subtract(ourLocation);
        int x = difference.getX();
        int y = difference.getY();
        if(x==y){
            if(x<0){
                return MapDirection.SOUTHWEST;
            }
            else{
                return MapDirection.NORTHEAST;
            }
        }
        else if(x==-y){
            if( x<0){
                return MapDirection.NORTHWEST;
            }
            else{
                return MapDirection.SOUTHEAST;
            }
        }
        else if (x==0){
            if(y>0){
                return MapDirection.NORTH;
            }
            else{
                return MapDirection.SOUTH;
            }
        }
        else if (y==0){
            if (x>0){
                return MapDirection.EAST;
            }
            else{
                return MapDirection.WEST;
            }
        }
        else{
            return MapDirection.NONEATALL;
        }
    }

    public void moveTo(Vector2D positon){
        if(this.getTonk().getWorldMap().canMoveTo(positon,this.getTonk())) {
            this.getTonk().getWorldMap().PositionChanged(this, positon);
            this.getTonk().moveTo(positon);
        }
    }

    public MapDirection altLocalize(){
        MapDirection dir = this.locateHero();
        if(dir==MapDirection.NONEATALL){
            int xDiff = this.getTonk().getPosition().getX()-this.getTonk().getWorldMap().getHero().getTonk().getPosition().getX();
            int yDiff = this.getTonk().getPosition().getY()-this.getTonk().getWorldMap().getHero().getTonk().getPosition().getY();
            int absDiff = xDiff-yDiff;
            int absSum = yDiff+xDiff;

            int absSquareXDiff = xDiff*xDiff;
            int absSquareYDiff = yDiff*yDiff;
            int absSquareAbsDiff = absDiff*absDiff;
            int absSquareAbsSum = absSum*absSum;

            int najman = min(min(absSquareAbsSum,absSquareXDiff), min(absSquareYDiff,absSquareAbsDiff));

            if (najman==absSquareXDiff){
                if(xDiff>0){
                    return MapDirection.WEST;
                }
                else{
                    return MapDirection.EAST;
                }
            }
            else if(najman==absSquareYDiff){
                if(yDiff>0){
                    return MapDirection.SOUTH;
                }
                else{
                    return MapDirection.NORTH;
                }
            }
            else if(najman==absSquareAbsDiff){
                if(absDiff>0){
                    return MapDirection.SOUTHWEST;
                }
                else{
                    return MapDirection.NORTHEAST;
                }
            }
            else{
                if(absSum>0){
                    return MapDirection.NORTHWEST;
                }
                else{
                    return MapDirection.SOUTHEAST;
                }
            }
        }
        else{
            return dir;
        }
    }

    public void setWorldMap(WMap wMap) {
        this.getTonk().setWorldMap(wMap);
    }

    public String getPictureFile(){
        MapDirection head = this.getTonk().getHeading();
        switch(head){
            case NORTH:
                return("D:\\Java\\Czołgi\\Graphicss\\Enemy-0.png");

            case NORTHEAST:
                return("D:\\Java\\Czołgi\\Graphicss\\Enemy-1.png");

            case EAST:
                return("D:\\Java\\Czołgi\\Graphicss\\Enemy-2.png");

            case SOUTHEAST:
                return("D:\\Java\\Czołgi\\Graphicss\\Enemy-3.png");

            case SOUTH:
                return("D:\\Java\\Czołgi\\Graphicss\\Enemy-4.png");

            case SOUTHWEST:
                return("D:\\Java\\Czołgi\\Graphicss\\Enemy-5.png");

            case WEST:
                return("D:\\Java\\Czołgi\\Graphicss\\Enemy-6.png");

            case NORTHWEST:
                return("D:\\Java\\Czołgi\\Graphicss\\Enemy-7.png");

            case NONEATALL:
                return("D:\\Java\\Czołgi\\Graphicss\\Enemy-5.png");

            default:
                throw new IllegalStateException("Unexpected value: " + head);
        }
    }

    public void setHeading(MapDirection loc) {
        this.getTonk().changeHeading( loc);
    }
}
