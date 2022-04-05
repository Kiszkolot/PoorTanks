package Interfaces;

import Classes.Enemy;
import Classes.Vector2D;

public interface IMoveObserver {

    void PositionChanged(Enemy obj, Vector2D newPos);
}
