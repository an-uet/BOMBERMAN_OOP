package uet.oop.bomberman.Entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.Character.Character;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AILow;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.Game.totalScore;

public class Balloom extends Enemy {
    public Balloom(double x, double y, Game game) {
        super(x, y, game);
        img = Sprite.balloom_left1.getFxImage();
        ai = new AILow();
        speed =(double)Sprite.SCALED_SIZE/64;
        score = 100;
    }

    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 60).getFxImage();
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60).getFxImage();
                break;
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        if (isKilled()) {
            if (timeToDie > -60) {
                img = Sprite.movingSprite(Sprite.mob_dead1,Sprite.mob_dead2,Sprite.mob_dead3,animate,60).getFxImage();
                gc.drawImage(img, x, y);
            } else {
                remove();
                totalScore += score;
            }
            animate();
        } else {
            super.render(gc);
        }
    }
}
