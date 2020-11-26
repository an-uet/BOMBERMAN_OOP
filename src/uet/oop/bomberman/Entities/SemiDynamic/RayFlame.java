package uet.oop.bomberman.Entities.SemiDynamic;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class RayFlame extends SemiDynamic{
    public static int lengthFlame = 1;
    protected int direction;
    public List<Flame> flameList = new ArrayList<>();

    public RayFlame(double xUnit, double yUnit,  int direction, Game game) {

        super(xUnit, yUnit, game);
        this.direction = direction;
        for (int i = 0; i < lengthFlame - 1; i++) {
            if (direction == 0) {
                flameList.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - i, 4, game));
            } else if (direction == 1) {
                flameList.add(new Flame(x / Sprite.SCALED_SIZE + i, y / Sprite.SCALED_SIZE, 5, game));
            } else if (direction == 2) {
                flameList.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + i, 4, game));
            } else {
                flameList.add(new Flame(x / Sprite.SCALED_SIZE - i, y / Sprite.SCALED_SIZE, 5, game));
            }
        }
        if (direction == 0) {
            flameList.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE - (lengthFlame - 1), 0, game));
        } else if (direction == 1) {
            flameList.add(new Flame(x / Sprite.SCALED_SIZE + (lengthFlame - 1), y / Sprite.SCALED_SIZE, 1, game));
        } else if (direction == 2) {
            flameList.add(new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE + (lengthFlame - 1), 2, game));
        } else {
            flameList.add(new Flame(x / Sprite.SCALED_SIZE - (lengthFlame - 1), y / Sprite.SCALED_SIZE, 3, game));
        }
    }
    public void collide() {
        flameList.get(0).collide();
        int i = 1;
        while (i < flameList.size()) {
            if (!flameList.get(i - 1).canExplodes() || !flameList.get(i - 1).canRender) {
                break;
            } else {
                flameList.get(i).collide();
                i++;
            }
        }
    }

    public void render(GraphicsContext gc) {
        int i = 0;
        while (i < flameList.size()) {
            if (flameList.get(i).canExplodes() && flameList.get(i).canRender) {
                flameList.get(i).render(gc);
                i++;
            } else {
                break;
            }
        }
    }


    public void update() {
        for (Flame flame : flameList) {
            flame.update();
        }
    }
}
