package uet.oop.bomberman.Entities.Static.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
    public BombItem(double x, double y, Game game) {
        super(x, y, game);
        img = Sprite.powerup_bombs.getFxImage();
    }

    public void collide() {

    }
    @Override
    public void update() {

    }
}
