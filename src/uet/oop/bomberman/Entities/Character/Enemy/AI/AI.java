package uet.oop.bomberman.Entities.Character.Enemy.AI;

import uet.oop.bomberman.Game;

import java.util.Random;

public abstract class AI {
    protected Random random = new Random();
    public abstract int calculateDirection();
}
