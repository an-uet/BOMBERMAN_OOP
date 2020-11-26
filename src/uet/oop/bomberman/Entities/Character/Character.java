package uet.oop.bomberman.Entities.Character;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.SemiDynamic.Bomb;
import uet.oop.bomberman.Entities.SemiDynamic.Brick;
import uet.oop.bomberman.Entities.Static.Wall;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public abstract class Character extends Entity {

    protected double speed;
    protected Game game = new Game();
    protected boolean canMove;
    protected int direction = -1 ; //hướng đi của thực thể

    public Character(double x, double y, Game game) {
        super(x, y, game);
    }

    protected int animate = 0;
    protected final int MAX_ANIMATE = 7500;

    protected void animate() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0;
        }
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean canMove(int direction) {
        canMove = true;
        if (direction == 0) {
            List<Entity> entityList = game.getEntityAt(x, y - speed);
            for (Entity a : entityList) {
                if ((a instanceof Wall || a instanceof Bomb) && y > a.getY()) {
                    canMove = false;
                } else if (a instanceof LayeredEntity && y > a.getY()) {
                    if (((LayeredEntity) a).getTopEntity() instanceof Brick) {
                        canMove = false;
                    }
                }
            }
        }
        if (direction == 1) {
            List<Entity> entityList = game.getEntityAt(x + speed, y);
            for (Entity a : entityList) {
                if ((a instanceof Wall || a instanceof Bomb) && x < a.getX()) {
                    canMove = false;
                } else if (a instanceof LayeredEntity && x < a.getX()) {
                    if (((LayeredEntity) a).getTopEntity() instanceof Brick) {
                        canMove = false;
                    }
                }
            }
        }
        if (direction == 2) {
            List<Entity> entityList = game.getEntityAt(x, y + speed);
            for (Entity a : entityList) {
                if ((a instanceof Wall || a instanceof Bomb) && y < a.getY()) {
                    canMove = false;
                } else if (a instanceof LayeredEntity && y < a.getY()) {
                    if (((LayeredEntity) a).getTopEntity() instanceof Brick) {
                        canMove = false;
                    }
                }
            }
        }
        if (direction == 3) {
            List<Entity> entityList = game.getEntityAt(x - speed, y);
            for (Entity a : entityList) {
                if ((a instanceof Wall || a instanceof Bomb) && x > a.getX()) {
                    canMove = false;
                } else if (a instanceof LayeredEntity && x > a.getX()) {
                    if (((LayeredEntity) a).getTopEntity() instanceof Brick) {
                        canMove = false;
                    }
                }
            }
        }
        if (isKilled()) {
            canMove = false;
        }
        return canMove;
    }

    public void update() {
        if (isKilled()) {
            timeToDie--;
        }
        collide();
    }
}
