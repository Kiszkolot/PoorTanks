package ClassesTests;

import Classes.*;
import EnumClasses.MapDirection;
import WorldMap.WMap;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Set;

public class WMapTest {

    @Test
    public void WMapBulletTest(){
        Vector2D first = new Vector2D(0,0);
        Vector2D second = new Vector2D(10,10);
        WMap mapa = new WMap(first, second);
        Symulation symulacja = new Symulation(mapa);
        Bullet pocisk1 = new Bullet(new Vector2D(0,0), MapDirection.EAST);
        Bullet pocisk2 = new Bullet(new Vector2D(3,3), MapDirection.NORTHEAST);
        symulacja.getWorldMap().placeBullet(pocisk1);
        symulacja.getWorldMap().placeBullet(pocisk2);
        LinkedList<Bullet> expected = new LinkedList<>();
        expected.add(pocisk1);
        expected.add(pocisk2);
        Assert.assertEquals(expected,symulacja.getWorldMap().getBullets());
    }

    @Test
    public void WMapBulletMovingTest(){
        Vector2D first = new Vector2D(0,0);
        Vector2D second = new Vector2D(10,10);
        WMap mapa = new WMap(first, second);
        Symulation symulacja = new Symulation(mapa);
        Bullet pocisk1 = new Bullet(new Vector2D(0,0), MapDirection.EAST);
        Bullet pocisk2 = new Bullet(new Vector2D(3,3), MapDirection.NORTHEAST);
        symulacja.getWorldMap().placeBullet( pocisk1);
        symulacja.getWorldMap().placeBullet( pocisk2);
        symulacja.moveTheBullets();
        symulacja.moveTheBullets();
        LinkedList<Bullet> moved = symulacja.getWorldMap().getBullets();
        Vector2D distance1 = pocisk1.getFacingVector();
        Vector2D distance2 = pocisk2.getFacingVector();
        distance1 = distance1.add(distance1);
        distance2 = distance2.add(distance2);
        Assert.assertTrue(pocisk1.getPosition().equals(distance1.add(new Vector2D(0,0))));
        Assert.assertTrue(pocisk2.getPosition().equals(distance2.add(new Vector2D(3,3))));
    }

    @Test
    public void WMapBulletCollideWithObstacle(){
        Vector2D first = new Vector2D(0,0);
        Vector2D second = new Vector2D(10,10);
        WMap mapa = new WMap(first, second);
        Symulation symulacja = new Symulation(mapa);

        Bullet pocisk1 = new Bullet(new Vector2D(0,0), MapDirection.EAST);
        symulacja.getWorldMap().placeBullet( pocisk1);

        Tonk herotank = new Tonk(new Vector2D(5,5),3,MapDirection.NORTH);
        Player gracz = new Player(herotank);
        mapa.setHero(gracz);
        herotank.setWorldMap(mapa);

        Obstacle przeszkoda = new Obstacle(new Vector2D(1,0),2);
        symulacja.getWorldMap().placeObstacle(przeszkoda);
        Assert.assertTrue(symulacja.getWorldMap().getObstaclesMap().containsValue(przeszkoda));
        
        symulacja.moveTheBullets();
        symulacja.bulletsHit();


        Assert.assertTrue(pocisk1.getPosition().equals(new Vector2D(1,0)));
        Assert.assertTrue(pocisk1.getPosition().equals(przeszkoda.getPosition()));
        Assert.assertEquals(1,przeszkoda.getHealth());
    }
    
    @Test
    public void WMapRemoveObjectAfterDyingTest(){
        Vector2D first = new Vector2D(0,0);
        Vector2D second = new Vector2D(10,10);
        WMap mapa = new WMap(first, second);
        Symulation symulacja = new Symulation(mapa);

        Tonk herotank = new Tonk(new Vector2D(5,5),3,MapDirection.NORTH);
        Player gracz = new Player(herotank);
        mapa.setHero(gracz);
        herotank.setWorldMap(mapa);

        Bullet pocisk1 = new Bullet(new Vector2D(0,0), MapDirection.EAST);
        symulacja.getWorldMap().placeBullet( pocisk1);
        Bullet pocisk2 = new Bullet(new Vector2D(1,0), MapDirection.EAST);
        symulacja.getWorldMap().placeBullet( pocisk2);

        Obstacle przeszkoda = new Obstacle(new Vector2D(2,0),2);
        symulacja.getWorldMap().placeObstacle(przeszkoda);
        przeszkoda.setWorldMap(mapa);
        Assert.assertTrue(symulacja.getWorldMap().getObstaclesMap().containsValue(przeszkoda));

        //System.out.println(przeszkoda.getHealth());
        symulacja.moveTheBullets();
        symulacja.bulletsHit();
        //System.out.println(przeszkoda.getHealth());
        symulacja.moveTheBullets();
        //System.out.println("moved");
        symulacja.bulletsHit();
        //System.out.println(przeszkoda.getHealth());

        Assert.assertTrue(symulacja.getWorldMap().getObstaclesMap().isEmpty());
    }
    
    @Test
    public void  WMapShootingTest(){
        Vector2D first = new Vector2D(0,0);
        Vector2D second = new Vector2D(10,10);
        WMap mapa = new WMap(first, second);
        Symulation symulacja = new Symulation(mapa);

        Tonk herotank = new Tonk(new Vector2D(5,5),3,MapDirection.NORTH);
        Player gracz = new Player(herotank);
        mapa.setHero(gracz);

        Obstacle przeszkoda = new Obstacle(new Vector2D(0,0),2);
        symulacja.getWorldMap().placeObstacle(przeszkoda);
        Assert.assertTrue(symulacja.getWorldMap().getObstaclesMap().containsValue(przeszkoda));

        gracz.getTonk().changeHeading(MapDirection.SOUTHWEST);
        gracz.getTonk().fireABullet();
        symulacja.moveTheBullets();
        gracz.getTonk().fireABullet();
        LinkedList<Bullet> bullets = symulacja.getWorldMap().getBullets();

        Assert.assertTrue(symulacja.getWorldMap().getBullets().size()==2);
        symulacja.moveTheBullets();

        symulacja.moveTheBullets();

        //Assert.assertTrue(symulacja.getWorldMap().getBullets().size()==1);
        symulacja.moveTheBullets();

        symulacja.bulletsHit();

        //System.out.println(przeszkoda.getHealth());
        symulacja.moveTheBullets();

        symulacja.bulletsHit();

        //System.out.println(przeszkoda.getHealth());
        Assert.assertTrue(symulacja.getWorldMap().getObstaclesMap().size()==0);
    }

    @Test
    public void WMapMovingTonksTest(){
        Vector2D first = new Vector2D(0,0);
        Vector2D second = new Vector2D(10,10);
        WMap mapa = new WMap(first, second);
        Symulation symulacja = new Symulation(mapa);

        Tonk herotank = new Tonk(new Vector2D(5,5),3,MapDirection.NORTH);
        Player gracz = new Player(herotank);
        mapa.setHero(gracz);
        
        Tonk enemy1 = new Tonk(new Vector2D(2,2),1,MapDirection.NORTH);
        Enemy en1 = new Enemy(enemy1);
        //System.out.println("a");
        mapa.placeEnemy(en1);
        //System.out.println("a");
        symulacja.getWorldMap().getHero().moveTo(new Vector2D(6,6));
        //System.out.println("a");
        //System.out.println(symulacja.getWorldMap().getHero().getTonk().getPosition().getX());
        LinkedList<Enemy> enemies = symulacja.getWorldMap().getEnemies();
        for(Enemy en:enemies){
            en.moveTo(new Vector2D(3,3));
        }
        Assert.assertTrue(symulacja.getWorldMap().getHero().getTonk().getPosition().equals(new Vector2D(6,6)));
        Assert.assertTrue(en1.getTonk().getPosition().equals(new Vector2D(3,3)));
    }

    @Test
    public void WMapMovingIntoImpossiblePlaceTest(){
        Vector2D first = new Vector2D(0,0);
        Vector2D second = new Vector2D(10,10);
        WMap mapa = new WMap(first, second);
        Symulation symulacja = new Symulation(mapa);

        Tonk herotank = new Tonk(new Vector2D(5,5),3,MapDirection.NORTH);
        Player gracz = new Player(herotank);
        mapa.setHero(gracz);

        Tonk enemy1 = new Tonk(new Vector2D(3,3),1,MapDirection.NORTH);
        Enemy en1 = new Enemy(enemy1);
        mapa.placeEnemy(en1);
        //tu powinno być inaczej
        Assert.assertTrue(symulacja.getWorldMap().getEnemies().size()==0);
    }
    @Test
    public void WMapOutOfBoundaryMove(){
        Vector2D first = new Vector2D(0,0);
        Vector2D second = new Vector2D(10,10);
        WMap mapa = new WMap(first, second);
        Symulation symulacja = new Symulation(mapa);

        Tonk herotank = new Tonk(new Vector2D(9,9),3,MapDirection.NORTH);
        Player gracz = new Player(herotank);
        mapa.setHero(gracz);

        mapa.getHero().moveTo(new Vector2D(10,10));
        mapa.getHero().moveTo(new Vector2D(11,11));
        Assert.assertTrue(mapa.getHero().getTonk().getPosition().equals(new Vector2D(10,10)));
    }
    @Test
    public void WMapAtopPlacementOrOutOfBoundPlacement(){
        Vector2D first = new Vector2D(0,0);
        Vector2D second = new Vector2D(10,10);
        WMap mapa = new WMap(first, second);
        Symulation symulacja = new Symulation(mapa);

        Tonk herotank = new Tonk(new Vector2D(9,9),3,MapDirection.NORTH);
        Player gracz = new Player(herotank);
        mapa.setHero(gracz);

        Tonk enemy1 = new Tonk(new Vector2D(3,3),1,MapDirection.NORTH);
        Enemy en1 = new Enemy(enemy1);
        mapa.placeEnemy(en1);

        Tonk enemy2 = new Tonk(new Vector2D(3,3),1,MapDirection.NORTH);
        Enemy en2 = new Enemy(enemy2);
        mapa.placeEnemy(en2);

        Obstacle przeszkoda = new Obstacle(new Vector2D(0,0),2);
        symulacja.getWorldMap().placeObstacle(przeszkoda);

        Obstacle przeszkoda1 = new Obstacle(new Vector2D(0,0),2);
        symulacja.getWorldMap().placeObstacle(przeszkoda1);

        Assert.assertTrue(symulacja.getWorldMap().getEnemies().size()==1);
        Assert.assertTrue(symulacja.getWorldMap().getObstaclesMap().size()==1);

    }

    @Test
    public void WMapTrackingHeroTest(){
        Vector2D first = new Vector2D(0,0);
        Vector2D second = new Vector2D(10,10);
        WMap mapa = new WMap(first, second);
        Symulation symulacja = new Symulation(mapa);

        Tonk herotank = new Tonk(new Vector2D(5,5),3,MapDirection.NORTH);
        Player gracz = new Player(herotank);
        mapa.setHero(gracz);

        Tonk enemy1 = new Tonk(new Vector2D(2,2),1,MapDirection.NORTH);
        Enemy en1 = new Enemy(enemy1);
        mapa.placeEnemy(en1);

        Tonk enemy2 = new Tonk(new Vector2D(0,7),1,MapDirection.NORTH);
        Enemy en2 = new Enemy(enemy2);
        mapa.placeEnemy(en2);

        en1.getTonk().setHeading(en1.altLocalize());
        en2.getTonk().setHeading(en2.altLocalize());

        Assert.assertTrue(en1.getTonk().getHeading()==MapDirection.NORTHEAST);
        Assert.assertTrue(en2.getTonk().getHeading()==MapDirection.SOUTH);
    }


}
