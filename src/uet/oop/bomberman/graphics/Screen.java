package uet.oop.bomberman.graphics;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uet.oop.bomberman.Entities.Level.Level;
import uet.oop.bomberman.Game;

import static uet.oop.bomberman.BombermanGame.HEIGHT;
import static uet.oop.bomberman.BombermanGame.WIDTH;
import static uet.oop.bomberman.Game.totalScore;

public class Screen {
    private Game game = new Game();
    private Level level = new Level(game);

        public Scene gameOver() {
        Group root = new Group();
        StackPane sp = new StackPane();
        sp.getChildren().addAll(new Rectangle(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT, Color.BLACK));

        Text text = new Text(String.format("Game Over \n Score: %d", totalScore));
        text.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        sp.getChildren().addAll(text);

       /* Button newGame = new Button("new Game");
        newGame.setLayoutX(500);
        newGame.setLayoutY(300);
        newGame.setOnMouseClicked(event -> {
            createNew(2);
        });
        sp.getChildren().add(newGame);

        */
        root.getChildren().addAll(sp);
        Scene scene = new Scene(root);
        return scene;


    }

   /* private void createNew(int i) {
        if(game.bomberman.isKilled())
        {
            game.reset();
            level.createMap(i);
        }
    }

    */

    public Scene LevelScene() {
        Group root = new Group();
        StackPane sp = new StackPane();
        sp.getChildren().addAll(new Rectangle(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT, Color.BLACK));

        Text text = new Text("BOMBERMAN\n Level: "); // + bomberman level + ?
        text.setFont(Font.font("Arial", FontWeight.LIGHT, 60));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        sp.getChildren().addAll(text);

        root.getChildren().addAll(sp);
        Scene scene = new Scene(root);
        return scene;
    }





}
