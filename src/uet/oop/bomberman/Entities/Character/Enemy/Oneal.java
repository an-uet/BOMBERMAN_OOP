package uet.oop.bomberman.Entities.Character.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AILow;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AIMedium;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AIOneal;
import uet.oop.bomberman.Entities.Character.Enemy.AI.Point;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.Game.totalScore;

public class Oneal extends Enemy {
    //protected Random random = new Random();
    //
    //public Point bomber = new Point((int) (game.bomberman.getX() / 32), (int) (game.bomberman.getY() / 32));
    //public Point oneal = new Point((int) x / 32, (int) y / 32);


    public Oneal(double x, double y, Game game) {
        super(x, y, game);
        img = Sprite.oneal_left1.getFxImage();
        ai = new AIOneal(this, game);
        speed = (double) Sprite.SCALED_SIZE / 64 ;
        //speed += random.nextInt(2);
        score = 200;
        //direction = ai.calculateDirection();
    }

    public void move() {
        if (this.x % 32 == 0 && this.y % 32 == 0) {
            direction = ai.calculateDirection();
        }
        if (direction == 0) {
            up();
        } else if (direction == 2) {
            down();
        } else if (direction == 3) {
            left();
        } else {
            right();
        }
    }

    protected void chooseSprite() {
        switch (direction) {
            case 0:
            case 1:
                img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60).getFxImage();
                break;
            case 2:
            case 3:
                img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60).getFxImage();
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        chooseSprite();
        if (isKilled()) {
            if (timeToDie > -30) {
                if (animate % 60 < 20) {
                    img = Sprite.oneal_dead.getFxImage();
                } else if (animate % 60 < 40) {
                    img = Sprite.mob_dead2.getFxImage();
                } else {
                    img = Sprite.mob_dead3.getFxImage();
                }
                gc.drawImage(img, x, y);
            } else {
                remove();
            }
            animate();
        } else {
            super.render(gc);
        }

    }
}
