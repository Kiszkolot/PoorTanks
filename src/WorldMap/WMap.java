package WorldMap;
import Classes.*;
import EnumClasses.MapDirection;
import Interfaces.IMoveObserver;

import java.util.*;

public class WMap implements IMoveObserver {

    private Vector2D upperRight;
    private Vector2D lowerLeft;
    private Player hero;
    private Symulation symulacja;

    private HashMap<Vector2D, Enemy> enemyMap = new HashMap<>();
    private LinkedList<Enemy> enemies = new LinkedList<>();
    private LinkedList<Bullet> bullets = new LinkedList<>();
    private HashMap<Vector2D, Obstacle> obstacles = new HashMap<>();

    public WMap(Vector2D lowerLeft, Vector2D upperRight){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    //Gettery

    public Symulation getSymulation(){
        return this.symulacja;
    }

    public Player getHero(){
        return this.hero;
    }

    public HashMap getEnemiesMap(){
        return this.enemyMap;
    }

    public LinkedList<Enemy> getEnemies() {return this.enemies;}

    public HashMap getObstaclesMap(){
        return this.obstacles;
    }

    public Vector2D getLowerLeft() { return lowerLeft;}

    public Vector2D getUpperRight() { return upperRight; }

    public LinkedList<Bullet> getBullets() {return this.bullets;}

    //Settery

    public void setSymulation(Symulation our){
        this.symulacja = our;
    }

    public void setHero(Player pl){
        this.hero = pl;
        pl.getTonk().setWorldMap(this);
    }

    // Can do
    private boolean canPlace(Vector2D place){
        if(this.getHero().getTonk().getPosition().equals(place)){
            //System.out.println("fail");
            return false;
        }
        //System.out.println("passes");
        if(this.getHero().safeZone(place)){
            //**System.out.println("tu");
            //System.out.println("fail");
            return false;
        }
        //System.out.println("passes");
        LinkedList<Enemy> enemies = this.getEnemies();
        for(Enemy enemy:enemies){
            if(enemy.getTonk().getPosition().equals(place)){
                //System.out.println("fail");
                return false;
            }
        }
        //System.out.println("passes");
        Collection<Obstacle> obstacles =  this.getObstaclesMap().values();
        Iterator<Obstacle> iter = obstacles.iterator();
        while (iter.hasNext()){
            if(iter.next().getPosition().equals(place)){
                return false;
            }
        }

        //System.out.println("passed!");
        return true;
    }

    public boolean canMoveToBulet(Vector2D position){
        return position.precedes(this.getUpperRight()) && position.follows(this.getLowerLeft());
    }

    public boolean canMoveTo(Vector2D position, Tonk obj){
        if(position.precedes(this.getUpperRight()) && position.follows(this.getLowerLeft())){
            if(this.canMoveBoundary(position)){
                return true;
            }
            else {
                //System.out.println("tu");
                return false;
            }
        }
        else{
            //jeśli będzie odbijanie to tu będzie zaimplementowane
            return false;
        }
    }

    public boolean canMoveBoundary(Vector2D position){

        LinkedList<Enemy> enemies = this.getEnemies();
        Vector2D heroPos = this.getHero().getTonk().getPosition();
        Collection<Obstacle> obstacles =  this.getObstaclesMap().values();
        Iterator<Obstacle> iter = obstacles.iterator();
        while (iter.hasNext()){
            if(iter.next().getPosition().equals(position)){
                return false;
            }
        }
        for(Enemy enemy: enemies){
            if(enemy.getTonk().getPosition().equals(position)){
                return false;
            }
        }
        if(heroPos.equals(position)){
            return false;
        }
        return true;
    }


    // Place
    public boolean placeObstacle(Obstacle obs) {
        if (this.canPlace(obs.getPosition())) {
            this.obstacles.put(obs.getPosition(),obs);
            obs.setWorldMap(this);
            return true;
        }
        else {

            return false;
        }
    }

    public boolean placeEnemy(Enemy en){
        if (this.canPlace(en.getTonk().getPosition())) {
            this.enemyMap.put(en.getTonk().getPosition(),en);
            this.enemies.add(en);
            en.setWorldMap(this);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean placeBullet(Bullet shell){
        this.bullets.add(shell);
        shell.setWorldMap(this);
        return true;
    }

    public boolean placeObject(Object obj){
        if(obj instanceof Obstacle){
            //System.out.println("new obstacle");
            return this.placeObstacle((Obstacle) obj);

        }
        else if(obj instanceof Enemy){
            //System.out.println("new enemy");
            return this.placeEnemy((Enemy) obj);
        }
        else if(obj instanceof Bullet){
            //System.out.println("new bullet");
            this.bullets.add((Bullet) obj);
            return true;
        }
        else {
            return false;
        }
    }

    //Remove

    public boolean removeObject(Object obj){
        if(obj instanceof Bullet){
            return this.removeBullet((Bullet)obj);
        }
        else if(obj instanceof Enemy){
            return this.removeEnemy((Enemy)obj);
        }
        else if(obj instanceof Obstacle){
            return this.removeObstacle((Obstacle)obj);
        }
        else{
            return this.removeHero();
        }

    }

    private boolean removeHero() {
        //kod końca gry
        return true;
    }

    public boolean removeObstacle(Obstacle obs) {

        if(this.getObstaclesMap().containsValue(obs)){
            Vector2D position = obs.getPosition();
            this.getObstaclesMap().remove(position);
            return true;
       }
       return false;
    }

    public boolean removeEnemy(Enemy en) {
        for(Enemy ene: this.getEnemies()){
            if(ene.getTonk().getPosition().equals(en.getTonk().getPosition())){
                this.getEnemies().remove(ene);
                this.getEnemiesMap().remove(ene.getTonk().getPosition());
                return true;
            }
        }
        return false;
    }

    private boolean removeBullet(Bullet bullet) {
        for(Bullet shell: this.getBullets()){
            if(bullet.getPosition().equals(shell.getPosition()) && (bullet.getHeading()==shell.getHeading())){
                this.getBullets().remove(shell);
                return true;
            }
        }
        return false;
    }

    public Vector2D randomUnoccupiedPosition(){
        Random rand = new Random();
        int maxBound = this.getUpperRight().getX()+1;
        int x = rand.nextInt(maxBound);
        int y = rand.nextInt(maxBound);
        while(!this.canPlace(new Vector2D(x,y))){
            x = rand.nextInt(maxBound);
            y = rand.nextInt(maxBound);
        }
        return new Vector2D(x,y);
    }


    @Override
    public void PositionChanged(Enemy obj, Vector2D newpos) {
        this.getEnemiesMap().remove(obj.getTonk().getPosition());
        this.getEnemiesMap().put(obj,newpos);
    }

    public static void main(String[] args){
        WMap mapa = new WMap(new Vector2D(0,0), new Vector2D(10,10));
        Tonk tank = new Tonk(new Vector2D(5,5),3, MapDirection.NORTH);
        Player hero = new Player(tank);
        mapa.setHero(hero);

        Vector2D first = mapa.randomUnoccupiedPosition();
        System.out.println(first.getX());
        System.out.println(first.getY());

        Vector2D first1 = mapa.randomUnoccupiedPosition();
        System.out.println(first1.getX());
        System.out.println(first1.getY());
    }
}
