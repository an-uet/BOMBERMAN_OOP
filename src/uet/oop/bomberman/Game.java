package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sun.plugin2.message.Message;
import uet.oop.bomberman.Entities.Character.Bomber;
import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.Level.Level;
import uet.oop.bomberman.Entities.Massage;
import uet.oop.bomberman.Entities.SemiDynamic.Bomb;
import uet.oop.bomberman.Entities.SemiDynamic.Flame;
import uet.oop.bomberman.Entities.SemiDynamic.RayFlame;
import uet.oop.bomberman.Entities.Static.Grass;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.HEIGHT;
import static uet.oop.bomberman.BombermanGame.WIDTH;

public class Game {
    public Bomber bomberman;
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<LayeredEntity> layeredEntities = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();
    public static List<Flame> flames = new ArrayList<>();
    public static List<Massage> messages = new ArrayList<>();
    public static List<Entity> wallAndBrick = new ArrayList<>();

    public static char[][] mapChar = new char[HEIGHT][WIDTH];

    public List<Entity> getEntityAt (double x, double y) {
        List<Entity> entityList = new ArrayList<>();
        for (int i = 0; i < enemies.size(); i++) {
            double xOfEntity = enemies.get(i).getX();
            double yOfEntity = enemies.get(i).getY();
            if (xOfEntity >= x + 32 || xOfEntity + 32 <= x) {

            } else if (yOfEntity >= y + 32 || yOfEntity + 32 <= y) {

            } else {
                entityList.add(enemies.get(i));
            }
        }
        for (int i = 0; i < bombs.size(); i++) {
            double xOfEntity = bombs.get(i).getX();
            double yOfEntity = bombs.get(i).getY();
            if (xOfEntity >= x + 32 || xOfEntity + 32 <= x) {

            } else if (yOfEntity >= y + 32 || yOfEntity + 32 <= y) {

            } else {
                entityList.add(bombs.get(i));
            }
        }
        for (int i = 0; i < layeredEntities.size(); i++) {
            double xOfEntity = layeredEntities.get(i).getX();
            double yOfEntity = layeredEntities.get(i).getY();
            if (xOfEntity >= x + 32 || xOfEntity + 32 <= x) {

            } else if (yOfEntity >= y + 32 || yOfEntity + 32 <= y) {

            } else {
                entityList.add(layeredEntities.get(i));
            }
        }
        for (int i = 0; i < flames.size(); i++) {
            double xOfEntity = flames.get(i).getX();
            double yOfEntity = flames.get(i).getY();
            if (xOfEntity >= x + 32 || xOfEntity + 32 <= x) {

            } else if (yOfEntity >= y + 32 || yOfEntity + 32 <= y) {

            } else {
                entityList.add(flames.get(i));
            }
        }
        for (int i = 0; i < stillObjects.size(); i++) {
            double xOfEntity = stillObjects.get(i).getX();
            double yOfEntity = stillObjects.get(i).getY();
            if (xOfEntity >= x + 32 || xOfEntity + 32 <= x) {

            } else if (yOfEntity >= y + 32 || yOfEntity + 32 <= y) {

            } else {
                entityList.add(stillObjects.get(i));
            }
        }
        return entityList;
    }

    public void update() {
        bomberman.update();
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).isRemoved()) {
                enemies.remove(i);
            }
        }
        enemies.forEach(Enemy::update);
        stillObjects.forEach(Entity::update);
        for (int i = 0; i < layeredEntities.size(); i++) {
            if (layeredEntities.get(i).isRemoved()) {
                layeredEntities.remove(i);
            }
        }
        layeredEntities.forEach(LayeredEntity::update);
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).isRemoved()) {
                bombs.remove(i);
            }
        }
        bombs.forEach(Bomb::update);
        for (int i = 0; i < flames.size(); i++) {
            if (flames.get(i).isRemoved()) {
                flames.remove(i);
            }
        }
    }

    public void render() {
        BombermanGame.gc.clearRect(0, 0, BombermanGame.canvas.getWidth(), BombermanGame.canvas.getHeight());
        stillObjects.forEach(g -> g.render(BombermanGame.gc));
        layeredEntities.forEach(g -> g.render(BombermanGame.gc));
        bomberman.render(BombermanGame.gc);
        enemies.forEach(g -> g.render(BombermanGame.gc));
        bombs.forEach(g -> g.render(BombermanGame.gc));
        messages.forEach(g -> g.render(BombermanGame.gc));
        //Wall_and_Brick.forEach(g ->g.render(BombermanGame.gc));
    }
}
