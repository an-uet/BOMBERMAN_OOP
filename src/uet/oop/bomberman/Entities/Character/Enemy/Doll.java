package uet.oop.bomberman.Entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AILow;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AIMedium;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.SemiDynamic.Bomb;
import uet.oop.bomberman.Entities.SemiDynamic.Flame;
import uet.oop.bomberman.Entities.SemiDynamic.RayFlame;
import uet.oop.bomberman.Entities.Static.Wall;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Doll extends Enemy {
    // con nay ddi xuyen gach. di chuyen ngau nhien + van toc cham
    // ps: nhanh qua so bomber k tranh duoc.


    public Doll(double x, double y, Game game) {
        super(x, y, game);
        ai = new AIMedium(game.bomberman, this);
        speed = (double) Sprite.SCALED_SIZE /64;
        score = 100;
    }


    @Override
    public boolean canMove(int direction) {
        canMove = true;
        if (direction == 0) {
            List<Entity> entityList = game.getEntityAt(x, y - speed);
            for (Entity a : entityList) {
                if (a instanceof Wall) {
                    canMove = false;
                }
            }
        }
        if (direction == 1) {
            List<Entity> entityList = game.getEntityAt(x + speed, y);
            for (Entity a : entityList) {
                if (a instanceof Wall) {
                    canMove = false;
                }
            }
        }
        if (direction == 2) {
            List<Entity> entityList = game.getEntityAt(x, y + speed);
            for (Entity a : entityList) {
                if (a instanceof Wall) {
                    canMove = false;
                }
            }
        }
        if (direction == 3) {
            List<Entity> entityList = game.getEntityAt(x - speed, y);
            for (Entity a : entityList) {
                if (a instanceof Wall) {
                    canMove = false;
                }
            }
        }
        return canMove;
    }


    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, 60).getFxImage();
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, 60).getFxImage();
                break;
        }
    }


    public void render(GraphicsContext gc) {
        chooseSprite();
        if (isKilled()) {
            if (timeToDie > -30) {
                if (animate % 60 < 20) {
                    img = Sprite.doll_dead.getFxImage();
                } else if (animate % 60 < 40) {
                    img = Sprite.mob_dead2.getFxImage();
                } else {
                    img = Sprite.mob_dead3.getFxImage();
                }
                gc.drawImage(img, x, y);
            } else {
                remove();
                game.totalScore += score;// cong diem.
            }
            animate();
        } else {
            super.render(gc);
        }

    }


}


