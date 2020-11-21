package uet.oop.bomberman.Entities.SemiDynamic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends SemiDynamic {


    public Flame flameUp = new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - 1, 0, game);
    public Flame flameDown = new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + 1, 1, game);
    public Flame flameLeft = new Flame(x / Sprite.SCALED_SIZE - 1, y / Sprite.SCALED_SIZE, 2, game);
    public Flame flameRight = new Flame(x / Sprite.SCALED_SIZE + 1, y / Sprite.SCALED_SIZE, 3, game);
    public Bomb(double x, double y, Game game) {
        super(x, y, game);
        img = Sprite.bomb.getFxImage();
    }

    public void collide() {
        flameDown.collide();
        flameLeft.collide();
        flameRight.collide();
        flameUp.collide();
    }

    public void render(GraphicsContext gc) {
        if (timeToExplode > 0) {
            if (animate % 60 < 20) {
                img = Sprite.bomb_2.getFxImage();
            } else if (animate % 60 < 40) {
                img = Sprite.bomb_1.getFxImage();
            } else {
                img = Sprite.bomb.getFxImage();
            }
            gc.drawImage(img, x, y);
        } else {
            if (timeToDie > 0) {
                if (animate % 30 < 10) {
                    img = Sprite.bomb_exploded.getFxImage();
                } else if (animate % 30 < 20) {
                    img = Sprite.bomb_exploded1.getFxImage();
                } else {
                    img = Sprite.bomb_exploded2.getFxImage();
                }
                gc.drawImage(img, x, y);
            } else {
                remove();
            }
        }
        flameUp.render(gc);
        flameDown.render(gc);
        flameLeft.render(gc);
        flameRight.render(gc);
        animate();
    }

    @Override
    public void update() {
        super.update();
        flameUp.update();
        flameDown.update();
        flameLeft.update();
        flameRight.update();
        collide();
    }
}
