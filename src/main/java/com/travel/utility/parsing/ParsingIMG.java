package com.travel.utility.parsing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Парсинг фото отеля с сайта
 *
 * @author Artem Faenko
 */
public class ParsingIMG {

    public void parsingImage(String IMAGE_URL, String IMAGE_NAME) throws IOException {

        URL imageLocation = new URL(IMAGE_URL + "jpg");
        ReadableByteChannel rbc = Channels.newChannel(imageLocation.openStream());
        File file = new File("D://TourImage/temporal");
        file.mkdirs();
        FileOutputStream outputStream = new FileOutputStream("D://TourImage/temporal/"+ IMAGE_NAME +".jpg");
        outputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        outputStream.close();
    }

    public void deleteAllFiles() {
        for (File myFile : new File("D://TourImage/temporal/").listFiles())
            if (myFile.isFile()) myFile.delete();
    }


    public static void parsingImage2() throws IOException {
        String URL = "";
        String fileName = "google5";
        BufferedImage img = ImageIO.read(new URL(URL));
        File file = new File("D://TourImage/temporal/"+ fileName +".jpg");
        file.mkdirs();
        file.createNewFile();

        ImageIO.write(img, "jpg", file);

    }

}
