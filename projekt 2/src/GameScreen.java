import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GameScreen extends JPanel implements Runnable, KeyListener {

    public static final int GAME_FIRST_STATUS = 0;
    public static final int GAME_PLAY_STATUS = 1;
    public static final int GAME_OVER_STATUS = 2;
    public static final float GRAVITY = 0.1f;
    public static final float LAND = 250;
    private Dino dino;
    private Thread thread;
    private Land land;
    private Clouds cloud;
    private Enemies enemies;
    private int score;
    private int highScore;
    private int gameStatus = GAME_FIRST_STATUS;
    private BufferedImage gameOverImage;
    private BufferedImage replayImage;
    private BufferedImage firstStatusImage;
    private BufferedImage cloud1Image;
    private BufferedImage cloud2Image;
    private BufferedImage cloud3Image;
    private AudioClip audiScoreUp;


    public GameScreen(){
        thread = new Thread(this);
        dino = new Dino();
        dino.setX(50);    //ustawienie dino 50 pikseli w prawo
        dino.setY(208);
        land = new Land(this);
        cloud = new Clouds();
        enemies = new Enemies(dino,this);
        score = 0;
        highScore = readFromFile();

        try {
            gameOverImage = Path.getImage("img/gameover.png");
            replayImage = Path.getImage("img/replay.png");
            firstStatusImage = Path.getImage("img/firstpanelgame.png");
            cloud1Image =Path.getImage("img/cloud2.png");
            cloud2Image =Path.getImage("img/cloud2.png");
            cloud3Image =Path.getImage("img/cloud2.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            audiScoreUp =  Applet.newAudioClip(new URL("file","","C:\\Users\\MaATPlay\\Desktop\\Studia\\semestr 2\\GUI\\projekt 2\\music\\scoreup.wav"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void startGame(){
        thread.start();
    }

    @Override
    public void run() {
        while (true){
            try {
                update();
                repaint();
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        switch (gameStatus){
            case GAME_PLAY_STATUS:
                dino.update();
                land.update();
                enemies.update();
                score++;
                if (score % 500 == 0){
                    audiScoreUp.play();
                }
                if(dino.getAllive() == false){
                    gameStatus = GAME_OVER_STATUS;
                }
            break;
        }
    }

    public void paint(Graphics graphics){
        graphics.setColor(Color.decode("#f7f7f7"));
        graphics.fillRect(0,0,getWidth(),getHeight());

        switch (gameStatus){
            case GAME_FIRST_STATUS:
                graphics.drawImage(cloud1Image,70,150,null);
                graphics.drawImage(cloud1Image,315 ,213,null);
                graphics.drawImage(cloud1Image,358 ,110,null);
                graphics.drawImage(firstStatusImage,125,69,null);
                break;
            case GAME_PLAY_STATUS:
                land.draw(graphics);
                dino.draw(graphics);
                cloud.draw(graphics);
                enemies.draw(graphics);
                graphics.setColor(Color.black);
                graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
                graphics.drawString("Score : " +Integer.toString(score),350,20);
                graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
                graphics.drawString("High score : " +Integer.toString(highScore),435,20);
                break;
            case GAME_OVER_STATUS:
                land.draw(graphics);
                dino.draw(graphics);
                cloud.draw(graphics);
                enemies.draw(graphics);
                if(score > highScore){
                    highScore = score;
                }
                saveToFile(highScore);
                score = 0;
                graphics.drawImage(gameOverImage,190,90,null);
                graphics.drawImage(replayImage,248,175,null);
                break;
        }
    }

    public void countScore(int score){
        this.score += score;

    }

    private void reset(){
        dino.setAllive(true);
        dino.setX(50);
        dino.setY(208);
        enemies.reset();
    }

    public void saveToFile(int highscore){
        try {
            FileWriter fileWriter = new FileWriter("score.txt");
            fileWriter.write(String.valueOf(highscore));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readFromFile(){
        int zm = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader("score.txt");
            zm = fileReader.read();
            while (zm != -1){
                stringBuilder.append((char) zm);
                zm = fileReader.read();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(stringBuilder.substring(0));
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if (gameStatus == GAME_FIRST_STATUS){
                    gameStatus = GAME_PLAY_STATUS;
                }else if(gameStatus == GAME_PLAY_STATUS){
                    dino.jump();
                }else if(gameStatus == GAME_OVER_STATUS){
                    reset();
                    gameStatus = GAME_PLAY_STATUS;
                }
            break;
        }
    }
}
