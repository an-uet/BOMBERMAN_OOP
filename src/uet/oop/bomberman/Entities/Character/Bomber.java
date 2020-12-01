package uet.oop.bomberman.Entities.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.SemiDynamic.Bomb;
import uet.oop.bomberman.Entities.SemiDynamic.RayFlame;
import uet.oop.bomberman.Entities.Static.Item.BombItem;
import uet.oop.bomberman.Entities.Static.Item.FlameItem;
import uet.oop.bomberman.Entities.Static.Item.SpeedItem;
import uet.oop.bomberman.Entities.Static.Portal;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.List;

public class Bomber extends Character {
    public int MAXTIME = 8;
    private int up = 0;
    private int down = 0;
    private int right = 0;
    private int left = 0;
    public int amountBomb = 1;


    public Bomber(double x, double y, Game game) {
        super(x, y, game);
        img = Sprite.player_down.getFxImage();
        this.game = game;
        this.speed = Sprite.SCALED_SIZE / 8;
    }

    public void setMAXTIME(int maxtime) {
        this.MAXTIME = maxtime;
    }
    public void collide() {
        List<Entity> entityList = game.getEntityAt(x, y);
        for (Entity a : entityList) {
            if (a instanceof LayeredEntity) {
                if (((LayeredEntity) a).getTopEntity() instanceof SpeedItem) {
                    x = a.getX();
                    y = a.getY();
                    speed *= 2;
                    a.remove();
                    Sound.play("CRYST_UP");
                } else if (((LayeredEntity) a).getTopEntity() instanceof BombItem) {
                    amountBomb++;
                    a.remove();
                    Sound.play("CRYST_UP");
                } else if (((LayeredEntity) a).getTopEntity() instanceof FlameItem) {
                    RayFlame.lengthFlame += 1;
                    a.remove();
                    Sound.play("CRYST_UP");
                } else if (((LayeredEntity) a).getTopEntity() instanceof Portal) {
                    if (Game.enemies.isEmpty()) {
                        a.remove();
                        Sound.play("CRYST_UP");
                        Game.changeLevel = true;
                    }

                }

            }
            if (a instanceof Enemy) {
                game.bomberman.kill();
            }
        }
    }

    public void moveLeft() {
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left).getFxImage();
        if (left == MAXTIME) {
            left = 0;
        } else {
            left++;
        }
        if (canMove(3)) {
            x -= speed;
        }
    }

    public void moveRight() {
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right).getFxImage();
        if (right == MAXTIME) {
            right = 0;
        } else {
            right++;
        }
        if (canMove(1)) {
            x += speed;
        }
    }

    public void moveUp() {
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up).getFxImage();
        if (up == MAXTIME) {
            up = 0;
        } else {
            up++;
        }
        if (canMove(0)) {
            y -= speed;
        }
    }

    public void moveDown() {
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down).getFxImage();
        if (down == MAXTIME) {
            down = 0;
        } else {
            down++;
        }
        if (canMove(2)) {
            y += speed;
        }
    }

    public void anime(KeyEvent event) {
        if (event.getCode().equals(KeyCode.RIGHT)) {
            direction = 1;
            moveRight();
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            direction = 2;
            moveDown();
        }
        if (event.getCode().equals(KeyCode.UP)) {
            direction = 0;
            moveUp();
        }
        if (event.getCode().equals(KeyCode.LEFT)) {
            direction = 3;
            moveLeft();
        }
        if (event.getCode().equals(KeyCode.SPACE)) {
            /**if (Game.bombs.size() < amountBomb) {
                Sound.play("BOM_SET");
                Bomb bom;
                // chinh dat bom.
                if (direction == 0) {
                    bom = new Bomb(Math.round(x / Sprite.SCALED_SIZE), Math.floor(y / Sprite.SCALED_SIZE), game);
                }
                if (direction == 2) {
                    bom = new Bomb(Math.round(x / Sprite.SCALED_SIZE), Math.ceil(y / Sprite.SCALED_SIZE), game);
                }
                if (direction == 1) {
                    bom = new Bomb(Math.ceil(x / Sprite.SCALED_SIZE), Math.round(y / Sprite.SCALED_SIZE), game);
                } else {
                    bom = new Bomb(Math.floor(x / Sprite.SCALED_SIZE), Math.round(y / Sprite.SCALED_SIZE), game);
                }

                for (int i = 0; i < RayFlame.lengthFlame; i++) {
                    Game.flames.add(bom.rayFlameDown.flameList.get(i));
                    Game.flames.add(bom.rayFlameLeft.flameList.get(i));
                    Game.flames.add(bom.rayFlameUp.flameList.get(i));
                    Game.flames.add(bom.rayFlameRight.flameList.get(i));
                }
                Game.bombs.add(bom);
            }*/
            setBomb();
        }
    }

    public void setBomb() {
        if (Game.bombs.size() < amountBomb) {
            Sound.play("BOM_SET");
            Bomb bom;
            // chinh dat bom.
            if (direction == 0) {
                bom = new Bomb(Math.round(x / Sprite.SCALED_SIZE), Math.floor(y / Sprite.SCALED_SIZE), game);
            }
            if (direction == 2) {
                bom = new Bomb(Math.round(x / Sprite.SCALED_SIZE), Math.ceil(y / Sprite.SCALED_SIZE), game);
            }
            if (direction == 1) {
                bom = new Bomb(Math.ceil(x / Sprite.SCALED_SIZE), Math.round(y / Sprite.SCALED_SIZE), game);
            } else {
                bom = new Bomb(Math.floor(x / Sprite.SCALED_SIZE), Math.round(y / Sprite.SCALED_SIZE), game);
            }

            for (int i = 0; i < RayFlame.lengthFlame; i++) {
                Game.flames.add(bom.rayFlameDown.flameList.get(i));
                Game.flames.add(bom.rayFlameLeft.flameList.get(i));
                Game.flames.add(bom.rayFlameUp.flameList.get(i));
                Game.flames.add(bom.rayFlameRight.flameList.get(i));
            }
            Game.bombs.add(bom);
        }
    }
    @Override
    public void render(GraphicsContext gc) {
        if (isKilled()) {
            if (timeToDie > 20) {
                Sound.play("endgame3");
            }
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
