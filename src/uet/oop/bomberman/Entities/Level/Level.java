package uet.oop.bomberman.Entities.Level;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Entities.Character.Bomber;
import uet.oop.bomberman.Entities.Character.Enemy.Balloom;
import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Character.Enemy.Oneal;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.SemiDynamic.Bomb;
import uet.oop.bomberman.Entities.SemiDynamic.Brick;
import uet.oop.bomberman.Entities.Static.Grass;
import uet.oop.bomberman.Entities.Static.Item.BombItem;
import uet.oop.bomberman.Entities.Static.Item.FlameItem;
import uet.oop.bomberman.Entities.Static.Item.SpeedItem;
import uet.oop.bomberman.Entities.Static.Portal;
import uet.oop.bomberman.Entities.Static.Wall;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.*;

public class Level {
    private Game game;
    public Level(Game game) {
        this.game = game;
    }

    public void createMap(int level) {
        loadMapFromFile(level);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Grass grass = new Grass(j, i, game);
                Game.stillObjects.add(grass);
                if (Game.mapChar[i][j] == '#') {
                    Wall wall = new Wall(j, i, game);
                    Game.stillObjects.add(wall);
                    Game.wallAndBrick.add(wall);
                }
                loadLevelEntities(Game.mapChar[i][j], j, i);
            }
        }
    }

    public void loadMapFromFile(int level) {
        File readFile = new File("res\\levels\\Level" + level + ".txt");
        try (Scanner reader = new Scanner(readFile)) {
            reader.nextInt();
            reader.nextLine();
            while (reader.hasNextLine()) {
                for (int i = 0; i < HEIGHT; i++) {
                    String s = reader.nextLine();
                    for (int j = 0; j < WIDTH; j++)
                        Game.mapChar[i][j] = s.charAt(j);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadLevelEntities(char c, int x, int y) {

        switch (c) {

            case '*':
                Brick brick = new Brick(x, y, game);
                LayeredEntity layeredEntity = new LayeredEntity(x, y, brick, game);
                Game.layeredEntities.add(layeredEntity);
                Game.wallAndBrick.add(brick);
                break;

            case 'x':
                Portal portal = new Portal(x, y, game);
                Brick brick1 = new Brick(x, y, game);
                LayeredEntity layeredEntity1 = new LayeredEntity(x, y, portal, brick1, game);
                Game.layeredEntities.add(layeredEntity1);
                break;

            case 'p':
                game.bomberman = new Bomber(x, y, game);
                break;

            case '1':
                Balloom balloom = new Balloom(x, y, game);
                Game.enemies.add(balloom);
                break;

            case '2':
                Oneal oneal = new Oneal(x, y, game);
                Game.enemies.add(oneal);
                break;

            case 'b':
                BombItem bombItem = new BombItem(x, y, game);
                Brick brick3 = new Brick(x, y, game);
                LayeredEntity layeredEntity2 = new LayeredEntity(x, y, bombItem, brick3, game);
                Game.layeredEntities.add(layeredEntity2);
                Game.wallAndBrick.add(brick3);
                break;

            case 'f':
                FlameItem flameItem = new FlameItem(x, y, game);
                Brick brick4 = new Brick(x, y, game);
                LayeredEntity layeredEntity3 = new LayeredEntity(x, y, flameItem, brick4, game);
                Game.layeredEntities.add(layeredEntity3);
                Game.wallAndBrick.add(brick4);
                break;

            case 's':
                SpeedItem speedItem = new SpeedItem(x, y, game);
                Brick brick2 = new Brick(x, y, game);
                LayeredEntity layeredEntity4 = new LayeredEntity(x, y, speedItem, brick2, game);
                Game.layeredEntities.add(layeredEntity4);
                Game.wallAndBrick.add(brick2);
                break;

        }
    }
}