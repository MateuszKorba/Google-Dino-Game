import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Path {

    public static BufferedImage getImage(String path) throws IOException {
        BufferedImage img;
        img = ImageIO.read(new File(path));
        return img;
    }

}
