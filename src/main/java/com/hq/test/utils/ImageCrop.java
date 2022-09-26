package com.hq.test.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author lichaojie
 * @date 2022/9/21 16:13
 * @ClassName ImageCrop
 **/
public class ImageCrop {
    public static void main(String[] agrs) throws IOException {
        String originalImg = "C:\\Users\\PC\\Desktop\\sfz1.jpg";
        // 读入大图
        File file = new File(originalImg);
        FileInputStream fis = new FileInputStream(file);
        System.out.println(file.exists());
        BufferedImage image = ImageIO.read(fis);
        /*
         * 假设我需要切割的四点坐标为a(20,30)b(200,40)c(30,200)d(200,210)
         * 起始坐标为(最小的x,最小的y)
         * 此时的实际切割坐标应为由a为起始坐标,width为(x坐标最大的点的x - a的x坐标),height为(y坐标最大的点的y - a的y坐标)
         * 此时的width为180,height为180
         */
        //切割图片
        BufferedImage bf = new BufferedImage(296, 346, image.getType());
        Graphics2D graphics2D = bf.createGraphics();
        graphics2D.drawImage(image, 418, 852, 763, 853,418, 1148,  764, 1148, null);
        graphics2D.dispose();
        //输出图片
        Random random = new Random();
        int j = random.nextInt(1000);
        ImageIO.write(bf, "jpg", new File("C:\\Users\\PC\\Desktop\\0.jpg"));
        System.out.println("完成切割");
    }
}
