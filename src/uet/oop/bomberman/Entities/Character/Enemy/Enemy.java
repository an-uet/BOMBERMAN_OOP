package uet.oop.bomberman.Entities.Character.Enemy;

import uet.oop.bomberman.Entities.Character.Character;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AI;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.SemiDynamic.Bomb;
import uet.oop.bomberman.Entities.SemiDynamic.Brick;
import uet.oop.bomberman.Entities.Static.Wall;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.List;

public abstract class Enemy extends Character {

    protected AI ai;
    protected int score;
    protected int timeSetFlame = 10;

    public Enemy(double x, double y, Game game) {
        super(x, y, game);

    }

    @Override
    public void collide() {
    }


    public void move() {
        int x = direction;
        if (!canMove) {
            x = ai.calculateDirection();
        }
        if (x == 0) {
            up();
        } else if (x == 2) {
            down();
        } else if (x == 3) {
            left();
        } else {
            right();
        }
    }


    public void right() {
        direction = 1;
        if (canMove(direction)) {
            x += speed;
        }
    }

    public void left() {
        direction = 3;
        if (canMove(direction)) {
            x -= speed;
        }
    }

    public void up() {
        direction = 0;
        if (canMove(direction)) {
            y -= speed;
        }
    }

    public void down() {
        direction = 2;
        if (canMove(direction)) {
            y += speed;
        }
    }


    public void update() {
        animate();
        if (isKilled()) {
            timeToDie--;
        }
        collide();
        move();

    }
}
