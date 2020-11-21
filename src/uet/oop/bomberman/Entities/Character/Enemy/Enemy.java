package uet.oop.bomberman.Entities.Character.Enemy;

import uet.oop.bomberman.Entities.Character.Character;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AI;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

public abstract class Enemy extends Character {

    protected AI ai;
    protected int score;

    public Enemy(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void collide() {
    }


    public void move() {
        if (!isKilled()) {
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
    }


    public void right() {
        canMove = true;
        direction = 1;
        for (Entity a : Game.wallAndBrick) {
            if (Math.floor(x / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE == a.getX()
                    && Math.ceil(y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE == a.getY()) {
                if (a.getClass().toString().contains("Wall") || a.getClass().toString().contains("Brick")) {
                    canMove = false;
                }
            }
        }
        if (canMove) {
            x += speed;
        }
    }

    public void left() {
        canMove = true;
        direction = 3;
        for (Entity a : Game.wallAndBrick) {
            if (Math.ceil(x / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE == a.getX()
                    && Math.ceil(y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE == a.getY()) {
                if (a.getClass().toString().contains("Wall") || a.getClass().toString().contains("Brick")) {
                    canMove = false;
                }

            }
        }
        if (canMove) {
            x -= speed;
        }
    }

    public void up() {
        canMove = true;
        direction = 0;
        for (Entity a : Game.wallAndBrick) {
            if (Math.round(x / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE == a.getX()
                    && Math.ceil(y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE == a.getY()) {
                if (a.getClass().toString().contains("Wall") || a.getClass().toString().contains("Brick")) {
                    canMove = false;
                }

            }
        }
        if (canMove) {
            y -= speed;
        }
    }

    public void down() {
        direction = 2;
        canMove = true;
        for (Entity a : Game.wallAndBrick) {
            if (Math.round(x / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE == a.getX()
                    && Math.floor(y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE + Sprite.SCALED_SIZE == a.getY()) {
                if (a.getClass().toString().contains("Wall") || a.getClass().toString().contains("Brick")) {
                    canMove = false;
                }
            }
        }
        if (canMove) {
            y += speed;
        }
    }


    public void update() {
        animate();
        if (isKilled()) {
            timeToDie--;
        }
        collide();
    }
}
