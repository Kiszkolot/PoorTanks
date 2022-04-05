package Classes;

import Classes.*;

public class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){ return this.x;  }

    public int getY(){
        return this.y;
    }

    public boolean precedes(Vector2D other) {
        return other.x >= this.x && other.y >= this.y;
    }

    public boolean follows(Vector2D other) {
        return other.x <= this.x && other.y <= this.y;
    }

    public Vector2D upperRight(Vector2D other) {
        int m = Math.max(this.x, other.x);
        int n = Math.max(this.y, other.y);
        return new Vector2D(m, n);
    }

    public Vector2D lowerLeft(Vector2D other) {
        int m = Math.min(this.x, other.x);
        int n = Math.min(this.y, other.y);
        return new Vector2D(m, n);
    }

    public Vector2D add(Vector2D other) {
        int m, n;
        m = this.x + other.x;
        n = this.y + other.y;
        return new Vector2D(m, n);
    }

    public Vector2D subtract(Vector2D other) {
        int m, n;
        m = this.x - other.x;
        n = this.y - other.y;
        return new Vector2D(m, n);
    }

    public boolean equals(Vector2D vec){
        return this.getX()==vec.getX() && this.getY()==vec.getY();
    }

    public Vector2D opposite() {
        return new Vector2D((-1) * this.x, (-1) * this.y);
    }

    public boolean inArea(Vector2D bound1, Vector2D bound2){
        return (this.precedes(bound1) && this.follows(bound2));
    }



    @Override
    public int hashCode() {
        int hash = 17;
        hash += this.getX()*31;
        hash += this.getY()*13;
        return hash;
    }
}

