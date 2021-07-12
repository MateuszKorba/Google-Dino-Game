import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    GameScreen gameScreen;

    public Window(){
        this.setTitle("TRexGame by Mateusz Korba");
        this.setSize(550,350);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img\\logo.png"));
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
    }

    public void startGame(){
        gameScreen.startGame();
    }

}
