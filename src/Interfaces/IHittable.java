package Interfaces;

import Classes.Bullet;

public interface IHittable{

    void getHit(int damage);

    //to be implemented
    void die();

    int getHealth();

}
