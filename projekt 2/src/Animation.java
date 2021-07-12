import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {

    private List<BufferedImage> listofFramesImages;
    private int index = 0;
    private int speedTime;
    private long prevspeedTime;

    public Animation(int speedTime) {
        this.speedTime = speedTime;
        listofFramesImages = new ArrayList<>();
    }

    public void addImage(BufferedImage image){
        listofFramesImages.add(image);
    }

    public void update(){
        if(System.currentTimeMillis() - prevspeedTime > speedTime) {
            index++;
            if (index >= listofFramesImages.size()) {
                index = 0;
            }
            prevspeedTime = System.currentTimeMillis();
        }
    }

    public BufferedImage getFrame(){
        if(listofFramesImages.size()>0){
            return listofFramesImages.get(index);
        }
        return null;
    }

}
