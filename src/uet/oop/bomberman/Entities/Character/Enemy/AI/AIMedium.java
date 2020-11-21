package uet.oop.bomberman.Entities.Character.Enemy.AI;

import uet.oop.bomberman.Entities.Character.Bomber;
import uet.oop.bomberman.Entities.Character.Enemy.Enemy;

public class AIMedium extends AI {
    Enemy _e;
    Bomber _bomber;

    public AIMedium(Bomber bomber, Enemy e) {
        _bomber = bomber;
        _e = e;
    }



    @Override
    public int calculateDirection() {
        int result;
        // TODO: cài đặt thuật toán tìm đường đi
        if (_bomber == null) {
            result = random.nextInt(4);
        } else {
            int vertical = random.nextInt(2);
            if (vertical == 1) {
                int v = calculateRowDirection();
                if (v != -1) {
                    result = v;
                } else {
                    result = calculateColDirection();
                }

            } else {
                int h = calculateColDirection();
                if (h != -1) {
                    result = h;
                } else {
                    result = calculateRowDirection();
                }
            }
        }

        return result;
    }

    protected int calculateColDirection() {
        if(_bomber.getX() < _e.getX())
            return 3;
        else if(_bomber.getX() > _e.getX())
            return 1;

        return -1;
    }

    protected int calculateRowDirection() {
        if(_bomber.getY() < _e.getY())
            return 0;
        else if(_bomber.getY() > _e.getY())
            return 2;
        return -1;
    }
}
