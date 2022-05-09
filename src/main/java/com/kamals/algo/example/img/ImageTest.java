package com.kamals.algo.example.img;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageTest
{
    public static void main(String[] args) throws Exception
    {
        String inFileName = "/Users/kamal.sultania/Documents/Projects/images/RC2.png";

        String outFileName = "/Users/kamal.sultania/Documents/Projects/images/RC2.jpg";

        BufferedImage image = ImageIO.read(new File(inFileName));

        System.out.println(image);

        ImageIO.write(image, "png", new File(outFileName));
    }
}
