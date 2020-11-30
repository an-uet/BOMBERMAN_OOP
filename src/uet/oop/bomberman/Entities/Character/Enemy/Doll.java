package uet.oop.bomberman.Entities.Character.Enemy;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AILow;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AIMedium;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.Static.Item.SpeedItem;
import uet.oop.bomberman.Entities.Static.Wall;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.awt.image.BufferedImage;
import java.util.List;

public class Doll extends Enemy {
    // con nay di xuyen Brick, Bomb + di chuyen ngau nhien + van toc cham.
    // ps: có thể ăn được item speed, sau khi ăn item thì speed*2.


    public Doll(double x, double y, Game game) {
        super(x, y, game);
        ai = new AILow();
        BufferedImage bufferedImage;
        img = Sprite.doll_left1.getFxImage();
        speed = (double) Sprite.SCALED_SIZE / 128;
        score = 200;
    }


    // ăn item Speed.
    @Override
    public void collide() {
        super.collide();
        List<Entity> entityList = game.getEntityAt(x, y);
        for (Entity a : entityList) {
            if (a instanceof LayeredEntity) {
                if (((LayeredEntity) a).getTopEntity() instanceof SpeedItem) {
                    if (direction == 0) {
                        y += speed;
                    } else if (direction == 1) {
                        x -= speed;
                    } else if (direction == 2) {
                        y -= speed;
                    } else {
                        x += speed;
                    }
                    speed *= 2;
                    a.remove();
                    Sound.play("Item");
                }
            }
        }
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


