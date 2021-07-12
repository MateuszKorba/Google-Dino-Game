import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Cactus extends Enemy{

    private BufferedImage imageCactus;
    private Rectangle rectangle;
    private int positionX;
    private int positionY;
    private Dino dino;
    private boolean gotScore = false;

    public Cactus(Dino dino) {
        rectangle = new Rectangle();
        try {
            this.dino = dino;
            imageCactus = Path.getImage("img/cactus1.png");
            positionX = 200;
            positionY = 205;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        positionX = positionX - 2;
        rectangle.x = positionX;
        rectangle.y = positionY;
        rectangle.width = imageCactus.getWidth();
        rectangle.height = imageCactus.getHeight();
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(imageCactus, positionX, positionY, null);
    }

    public void setPositionX(int x) {
        positionX = x;
    }

    public void setPositionY(int y) {
        positionY = y;
    }

    public void setImageCactus(BufferedImage imageCactus) {
        this.imageCactus = imageCactus;
    }

    @Override
    public Rectangle getBound() {
        return rectangle;
    }

    @Override
    public boolean outOfScreen() {
        return (positionX + imageCactus.getWidth() < 0);
    }


}
