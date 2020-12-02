package uet.oop.bomberman.Entities.Character.Enemy.AI;

import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.SemiDynamic.Bomb;
import uet.oop.bomberman.Entities.SemiDynamic.RayFlame;
import uet.oop.bomberman.Entities.Static.Grass;
import uet.oop.bomberman.Entities.Static.Portal;
import uet.oop.bomberman.Entities.Static.Wall;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.HEIGHT;
import static uet.oop.bomberman.BombermanGame.WIDTH;

public class AIBomber extends AI{
    public Game game;
    public BFS bfs;
    public BFS1 bfs1;
    int direction = -1;

    public AIBomber(Game game) {
        this.game = game;
    }
    public int calculateDirection(){
        if (Game.enemies.isEmpty()) {
            return bfs1.calculateDirection();
        }
        return bfs.calculateDirection();
    }

    public void reset(Game game) {
        this.game = game;
    }
    public void move() {
        Game.bomberman.setSpeed((double) Sprite.SCALED_SIZE/32);
        Game.bomberman.setMAXTIME(16);
        if (!Game.enemies.isEmpty()) {
            bfs = new BFS(Game.bomberman, Game.enemies.get(0), game);
            if (Game.bomberman.getX() % 32 == 0 && Game.bomberman.getY() % 32 == 0) {
                direction = calculateDirection();
            }
            if (bfs.nextPointIsEnemy((int) Game.bomberman.getX(), (int) Game.bomberman.getY() + 32)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX(), (int) Game.bomberman.getY() - 32)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() - 32, (int) Game.bomberman.getY() + 32)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() - 32, (int) Game.bomberman.getY() - 32)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() + 32, (int) Game.bomberman.getY() + 32)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() + 32, (int) Game.bomberman.getY() - 32)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() + 32, (int) Game.bomberman.getY())
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() - 32, (int) Game.bomberman.getY())
            ) {
                Game.bomberman.setBomb();
                if (bfs.nextPointIsBomb((int) Game.bomberman.getX(), (int) Game.bomberman.getY())) {
                    if (direction == 0) {
                        direction = 2;
                    } else if (direction == 1) {
                        direction = 3;
                    } else if (direction == 2) {
                        direction = 0;
                    } else {
                        direction = 1;
                    }

                }
                if (!Game.bomberman.canMove(direction)) {
                    direction = (direction + 1) % 4;
                }
            }

            if (!Game.bombs.isEmpty()) {
                bfs = new BFS(Game.bomberman, Game.stillObjects.get((int) Game.bomberman.getY()), game);
                direction = bfs.calculateDirection();
                //if (Game.bomberman.getX() % 32 == 0 && Game.bomberman.getY() % 32 == 0) {
                    //direction = bfs.calculateDirection2();
                //}
                if (!Game.bomberman.canMove(direction)) {
                    direction = (direction + 1) % 4;
                }
            }
            if (direction == 0) {
                Game.bomberman.moveUp();
            } else if (direction == 1) {
                Game.bomberman.moveRight();
            } else if (direction == 2) {
                Game.bomberman.moveDown();
            } else {
                Game.bomberman.moveLeft();
            }

        } else {
            Portal portal = Game.portals.get(0);
            bfs1 = new BFS1(Game.bomberman, portal, game);
            direction = calculateDirection();
            if (bfs1.nextPointIsBrick((int) Math.round(Game.bomberman.getX()), (int) Math.round(Game.bomberman.getY()))) {
                Game.bomberman.setBomb();
                bfs1 = new BFS1(Game.bomberman, Game.bombs.get(0), game);
                direction = bfs1.calculateDirection2();
            }
            if (!Game.bomberman.canMove(direction)) {
                direction = (direction + 1) % 4;
            }
            if (direction == 0) {
                Game.bomberman.moveUp();
            } else if (direction == 1) {
                Game.bomberman.moveRight();
            } else if (direction == 2) {
                Game.bomberman.moveDown();
            } else {
                Game.bomberman.moveLeft();
            }
        }
    }
}
