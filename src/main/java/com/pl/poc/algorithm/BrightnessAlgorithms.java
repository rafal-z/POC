package com.pl.poc.algorithm;

import com.pl.poc.model.ImagesModel;

import java.awt.image.BufferedImage;

/**
 * Created by Rafał on 2017-07-30.
 */
public class BrightnessAlgorithms {
    public static ImagesModel execute(ImagesModel model, Integer value){
        BufferedImage srcImage = model.getSrcImage();
        BufferedImage dscImage = model.getDstImage();

        for (int y = 0; y < model.getSrcImage().getHeight(); ++y)
        {
            for (int x = 0; x < model.getSrcImage().getWidth(); ++x)
            {
                int rgbSrc = srcImage.getRGB(x,y);
                int rgbDsc = changeBrightness(rgbSrc, value);
                dscImage.setRGB(x,y, rgbDsc);
            }
        }
        model.setDstImage(dscImage);
        return model;
    }

    // Funkcja zmieniajaca jasnosc koloru rgb o wartosc db
    public static int changeBrightness(int rgb, int db)
    {
        // Rozbior koloru na skladowe R, G, B
        int r = ImageAlgorithms.jred(rgb);
        int g = ImageAlgorithms.jgreen(rgb);
        int b = ImageAlgorithms.jblue(rgb);

        // Modyfikacja skladowych razem z przypilnowaniem zakresow 0-255
        r = ImageAlgorithms.clamp(r + db, 0, 255);
        g = ImageAlgorithms.clamp(g + db, 0, 255);
        b = ImageAlgorithms.clamp(b + db, 0, 255);

        // Zlozenie skladowych R, G, B w jeden kolor i zwrocenie jego wartosci
        return ImageAlgorithms.jrgb(r, g, b);
    }
}
