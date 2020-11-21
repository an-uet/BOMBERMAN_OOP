package uet.oop.bomberman.Entities.Character.Enemy.AI;

import java.util.Random;

public class AILow extends AI {
    @Override
    public int calculateDirection() {
        return random.nextInt(4);
    }
}
