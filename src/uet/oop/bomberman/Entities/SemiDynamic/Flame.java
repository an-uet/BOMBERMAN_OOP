package uet.oop.bomberman.Entities.SemiDynamic;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.Static.Wall;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.List;

public class Flame extends SemiDynamic {
    protected int direction; //huong ngon lua : trên/dưới/trái/phải tương ứng là 0/1/2/3

    public Flame(double x, double y, int direction, Game game) {
        super(x, y, game);
        this.direction = direction;
    }

    public void collide() {
        List<Entity> entityList = game.getEntityAt(x, y);
        double xOfBomber = game.bomberman.getX();
        double yOfBomber = game.bomberman.getY();
        if (timeToExplode <= 0) {
            for (Entity entity : entityList) {
                if (entity instanceof LayeredEntity) {
                    if (((LayeredEntity) entity).getTopEntity() instanceof Brick) {
                        ((LayeredEntity) entity).getTopEntity().kill();
                        Sound.play("BOM_11_M");
                    }
                }
                if (entity instanceof Enemy) {
                    entity.kill();
                    Sound.play("AA126_11");
                }
            }
        }
        if (timeToExplode <= 0) {
            if (x + 32 < xOfBomber) {
                return;
            } else if (x > xOfBomber + 32) {
                return;
            } else if (y > yOfBomber + 32) {
                return;
            } else if (y + 32 < yOfBomber) {
                return;
            } else {
                game.bomberman.kill();
                //Sound.play("endgame3");
            }
        }
    }
    public boolean canExplodes() { // hàm kiểm tra có thể nổ được ở vị trí hiện tại không
        List<Entity> entityList = game.getEntityAt(x, y);
        for (Entity entity : entityList) {
            if (entity instanceof Wall) {
                return false;
            }
        }
        return true;
    }

    public void render(GraphicsContext gc) {
        if (canExplodes()) {
            if (timeToDie > 0 && timeToExplode <= 0) {
                if (animate % 30 < 10) {
                    switch (direction) {
                        case 0:
                            img = Sprite.explosion_vertical_top_last.getFxImage();
                            break;
                        case 1:
                            img = Sprite.explosion_vertical_down_last.getFxImage();
                            break;
                        case 2:
                            img = Sprite.explosion_horizontal_left_last.getFxImage();
                            break;
                        case 3:
                            img = Sprite.explosion_horizontal_right_last.getFxImage();
                            break;
                    }
                } else if (animate % 30 < 20) {
                    switch (direction) {
                        case 0:
                            img = Sprite.explosion_vertical_top_last1.getFxImage();
                            break;
                        case 1:
                            img = Sprite.explosion_vertical_down_last1.getFxImage();
                            break;
                        case 2:
                            img = Sprite.explosion_horizontal_left_last1.getFxImage();
                            break;
                        case 3:
                            img = Sprite.explosion_horizontal_right_last1.getFxImage();
                            break;
                    }
                } else {
                    switch (direction) {
                        case 0:
                            img = Sprite.explosion_vertical_top_last2.getFxImage();
                            break;
                        case 1:
                            img = Sprite.explosion_vertical_down_last2.getFxImage();
                            break;
                        case 2:
                            img = Sprite.explosion_horizontal_left_last2.getFxImage();
                            break;
                        case 3:
                            img = Sprite.explosion_horizontal_right_last2.getFxImage();
                            break;
                    }
                }
                gc.drawImage(img, x, y);
            } else {
                remove();
            }
            animate();
        } else {
            remove();
        }
    }
}

