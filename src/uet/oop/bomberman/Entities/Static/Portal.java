package uet.oop.bomberman.Entities.Static;

import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Static {
    public Portal(double x, double y, Game game) {
        super(x, y, game);
        img = Sprite.portal.getFxImage();
    }


    public void collide() {

    }
    @Override
    public void update() {

    }
}
