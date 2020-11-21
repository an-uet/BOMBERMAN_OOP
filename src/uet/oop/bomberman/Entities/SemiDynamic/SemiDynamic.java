package uet.oop.bomberman.Entities.SemiDynamic;

import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Game;

import java.util.List;

public abstract class SemiDynamic extends Entity {

    protected double timeToExplode = 120; //2 giay - thoi gian phat no

    protected int animate = 0;
    protected final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big

    public SemiDynamic(double xUnit, double yUnit, Game game) {
        super(xUnit, yUnit, game);
    }

    protected void animate() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0; //reset animation
        }
    }
    
    @Override
    public void update() {
        if (timeToExplode > 0) {
            timeToExplode--;
        } else {
            timeToDie--;
        }
    }
}
