
package uet.oop.bomberman.Entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AIMedium;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
    //con nay di cham. co the di xuyen tuong.
    public Kondoria(double x, double y, Game game) {
        super(x, y, game);
        speed = (double) Sprite.SCALED_SIZE / 256;
        ai = new AIMedium(Game.bomberman, this);
        score = 200;
        img = Sprite.kondoria_right1.getFxImage();
    }

    public void move() {
        int d = direction;
        if (!canMove) {
            d = ai.calculateDirection();
        }
        if (d == 0) {
            y -= speed;
        } else if (d == 2) {
            y += speed;
        } else if (d == 3) {
            x -= speed;
        } else {
            x += speed;
        }
    }


    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, 60).getFxImage();

                break;
            case 2:
            case 3:

                img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, 60).getFxImage();
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        if (isKilled()) {
            if (timeToDie > -30) {
                if (animate % 60 < 20) {
                    img = Sprite.kondoria_dead.getFxImage();
                } else if (animate % 60 < 40) {
                    img = Sprite.mob_dead2.getFxImage();
                } else {
                    img = Sprite.mob_dead3.getFxImage();
                }
                gc.drawImage(img, x, y);
            } else {
                remove();
                game.totalScore += score; // cong diem.
            }
            animate();
        } else {
            super.render(gc);
        }

    }


}