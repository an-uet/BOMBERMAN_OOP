package uet.oop.bomberman.Entities.Character.Enemy.AI;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Entities.Character.Bomber;
import uet.oop.bomberman.Entities.Character.Enemy.Enemy;
import uet.oop.bomberman.Entities.Character.Enemy.Oneal;
import uet.oop.bomberman.Game;

import java.util.LinkedList;

public class AIOneal extends AI {
    Oneal oneal;
    boolean[][] visited = new boolean[BombermanGame.WIDTH][BombermanGame.HEIGHT];
    String[][] path = new String[BombermanGame.WIDTH][BombermanGame.HEIGHT];
    public AIOneal(Oneal oneal) {
        this.oneal = oneal;
    }

    public int calculateDirection() {
        Point onealPoint = new Point((int) oneal.getX() / 32, (int) oneal.getY() / 32);
        Point point = new Point((int) Game.bomberman.getX() / 32, (int) Game.bomberman.getY() / 32);
        String eTob = resultPath(point, onealPoint);
        if (eTob.equals("l")) {
            return 3;
        } else if (eTob.equals("r")) {
            return 1;
        } else if (eTob.equals("u")) {
            return 0;
        } else {
            return 2;
        }
    }

    public void bfs(Point point) {
        for (int i = 0; i < BombermanGame.WIDTH; i++) {
            for (int j = 0; j < BombermanGame.HEIGHT; j++) {
                visited[i][j] = false;
                path[i][j] = "";
            }
        }
        LinkedList<Point> linkedList = new LinkedList<>();
        visited[point.x][point.y] = true;
        linkedList.add(point);
        while (linkedList.isEmpty() != true) {
            Point point1 = linkedList.pollFirst();
            int X, Y;
            X = point1.x + 1;
            Y = point1.y;
            if (X <= BombermanGame.WIDTH - 1 && Game.mapChar[Y][X] != '#'
                    && Game.mapChar[Y][X] != '*') {
                if (visited[X][Y] != true) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "r";
                }
            }
            X = point1.x - 1;
            Y = point1.y;
            if (X >= 0 && Game.mapChar[Y][X] != '#'
                    && Game.mapChar[Y][X] != '*') {
                if (visited[X][Y] != true) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "l";
                }
            }
            X = point1.x;
            Y = point1.y + 1;
            if (Y <= BombermanGame.HEIGHT - 1 && Game.mapChar[Y][X] != '#'
                    && Game.mapChar[Y][X] != '*') {
                if (visited[X][Y] != true) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "d";
                }
            }
            X = point1.x;
            Y = point1.y - 1;
            if (Y >= 0 && Game.mapChar[Y][X] != '#'
                    && Game.mapChar[Y][X] != '*') {
                if (visited[X][Y] != true) {
                    visited[X][Y] = true;
                    Point newPoint = new Point(X, Y);
                    linkedList.add(newPoint);
                    path[X][Y] = "u";
                }
            }
        }
    }

    public String resultPath(Point point1, Point point2) {
        String result = " ";
        bfs(point2);
        if (visited[point1.x][point1.y] == false) return " ";

        String direction = "";
        System.out.println(point2.x + " " + point2.y);
        while (point1.equals(point2) != true) {
            System.out.println(point1.x + " " + point1.y);
            direction = path[point1.x][point1.y];
            result = direction + result;
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
        return direction;
    }
}
