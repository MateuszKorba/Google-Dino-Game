import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemies {

    private List <Enemy> listofEnemies;
    private BufferedImage imageCactus1;
    private BufferedImage imageCactus2;
    private BufferedImage imageCactus3;
    private BufferedImage imageCactus4;
    private BufferedImage imageCactus5;
    private Random randomImage;
    private Dino dino;
    private GameScreen gameScreen;
    private AudioClip audioDead;

    public Enemies(Dino dino, GameScreen gameScreen) {
        try {
            listofEnemies = new ArrayList<>();
            imageCactus1 = Path.getImage("img/cactus1.png");
            imageCactus2 = Path.getImage("img/cactus2.png");
            imageCactus3 = Path.getImage("img/cactus3.png");
            imageCactus4 = Path.getImage("img/cactus4.png");
            imageCactus5 = Path.getImage("img/cactus5.png");
            randomImage = new Random();
            listofEnemies.add(getRandomCactus());
            this.dino = dino;
            this.gameScreen = gameScreen;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            audioDead =  Applet.newAudioClip(new URL("file","","C:\\Users\\MaATPlay\\Desktop\\Studia\\semestr 2\\GUI\\projekt 2\\music\\dead.wav"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        for (Enemy enemy : listofEnemies) {
            enemy.update();
            if (enemy.getBound().intersects(dino.getBound())){
                audioDead.play();
                dino.setAllive(false);
            }
        }
        Enemy enemy = listofEnemies.get(0);
        if(enemy.outOfScreen()){
            listofEnemies.remove(enemy);
           listofEnemies.add(getRandomCactus());
        }
    }

    public void draw(Graphics graphics){
        for(Enemy enemy : listofEnemies){
            enemy.draw(graphics);
        }
    }

    public void reset(){
        listofEnemies.clear();
        listofEnemies.add(getRandomCactus());
    }

    private Cactus getRandomCactus() {
        Cactus cactus = new Cactus(dino);
        cactus.setPositionX(550);
        cactus.setPositionY(205);
        if (randomImage.nextBoolean()) {
            cactus.setImageCactus(imageCactus1);
        }else if (randomImage.nextBoolean()) {
            cactus.setImageCactus(imageCactus2);
        }else if (randomImage.nextBoolean()) {
            cactus.setImageCactus(imageCactus3);
        }else if (randomImage.nextBoolean()) {
            cactus.setImageCactus(imageCactus4);
        }else{
            cactus.setImageCactus(imageCactus5);
        }
        return cactus;
    }

}
