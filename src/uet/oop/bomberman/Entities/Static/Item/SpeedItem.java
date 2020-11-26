package uet.oop.bomberman.Entities.Static.Item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {

    public SpeedItem(double x, double y, Game game) {
        super(x, y, game);
        img = Sprite.powerup_speed.getFxImage();
    }

    @Override
    public void update() {
        collide();
    }

}
