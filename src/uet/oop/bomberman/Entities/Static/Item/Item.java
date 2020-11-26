package uet.oop.bomberman.Entities.Static.Item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.Static.Static;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;


public abstract class Item extends Static {
    public Item(double x, double y, Game game) {
        super(x, y, game);
    }

    @Override
    public void collide() {
    }
}
