package uet.oop.bomberman.Entities.Static.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {
    public FlameItem(double x, double y, Game game) {
        super(x, y, game);
        img = Sprite.powerup_flames.getFxImage();
    }

    @Override
    public void update() {

    }
}
