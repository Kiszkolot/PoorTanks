package Classes;

import Classes.*;

import EnumClasses.MapDirection;
import WorldMap.WMap;

import java.util.*;

public class Symulation {
    private WMap worldMap;
    private int timeToSpownObstacle = 7;
    private int timetToSpownEnemy = 6;
    private int score = 0;
    private int multiplicator=1;
    private int timer = 0;
    
    public Symulation(WMap wmap){
        this.worldMap = wmap;
        wmap.setSymulation(this);
    }

    public Symulation(WMap mapa, int obstacleTime, int enemyTime, int multiplicator ){
        this.worldMap = mapa;
        mapa.setSymulation(this);
        this.timeToSpownObstacle = obstacleTime;
        this.timetToSpownEnemy = enemyTime;
        this.multiplicator = multiplicator;
    }

    public int getTimer(){
        return this.timer;
    }

    public int getResult(){
        return this.score;
    }

    public WMap getWorldMap() {
        return worldMap;
    }

    public void bulletsHit(){
        LinkedList<Bullet> pociski = this.getWorldMap().getBullets();
        Set<Vector2D> obstaclesPosition = this.getWorldMap().getObstaclesMap().keySet();
        LinkedList<Bullet> obstacleHittingBullets = new LinkedList<>();
        LinkedList<Obstacle> toHitObstacle = new LinkedList<>();
        LinkedList<Enemy> toHitEnemy = new LinkedList<>();
        LinkedList<Bullet> enemyHittingBullets = new LinkedList<>();
        LinkedList<Enemy> enemies = this.getWorldMap().getEnemies();
        LinkedList<Bullet> hitteHero = new LinkedList<>();
        for(Bullet pocisk: pociski){
            Vector2D position = pocisk.getPosition();
            if(this.getWorldMap().getHero().getTonk().getPosition().equals(position)){
                this.getWorldMap().getHero().getHit(pocisk.getDamage());
                hitteHero.add(pocisk);
                break;
            }
            for(Vector2D obsPosition: obstaclesPosition){
                if(obsPosition.equals(position)){
                    Obstacle hitted = (Obstacle) this.getWorldMap().getObstaclesMap().get(obsPosition);
                    toHitObstacle.add(hitted);
                    obstacleHittingBullets.add(pocisk);

                    break;
                }
            }
            for(Enemy en:enemies){
                if(en.getTonk().getPosition().equals(position)){
                    toHitEnemy.add(en);
                    enemyHittingBullets.add(pocisk);
                    this.getWorldMap().getBullets().remove(pocisk);
                    break;
                }
            }

        }
        for(Bullet shell: hitteHero){
            this.getWorldMap().getBullets().remove(shell);
        }

        for(int i=0; i< toHitObstacle.size();i++){
            toHitObstacle.get(i).getHit(obstacleHittingBullets.get(i).getDamage());
            this.getWorldMap().getBullets().remove(obstacleHittingBullets.get(i));
        }
        for(int i=0;i< toHitEnemy.size();i++){
            toHitEnemy.get(i).getHit(enemyHittingBullets.get(i).getDamage());
            this.score+=1*this.multiplicator;
            this.getWorldMap().getBullets().remove(enemyHittingBullets.get(i));
        }


        //dla enemy tez
    }

    public void moveTheBullets(){
        LinkedList<Bullet> pociski = this.getWorldMap().getBullets();
        for(Bullet pocisk: pociski){
            pocisk.setPosition(pocisk.getPosition().add(pocisk.getFacingVector()));
            //System.out.println(pocisk.getPosition().getX());
        }
        removeOutOfMapBullets();

    }

    public void removeOutOfMapBullets(){
        LinkedList<Bullet> pociski = this.getWorldMap().getBullets();
        for(Bullet pocisk: pociski){
            if(this.getWorldMap().getLowerLeft().subtract(new Vector2D(1,1)).follows(pocisk.getPosition())){
                pociski.remove(pocisk);
            }
            else if(this.getWorldMap().getUpperRight().add(new Vector2D(1,1)).precedes(pocisk.getPosition())){
                pociski.remove(pocisk);
            }
        }
    }

    public void runerrands(){
        this.timer+=1;
        this.bulletsHit();
        this.moveTheBullets();
        this.bulletsHit();
        if(this.getTimer()%this.timeToSpownObstacle==0){
            Vector2D localisation = this.getWorldMap().randomUnoccupiedPosition();
            this.getWorldMap().placeObstacle(new Obstacle(localisation,2));
        }
        if(this.getTimer()%this.timetToSpownEnemy==0){
            Vector2D localisation = this.getWorldMap().randomUnoccupiedPosition();
            Tonk etank = new Tonk(localisation,1, MapDirection.NORTH);
            Enemy e1 = new Enemy(etank);
            this.getWorldMap().placeEnemy(e1);
        }
        if(this.getWorldMap().getEnemies().isEmpty()){
            Vector2D localisation = this.getWorldMap().randomUnoccupiedPosition();
            Tonk etank = new Tonk(localisation,1, MapDirection.NORTH);
            Enemy e1 = new Enemy(etank);
            this.getWorldMap().placeEnemy(e1);
        }

        LinkedList<Enemy> enemies = this.getWorldMap().getEnemies();
        for(Enemy enemy:enemies){
            enemy.setHeading(enemy.altLocalize());
            Random rand = new Random();
            int choice = rand.nextInt(2);
            if(choice==1){
                Vector2D newpos = enemy.getTonk().getPosition().add(enemy.getTonk().getHeading().toUnitVector());
                if(this.getWorldMap().canMoveTo(newpos,enemy.getTonk())){
                    enemy.moveTo(newpos);
                }
            }
            else{
                enemy.getTonk().fireABullet();
            }
        }

        this.bulletsHit();

    }

}
