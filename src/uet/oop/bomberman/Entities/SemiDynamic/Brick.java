package uet.oop.bomberman.Entities.SemiDynamic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends SemiDynamic {

    public Brick(double x, double y, Game game) {
        super(x, y, game);
        img = Sprite.brick.getFxImage();
    }

    public void collide() {

    }

    public void afterKill() {
        if (timeToDie > 0) {
            if (animate % 30 < 10) {
                img = Sprite.brick_exploded.getFxImage();
            } else if (animate % 30 < 20) {
                img = Sprite.brick_exploded1.getFxImage();
            } else {
                img = Sprite.brick_exploded2.getFxImage();
            }
        } else {
            remove();
        }
        animate();
    }

    public void update() {
        if (isKilled()) {
            timeToDie--;
            afterKill();
        }
    }
}
