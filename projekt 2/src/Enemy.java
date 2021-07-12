import java.awt.*;

public abstract class Enemy {

    public abstract Rectangle getBound();
    public abstract void draw(Graphics graphics);
    public abstract void update();
    public abstract boolean outOfScreen();

}
