package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.Entities.Character.Enemy.AI.AIBomber;
import uet.oop.bomberman.Entities.Level.Level;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.io.InputStream;

import static uet.oop.bomberman.Game.*;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static GraphicsContext gc;
    public static Canvas canvas;
    private Game game = new Game();
    private Level level = new Level(game);
    public int numOfLevel = 1;
    public int timeToSub = 1;
    public int lives = 3;
    public int mode = 1;

    Screen screen = new Screen();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);

    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        Sound.play("startstage");
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
        score.setLayoutX(550);
        score.setLayoutY(10);
        paneCanvas.getChildren().add(score);

        //time.
        Label time = new Label(String.format("Time: %d", TIME));
        time.setTextFill(Color.WHITE);
        time.setLayoutX(300);
        time.setLayoutY(10);
        paneCanvas.getChildren().add(time);

        //level.
        Label level1 = new Label(String.format("Level : %d", numOfLevel));
        level1.setTextFill(Color.WHITE);
        level1.setLayoutX(50);
        level1.setLayoutY(10);
        paneCanvas.getChildren().add(level1);

        //lives
        Label live = new Label(String.format("Live : %d", lives));
        live.setTextFill(Color.WHITE);
        live.setLayoutX(850);
        live.setLayoutY(10);
        paneCanvas.getChildren().add(live);


        // tao scene Start.
        Group root = new Group();
        root.getChildren().addAll(new Rectangle(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT, Color.BLACK));
        Class<?> clazz = this.getClass();

        InputStream input = clazz.getResourceAsStream("/sprites/start.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(250);
        imageView.setLayoutY(5);
        root.getChildren().addAll(imageView);

        // button chon choi binh thuong.
        Button start = new Button("Start");
        start.setStyle("-fx-background-color: olive");
        start.setTranslateX(470);
        start.setTranslateY(255);
        start.setPadding(new Insets(7, 25, 7, 25));
        root.getChildren().add(start);

        // button chon choi Ai
        Button startAI = new Button("Game AI");
        startAI.setStyle("-fx-background-color: olive");
        startAI.setLayoutX(470);
        startAI.setLayoutY(310);
        root.getChildren().add(startAI);


        Scene startScene = new Scene(root);
        stage.setScene(startScene);
        stage.show();


        // choi binhf thuong.
        start.setOnMouseClicked(mouseEvent -> {
                    Scene scene = new Scene(paneCanvas, 1000, 470);
                    stage.setScene(scene);
                    stage.show();
                    level.createMap(numOfLevel);

                    AnimationTimer timer = new AnimationTimer() {
                        public void handle(long l) {
                            game.render();
                            game.update();
                            score.setText(String.format("Score: %d", totalScore));

                            if (timeToSub > 60) {
                                timeToSub = 0;
                                TIME--;
                            } else {
                                timeToSub++;
                            }

                            if (TIME == 0) {
                                GameOver(stage);
                            }


                            time.setText(String.format("Time: %d", TIME));
                            if (Game.changeLevel && lives >= 0) {
                                totalScoreOne = 0;
                                numOfLevel++;
                                game.reset();
                                level.createMap(numOfLevel);
                                level1.setText(String.format("Level : %d", numOfLevel));
                                Game.changeLevel = false;

                            }

                            // live moi.
                            if (game.bomberman.isRemoved()) {
                                lives--;
                                if (lives > 0) {
                                    totalScore -= totalScoreOne;
                                    totalScoreOne = 0;
                                    live.setText(String.format("Live : %d", lives));
                                    game.reset();
                                    level.createMap(numOfLevel);
                                } else {
                                    GameOver(stage);
                                }
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
                    Sound.playLoop("soundtrack");
                }

        );


        // choi AI.
        startAI.setOnMouseClicked(event -> {
            Scene scene = new Scene(paneCanvas, 1000, 470);
            stage.setScene(scene);
            stage.show();
            level.createMap(numOfLevel);
            AIBomber aiBomber = new AIBomber(game);
            AnimationTimer timer = new AnimationTimer() {
                public void handle(long l) {
                    game.render();
                    game.update();
                    score.setText(String.format("Score: %d", totalScore));

                    if (timeToSub > 60) {
                        timeToSub = 0;
                        TIME--;
                    } else {
                        timeToSub++;
                    }
                    if (TIME == 0) {
                        GameOver(stage);
                    }

                    time.setText(String.format("Time: %d", TIME));
                    if (Game.changeLevel && lives >= 0) {
                        totalScoreOne = 0;
                        numOfLevel++;
                        game.reset();
                        level.createMap(numOfLevel);
                        level1.setText(String.format("Level : %d", numOfLevel));
                        Game.changeLevel = false;
                        aiBomber.reset(game);
                    }

                    // live moi.
                    if (Game.bomberman.isRemoved()) {
                        lives--;
                        if (lives > 0) {
                            totalScore -= totalScoreOne;
                            totalScoreOne = 0;
                            live.setText(String.format("Live : %d", lives));
                            game.reset();
                            level.createMap(numOfLevel);
                            aiBomber.reset(game);
                        } else {
                            GameOver(stage);
                        }
                    }
                    aiBomber.move();
                }
            };
            timer.start();
            Sound.play("soundtrack");
        });

    }


    // hiện màn hình game over khi bomber chết or khi hết time.
    public void GameOver(Stage stage) {
        PauseTransition delay = new PauseTransition(Duration.seconds(0));
        delay.setOnFinished(event ->
        {
            stage.setScene(screen.gameOver());

        });
        delay.play();

    }

}
