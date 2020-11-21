package uet.oop.bomberman.Entities.Static;

import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Game;

public abstract class Static extends Entity {
    protected Game game;
    public Static(double x, double y, Game game) {
        super(x, y, game);
    }

}
