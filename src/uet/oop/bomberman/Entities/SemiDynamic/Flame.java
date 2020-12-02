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
    protected int direction; //huong ngon lua : trên/dưới/trái/phải/doc/ngang tương ứng là 0/1/2/3/4/5
    public boolean canRender = true;

    public Flame(double x, double y, int direction, Game game) {
        super(x, y, game);
        this.direction = direction;
    }

    public Flame(double x, double y, Game game)
    {
        super(x,y,game);
    }

    public void collide() {
        List<Entity> entityList = game.getEntityAt(x, y);
        double xOfBomber = Game.bomberman.getX();
        double yOfBomber = Game.bomberman.getY();
        if (timeToExplode <= 0) {
            for (Entity entity : entityList) {
                if (entity instanceof LayeredEntity) {
                    if (((LayeredEntity) entity).getTopEntity() instanceof Brick) {
                        ((LayeredEntity) entity).getTopEntity().kill();
                        Sound.play("BOM_11_M");
                        canRender = false;
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
                    Sound.play("bomb_bang");
                    switch (direction) {
                        case 0:
                            img = Sprite.explosion_vertical_top_last.getFxImage();
                            break;
                        case 2:
                            img = Sprite.explosion_vertical_down_last.getFxImage();
                            break;
                        case 3:
                            img = Sprite.explosion_horizontal_left_last.getFxImage();
                            break;
                        case 1:
                            img = Sprite.explosion_horizontal_right_last.getFxImage();
                            break;
                        case 4:
                            img = Sprite.explosion_vertical.getFxImage();
                            break;
                        case 5:
                            img = Sprite.explosion_horizontal.getFxImage();
                    }
                } else if (animate % 30 < 20) {
                    switch (direction) {
                        case 0:
                            img = Sprite.explosion_vertical_top_last1.getFxImage();
                            break;
                        case 2:
                            img = Sprite.explosion_vertical_down_last1.getFxImage();
                            break;
                        case 3:
                            img = Sprite.explosion_horizontal_left_last1.getFxImage();
                            break;
                        case 1:
                            img = Sprite.explosion_horizontal_right_last1.getFxImage();
                            break;
                        case 4:
                            img = Sprite.explosion_vertical1.getFxImage();
                            break;
                        case 5:
                            img = Sprite.explosion_horizontal1.getFxImage();
                    }
                } else {
                    switch (direction) {
                        case 0:
                            img = Sprite.explosion_vertical_top_last2.getFxImage();
                            break;
                        case 2:
                            img = Sprite.explosion_vertical_down_last2.getFxImage();
                            break;
                        case 3:
                            img = Sprite.explosion_horizontal_left_last2.getFxImage();
                            break;
                        case 1:
                            img = Sprite.explosion_horizontal_right_last2.getFxImage();
                            break;
                        case 4:
                            img = Sprite.explosion_vertical2.getFxImage();
                            break;
                        case 5:
                            img = Sprite.explosion_horizontal2.getFxImage();
                    }
                }
                if (canRender) {
                    gc.drawImage(img, x, y);
                }
            } else {
                remove();
            }
            animate();
        } else {
            remove();
        }
    }
}

