import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Land {

    private List<LandImage> listofIamgeLand;
    private BufferedImage imageLand1;
    private BufferedImage imageLand2;
    private BufferedImage imageLand3;
    private Random randomLandImage;


    public Land(GameScreen gameScreen) {
        try {
            imageLand1 = Path.getImage("img/land1.png");
            imageLand2 = Path.getImage("img/land2.png");
            imageLand3 = Path.getImage("img/land3.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        randomLandImage = new Random();
        listofIamgeLand = new ArrayList<>();
        int numberodLandImage = 550 / imageLand2.getWidth() +50;
        for (int i = 0; i < numberodLandImage; i++) {
            LandImage landImage = new LandImage();
            landImage.positionX = (i * imageLand2.getWidth());
            landImage.image = getImageLand();
            listofIamgeLand.add(landImage);
        }
    }

    public void draw(Graphics graphics) {
        for (LandImage landImage : listofIamgeLand) {
            graphics.drawImage(landImage.image, landImage.positionX, (int) GameScreen.LAND - 20, null);
        }
    }

    private BufferedImage getImageLand(){
        int number = randomLandImage.nextInt(3); // od 0 do 2
        switch (number){
            case 0:
                return imageLand1;
            case 1:
                return imageLand2;
            default:
                return imageLand3;
        }
    }

    public void update(){
        for(LandImage landImage : listofIamgeLand){
            landImage.positionX --;
        }
        if(listofIamgeLand.get(0).positionX + imageLand2.getWidth() < 0){
            listofIamgeLand.get(0).positionX = listofIamgeLand.get(listofIamgeLand.size() -1).positionX + imageLand2.getWidth();
            listofIamgeLand.add(listofIamgeLand.get(0));
            listofIamgeLand.remove(0);
        }
    }

    private class LandImage{
        int positionX;
        BufferedImage image;
    }

}
