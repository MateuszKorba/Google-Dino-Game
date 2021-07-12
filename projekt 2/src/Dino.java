import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Dino {

    private float x = 0;
    private float y = 0;
    private float Yspeed = 0;
    private Animation animationRun;
    private Rectangle rectangle;
    private boolean isAllive = true;
    private AudioClip audioJump;

    public Dino()  {
        animationRun = new Animation(100);
        try {
            animationRun.addImage(Path.getImage("img/dino1.png"));
            animationRun.addImage(Path.getImage("img/dino2.png"));
            rectangle = new Rectangle();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            audioJump =  Applet.newAudioClip(new URL("file","","C:\\Users\\MaATPlay\\Desktop\\Studia\\semestr 2\\GUI\\projekt 2\\music\\jump.wav"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        animationRun.update();
        if(y >= GameScreen.LAND - animationRun.getFrame().getHeight()){
            Yspeed = 0;
            y = GameScreen.LAND - animationRun.getFrame().getHeight();
        }else {
            Yspeed = Yspeed + GameScreen.GRAVITY;
            y = y + Yspeed;
        }
        rectangle.x = (int)x;
        rectangle.y = (int)y;
        rectangle.width = animationRun.getFrame().getWidth();
        rectangle.height = animationRun.getFrame().getHeight();
    }

    public Rectangle getBound(){
        return rectangle;
    }

    public void draw(Graphics graphics){
        graphics.setColor(new Color(0,0,0,0));
        graphics.drawRect((int) x, (int) y, animationRun.getFrame().getWidth(), animationRun.getFrame().getHeight());
        graphics.drawImage(animationRun.getFrame(),(int)x,(int)y,null);
    }

    public void jump(){
        audioJump.play();
        Yspeed = -4;
        y = y+Yspeed;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getYspeed() {
        return Yspeed;
    }

    public void setYspeed(float Yspeed) {
        this.Yspeed = Yspeed;
    }

    public void setAllive(boolean allive){
        isAllive = allive;
    }

    public boolean getAllive() {
        return isAllive;
    }

}
