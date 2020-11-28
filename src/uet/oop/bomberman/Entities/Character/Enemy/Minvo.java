package uet.oop.bomberman.Entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AILow;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.Static.Item.BombItem;
import uet.oop.bomberman.Entities.Static.Item.FlameItem;
import uet.oop.bomberman.Entities.Static.Item.SpeedItem;
import uet.oop.bomberman.Entities.Static.Portal;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.List;

import static uet.oop.bomberman.Game.totalScore;

public class Minvo extends Enemy {
    // di chuyển ngẫu nhiên. không xuyên Wall + Brick.
    // có thể ăn item.

    public Minvo(double x, double y, Game game) {
        super(x, y, game);
        ai = new AILow();
        img = Sprite.minvo_right2.getFxImage();
        speed = (double) Sprite.SCALED_SIZE / 128;
        score = 200;
    }


    // ăn item.
    public void collide() {
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
                } else if (((LayeredEntity) a).getTopEntity() instanceof BombItem) {
                    a.remove();
                    Sound.play("Item");
                } else if (((LayeredEntity) a).getTopEntity() instanceof FlameItem) {
                    a.remove();
                    Sound.play("Item");
                } else if (((LayeredEntity) a).getTopEntity() instanceof Portal) {
                    if (Game.enemies.isEmpty()) {
                        a.remove();
                        Sound.play("Item");
                    }
                }
            }
        }
    }


    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, 60).getFxImage();
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, 60).getFxImage();
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        if (isKilled()) {
            if (timeToDie > -30) {
                if (animate % 60 < 20) {
                    img = Sprite.minvo_dead.getFxImage();
                } else if (animate % 60 < 40) {
                    img = Sprite.mob_dead2.getFxImage();
                } else {
                    img = Sprite.mob_dead3.getFxImage();
                }
                gc.drawImage(img, x, y);
            } else {
                remove();
                totalScore += score;
            }
            animate();
        } else {
            super.render(gc);
        }

    }
}
