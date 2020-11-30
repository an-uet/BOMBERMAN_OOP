package uet.oop.bomberman.Entities.Character.Enemy.AI;

public class Point {
    int x;
    int y;
    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point obj) {
        if (this.x == obj.x && this.y == obj.y) {
            return true;
        } else return false;
    }
}
