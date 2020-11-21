package uet.oop.bomberman.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteSheet;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;

    protected Image img;

    protected boolean _removed = false;
    protected boolean killed = false;
    protected int timeToDie = 30; // thời gian diễn ra sự chết của thực thể
    protected Game game;


    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double xUnit, double yUnit, Game game) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.game = game;
    }

    public abstract void collide(); //xử lý sự kiện khi va chạm với Entity e
    public void remove() {
        _removed = true;
    } // hành động xóa thực thể
    public boolean isRemoved() {
        return _removed;
    } // hành động kiểm tra xem thực thể có bị xóa hay không

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();

    public void kill() { // hành động giết thực thể
        killed = true;
    } // hành động giết thực thể
    public boolean isKilled() {
        return killed;
    } // kiểm tra xem thực thể có bị giết hay không

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public Image getImg() {
        return img;
    }
}
