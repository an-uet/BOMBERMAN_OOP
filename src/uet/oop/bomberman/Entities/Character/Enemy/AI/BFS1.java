package uet.oop.bomberman.Entities.Character.Enemy.AI;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Entity;
import uet.oop.bomberman.Entities.LayeredEntity;
import uet.oop.bomberman.Entities.SemiDynamic.Bomb;
import uet.oop.bomberman.Entities.SemiDynamic.Brick;
import uet.oop.bomberman.Entities.SemiDynamic.Flame;
import uet.oop.bomberman.Entities.Static.Wall;
import uet.oop.bomberman.Game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BFS1 {
    Entity entity;
    Entity entity1;
    Game game;
    boolean[][] visited = new boolean[BombermanGame.WIDTH][BombermanGame.HEIGHT];
    String[][] path = new String[BombermanGame.WIDTH][BombermanGame.HEIGHT];

    public BFS1(Entity entity, Entity entity1, Game game) {
        this.entity = entity;
        this.entity1 = entity1;
        this.game = game;
    }

    public int calculateDirection() {
        Point point = new Point((int) (entity.getX() / 32), (int) (entity.getY() / 32));
        Point point1 = new Point((int) entity1.getX() / 32, (int) entity1.getY() / 32);
        String eTob = resultPath(point1, point);
        if (eTob.equals("l")) {
            return 3;
        } else if (eTob.equals("r")) {
            return 1;
        } else if (eTob.equals("u")) {
            return 0;
        } else if (eTob.equals("d")) {
            return 2;
        } else {
            return 2;
        }
    }

    public int calculateDirection2() {
        Point point = new Point((int) (entity.getX() / 32), (int) (entity.getY() / 32));
        Point point1 = new Point((int) entity1.getX() / 32, (int) entity1.getY() / 32);
        String eTob = resultPath(point1, point);
        if (eTob.equals("l")) {
            return 1;
        } else if (eTob.equals("r")) {
            return 3;
        } else if (eTob.equals("u")) {
            return 2;
        } else if (eTob.equals("d")) {
            return 0;
        } else {
            return 2;
        }
    }

    public boolean nextPoint(int X, int Y) {
        List<Entity> entityList = game.getEntityAt(X * 32, Y * 32);
        for (Entity entity : entityList) {
            if (entity instanceof Wall || entity instanceof Bomb) {
                return false;
            }
            if (entity instanceof Flame) {
                System.out.println("Flame");
                return false;
            }
        }
        return true;
    }


    public boolean nextPointIsBrick(int X, int Y) {
        List<Entity> entityList = game.getEntityAt(X + 32, Y);
        for (Entity entity : entityList) {
            if (entity instanceof Brick) {
                return true;
            } else if (entity instanceof LayeredEntity) {
                if (((LayeredEntity) entity).getTopEntity() instanceof Brick) {
                    return true;
                }
            }
        }
        entityList = game.getEntityAt(X - 32, Y);
        for (Entity entity : entityList) {
            if (entity instanceof Brick) {
                return true;
            } else if (entity instanceof LayeredEntity) {
                if (((LayeredEntity) entity).getTopEntity() instanceof Brick) {
                    return true;
                }
            }
        }
        entityList = game.getEntityAt(X, Y + 32);
        for (Entity entity : entityList) {
            if (entity instanceof Brick) {
                return true;
            } else if (entity instanceof LayeredEntity) {
                if (((LayeredEntity) entity).getTopEntity() instanceof Brick) {
                    return true;
                }
            }
        }
        entityList = game.getEntityAt(X, Y - 32);
        for (Entity entity : entityList) {
            if (entity instanceof Brick) {
                return true;
            } else if (entity instanceof LayeredEntity) {
                if (((LayeredEntity) entity).getTopEntity() instanceof Brick) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean nextPointIsBomb(int X, int Y) {
        List<Entity> entityList = game.getEntityAt(X, Y);
        for (Entity entity : entityList) {
            if (entity instanceof Bomb) {
                return true;
            }
        }
        return false;
    }

    public void bfs(Point point) {
        for (int i = 0; i < BombermanGame.WIDTH; i++) {
            for (int j = 0; j < BombermanGame.HEIGHT; j++) {
                visited[i][j] = false;
                path[i][j] = " ";
            }
        }
        LinkedList<Point> linkedList = new LinkedList<>();
        visited[point.x][point.y] = true;
        linkedList.add(point);
        boolean check = true;
        while (!linkedList.isEmpty()) {
            Point point1 = linkedList.pollFirst();
            int X, Y;
            X = point1.x + 1;
            Y = point1.y;
            if (nextPoint(X, Y)) {
                if (!visited[X][Y] ) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "r";
                }
            }
            X = point1.x - 1;
            Y = point1.y;
            if (nextPoint(X, Y)) {
                if (!visited[X][Y]) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "l";
                }
            }
            X = point1.x;
            Y = point1.y + 1;
            if (nextPoint(X, Y)) {
                if (!visited[X][Y]) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "d";
                }
            }
            X = point1.x;
            Y = point1.y - 1;
            if (nextPoint(X, Y)) {
                if (!visited[X][Y]) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "u";
                }
            }
        }
    }

    public String resultPath(Point point1, Point point2) {
        List<String> result = new ArrayList<>();
        bfs(point2);
        if (!visited[point1.x][point1.y]) return " ";

        String direction = "";
        System.out.println(point2.x + " " + point2.y);
        while (!point1.equals(point2)) {
            System.out.println(point1.x + " " + point1.y);
            direction = path[point1.x][point1.y];
            result.add(0, direction);
            if (direction == "l") {
                point1.x++;
            } else if (direction == "r") {
                point1.x--;
            } else if (direction == "d") {
                point1.y--;
            } else if (direction == "u") {
                point1.y++;
            }
        }
        System.out.println(result);
        return direction;
    }


}


