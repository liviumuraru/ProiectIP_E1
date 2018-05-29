package concatenation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Concat finds all UML diagrams in a folder and concatenates them in a single, final .png file
 */

public class Concat {
    /**
     * Files with the .png extension and a title different from "saved.png" are stored in a List;
     * A BuffereImage variable is created and given the maximum height of the images from the aforementioned
     * list and the sum of their widths;
     * Images are concatenate and drawn in a final image titled "saved.png";
     * @param dirPath
     * @throws IOException
     */
    public void run(File dirPath) throws IOException {
        File path = new File(String.valueOf(dirPath));
        System.out.println(dirPath);
        List<File> toConcat = new ArrayList<File>();
        int width = 1;
        int height = 1;

        File[] files = path.listFiles();
        if (files != null)
            for (File file : files) {
                if (file.getName().endsWith(".png") && !file.getName().equalsIgnoreCase("saved.png")) {
                    toConcat.add(file);
                }
            }

        else return;


        List<BufferedImage> images = new ArrayList<BufferedImage>();
        BufferedImage current;

        for(File aux : toConcat)
        {
            current = ImageIO.read(aux);
            System.out.println(aux.getName());
            images.add(current);
        }

        for (BufferedImage image : images)
        {
            width = image.getWidth() + width;
            height = Math.max(height, image.getHeight());
        }

        BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB );
        Graphics g = combined.getGraphics();

        if(!images.isEmpty()){
            g.drawImage(images.get(0), 0, 0, null);

            int sum = images.get(0).getWidth();

            for(int i = 1; i < images.size() ; ++i)
            {
                g.drawImage(images.get(i), sum , 0, null);
                sum = sum + images.get(i).getWidth();
            }

            File outputfile = new File(path + "\\saved.png");
            ImageIO.write(combined, "png", outputfile);
            System.out.println("Image created");
        }

    }

   /* public static void main (String[] argc) throws IOException {
        run("D:\\Anul 2\\ProiectIP\\ProiectIPM3\\reverse_java");

    }*/
}
