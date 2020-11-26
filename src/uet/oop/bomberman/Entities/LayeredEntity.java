package uet.oop.bomberman.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Entities.SemiDynamic.Brick;
import uet.oop.bomberman.Game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LayeredEntity extends Entity {
    protected List<Entity> entities = new ArrayList<>();

    public LayeredEntity(double x, double y, Entity entity1, Game game) {
        super(x, y, game);
        entities.add(entity1);
        img = getTopEntity().getImg();
    }

    public LayeredEntity(double x, double y, Entity entity1, Entity entity2, Game game) {
        super(x, y, game);
        entities.add(entity1);
        entities.add(entity2);
        img = getTopEntity().getImg();
    }


    public void update() {
        Entity entityRemove = null;
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
            if (entities.get(i).isRemoved()) {
                entityRemove = entities.get(i);
            }
        }
        entities.remove(entityRemove);
    }

    public void collide() {

    }

    public Entity getTopEntity() {
        Entity entity = null;
        if (entities.isEmpty()) {
            remove();
        } else {
            entity = entities.get(entities.size() - 1);
        }
        return entity;
    }

    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(gc);
        }
    }

}
