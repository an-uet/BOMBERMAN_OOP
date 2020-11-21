package uet.oop.bomberman.Entities.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.Level.Level;
import uet.oop.bomberman.Entities.SemiDynamic.Bomb;
import uet.oop.bomberman.Entities.SemiDynamic.Brick;
import uet.oop.bomberman.Entities.Static.Grass;
import uet.oop.bomberman.Entities.Static.Wall;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.HEIGHT;
import static uet.oop.bomberman.BombermanGame.WIDTH;

public class Bomber extends Character {
    private int up = 0;
    private int down = 0;
    private int right = 0;
    private int left = 0;



    public Bomber(double x, double y, Game game) {
        super(x, y, game);
        img = Sprite.player_down.getFxImage();
        this.game = game;
        this.speed = Sprite.SCALED_SIZE / 8;
    }

    public void collide() {
        List<Entity> entityList = game.getEntityAt(x , y);
        for (Entity a : entityList) {
            if (a instanceof Enemy) {
                kill();
            }
        }
    }

    public void moveLeft() {
        canMove = true;
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left).getFxImage();
        if (left == 8) {
            left = 0;
        } else {
            left++;
        }
        List<Entity> entityList = game.getEntityAt(x - speed, y);
        for (Entity a : entityList) {
            if ((a instanceof Wall || a instanceof Bomb) && x > a.getX()) {
                canMove = false;
            } else if (a instanceof LayeredEntity && x > a.getX()) {
                if (((LayeredEntity) a).getTopEntity() instanceof Brick) {
                    canMove = false;
                }
            }
        }
        if (canMove) {
            x -= speed;
        }
    }

    public void moveRight() {
       canMove = true;
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right).getFxImage();
        if (right == 8) {
            right = 0;
        } else {
            right++;
        }
        List<Entity> entityList = game.getEntityAt(x + speed, y);
        for (Entity a : entityList) {
            if ((a instanceof Wall || a instanceof Bomb) && x < a.getX()) {
                canMove = false;
            } else if (a instanceof LayeredEntity && x < a.getX()) {
                if (((LayeredEntity) a).getTopEntity() instanceof Brick) {
                    canMove = false;
                }
            }
        }
        if (canMove) {
            x += speed;
        }
    }

    public void moveUp() {
      canMove = true;
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up).getFxImage();
        if (up == 8) {
            up = 0;
        } else {
            up++;
        }
        List<Entity> entityList = game.getEntityAt(x, y - speed);
        for (Entity a : entityList) {
            if ((a instanceof Wall || a instanceof Bomb) && y > a.getY()) {
                canMove = false;
            } else if (a instanceof LayeredEntity && y > a.getY()) {
                if (((LayeredEntity) a).getTopEntity() instanceof Brick) {
                    canMove = false;
                }
            }
        }
        if (canMove) {
            y -= speed;
        }
    }

    public void moveDown() {
       canMove = true;
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down).getFxImage();
        if (down == 8) {
            down = 0;
        } else {
            down++;
        }
        List<Entity> entityList = game.getEntityAt(x, y + speed);
        for (Entity a : entityList) {
            if ((a instanceof Wall || a instanceof Bomb) && y < a.getY()) {
                canMove = false;
            } else if (a instanceof LayeredEntity && y < a.getY()) {
                if (((LayeredEntity) a).getTopEntity() instanceof Brick) {
                    canMove = false;
                }
            }
        }
        if (canMove) {
            y += speed;
        }
    }

    public void anime(KeyEvent event) {
        if (event.getCode().equals(KeyCode.RIGHT)) {
            moveRight();
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            moveDown();
        }
        if (event.getCode().equals(KeyCode.UP)) {
            moveUp();
        }
        if (event.getCode().equals(KeyCode.LEFT)) {
            moveLeft();
        }
        if (event.getCode().equals(KeyCode.SPACE)) {
            Sound.play("BOM_SET");
            Bomb bom = new Bomb(Math.round(x / Sprite.SCALED_SIZE), Math.round(y / Sprite.SCALED_SIZE), game);
            Game.flames.add(bom.flameDown);
            Game.flames.add(bom.flameLeft);
            Game.flames.add(bom.flameUp);
            Game.flames.add(bom.flameRight);
            Game.bombs.add(bom);

        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isKilled()) {
            if (timeToDie > -30) {
                if (animate % 60 < 20) {
                    img = Sprite.player_dead1.getFxImage();
                } else if (animate % 60 < 40) {
                    img = Sprite.player_dead2.getFxImage();
                } else {
                    img = Sprite.player_dead3.getFxImage();
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
