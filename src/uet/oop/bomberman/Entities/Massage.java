package uet.oop.bomberman.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;

public class Massage extends Entity {
    public Massage(double x, double y, Image image, Game game) {
        super(x, y, game);
        img = image;
    }


    public void collide() {

    }
    @Override
    public void update() {

    }
}
