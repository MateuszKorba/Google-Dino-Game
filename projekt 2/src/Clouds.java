import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Clouds {

    private BufferedImage imageCloud1;
    private BufferedImage imageCloud2;
    private BufferedImage imageCloud3;
    private BufferedImage imageCloud4;
    private BufferedImage imageCloud5;
    private BufferedImage imageCloud6;
    private BufferedImage imageCloud7;

    public Clouds() {
        try {
            imageCloud1 = Path.getImage("img/cloud1.png");
            imageCloud2 = Path.getImage("img/cloud2.png");
            imageCloud3 = Path.getImage("img/suncloud.png");
            imageCloud4 = Path.getImage("img/cloud1.png");
            imageCloud5 = Path.getImage("img/cloud2.png");
            imageCloud6 = Path.getImage("img/cloud3.png");
            imageCloud7 = Path.getImage("img/cloud3.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(imageCloud1,300,10,null);
        graphics.drawImage(imageCloud2,170,10,null);
        graphics.drawImage(imageCloud6,130,35,null);
        graphics.drawImage(imageCloud3,210,20,null);
        graphics.drawImage(imageCloud4,350,20,null);
        graphics.drawImage(imageCloud5,410,15,null);
        graphics.drawImage(imageCloud5,410,15,null);
        graphics.drawImage(imageCloud7,20,15,null);
    }

}
