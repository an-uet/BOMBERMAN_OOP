package uet.oop.bomberman.Entities.Character.Enemy.AI;

import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.SemiDynamic.Bomb;
import uet.oop.bomberman.Entities.SemiDynamic.RayFlame;
import uet.oop.bomberman.Entities.Static.Grass;
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
    int direction = -1;

    public AIBomber(Game game) {
        this.game = game;
    }
    public int calculateDirection(){
        return bfs.calculateDirection();
    }

    public void move() {
        Game.bomberman.setSpeed((double) Sprite.SCALED_SIZE/32);
        Game.bomberman.setMAXTIME(16);
        if (!Game.enemies.isEmpty()) {
            bfs = new BFS(Game.bomberman, Game.enemies.get(0), game);
            if (Game.bomberman.getX() % 32 == 0 && Game.bomberman.getY() % 32 == 0) {
                direction = calculateDirection();
            }
            if (bfs.nextPointIsEnemy((int) Game.bomberman.getX(), (int) Game.bomberman.getY() + 64)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX(), (int) Game.bomberman.getY() - 64)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() - 64, (int) Game.bomberman.getY() + 64)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() - 64, (int) Game.bomberman.getY() - 64)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() + 64, (int) Game.bomberman.getY() + 64)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() + 64, (int) Game.bomberman.getY() - 64)
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() + 64, (int) Game.bomberman.getY())
                    || bfs.nextPointIsEnemy((int) Game.bomberman.getX() - 64, (int) Game.bomberman.getY())
            ) {
                Game.bomberman.setBomb();

            } else {
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
        } else {
            Entity entity = null;
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if (Game.mapChar[i][j] == 'x') {
                        entity = bfs.game.getEntityAt(j * 32, i * 32).get(0);
                    }
                }
            }
            bfs = new BFS(Game.bomberman, entity, game);
            if (Game.bomberman.getX() % 32 == 0 && Game.bomberman.getY() % 32 == 0) {
                direction = calculateDirection();
            }
        }
    }
}
