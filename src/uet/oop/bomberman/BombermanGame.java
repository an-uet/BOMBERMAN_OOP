package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.Entities.Level.Level;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import static uet.oop.bomberman.Game.TIME;
import static uet.oop.bomberman.Game.totalScore;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static GraphicsContext gc;
    public static Canvas canvas;
    private Game game = new Game();
    private Level level = new Level(game);
    public int numOfLevel = 1;
    Screen screen = new Screen();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);

    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        Pane paneCanvas = new Pane();
        paneCanvas.setStyle("-fx-background-color: black");
        canvas = new Canvas();
        canvas.setHeight(Sprite.SCALED_SIZE * HEIGHT);
        canvas.setWidth(Sprite.SCALED_SIZE * WIDTH);
        gc = canvas.getGraphicsContext2D();

        // set layout for canvas.
        canvas.setLayoutX(1000 / 2 - Sprite.SCALED_SIZE * WIDTH / 2);
        canvas.setLayoutY(50);

        // Tao container
        paneCanvas.getChildren().add(canvas);

        //score.
        Label score = new Label(String.format("Score: %d", totalScore));
        score.setTextFill(Color.WHITE);
        score.setLayoutX(750);
        score.setLayoutY(10);
        paneCanvas.getChildren().add(score);

        //time.
        Label time = new Label(String.format("Time: %d", TIME));
        time.setTextFill(Color.WHITE);
        time.setLayoutX(400);
        time.setLayoutY(10);
        paneCanvas.getChildren().add(time);

        Label level1 = new Label(String.format("Level : %d", numOfLevel));
        level1.setTextFill(Color.WHITE);
        level1.setLayoutX(50);
        level1.setLayoutY(10);
        paneCanvas.getChildren().add(level1);


        // Tao scene
        Scene scene = new Scene(paneCanvas, 1000, 470);


        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        level.createMap(1);
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long l) {
                game.render();
                game.update();
                GameOver(stage);
                if (Game.changeLevel && !game.bomberman.isKilled()) {
                    numOfLevel++;
                    game.reset();
                    level.createMap(numOfLevel);
                    game.changeLevel = false;
                }
            }
        };
        timer.start();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                game.bomberman.anime(keyEvent);
            }

        });
        // nhac cho man choi.
        Sound.play("soundtrack");

    }


    // hiện màn hình game over khi bomber chết.
    public void GameOver(Stage stage) {
        if (game.bomberman.isKilled()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event ->
            {
                stage.setScene(screen.gameOver());
            });
            delay.play();
        } else {
            // ket thuc man choi sau 200s.
            PauseTransition delay = new PauseTransition(Duration.seconds(200));
            delay.setOnFinished(event ->
            {
                stage.setScene(screen.gameOver());
            });
            delay.play();
        }
    }


}
