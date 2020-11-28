package uet.oop.bomberman.Entities.Character.Enemy.AI;

import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Entity;

public class AIMedium extends AI {
    Enemy enemy;
    Entity entity;

    public AIMedium(Entity entity, Enemy e) {
        enemy = e;
        this.entity = entity;
    }


    @Override
    public int calculateDirection() {
        int result;
        int temp = random.nextInt(2);
        if (temp == 1) {
            int v = calculateRow();
            if (v != -1) {
                result = v;
            } else {
                result = calculateCol();
            }

        } else {
            int h = calculateCol();
            if (h != -1) {
                result = h;
            } else {
                result = calculateRow();
            }
        }
        return result;
    }

    protected int calculateCol() {
        if (entity.getX() < enemy.getX())
            return 3;
        else if (entity.getX() > enemy.getX())
            return 1;

        return -1;
    }

    protected int calculateRow() {
        if (entity.getY() < enemy.getY())
            return 0;
        else if (entity.getY() > enemy.getY())
            return 2;
        return -1;
    }
}
