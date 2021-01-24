package com.mashibing.tank;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author : shiwp
 * @Date : 2021/1/23 4:40 下午
 * @Description : com.mashibing.tank
 * @Version : 1.0
 */
public class ImageTest {

    @Test
    public void test() {
        BufferedImage image = null;
        try {
//            image = ImageIO.read(new File(""));
            image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/0.gif"));
            assertNotNull(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}