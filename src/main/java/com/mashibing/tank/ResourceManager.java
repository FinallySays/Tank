package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author : shiwp
 * @Date : 2021/1/23 5:43 下午
 * @Description : com.mashibing.tank
 * @Version : 1.0
 */
public class ResourceManager {


    public BufferedImage goodTankLeft, goodTankRight, goodTankUp, goodTankDown;
    public BufferedImage badTankLeft, badTankRight, badTankUp, badTankDown;
    public BufferedImage bulletLeft, bulletRight, bulletUp, bulletDown;
    public BufferedImage[] explore = new BufferedImage[16];


    private ResourceManager() {
        ClassLoader classLoader = ResourceManager.class.getClassLoader();
        try {
            goodTankUp = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank1.png"));
            goodTankLeft = ImageUtil.rotateImage(goodTankUp, -90);
            goodTankRight = ImageUtil.rotateImage(goodTankUp, 90);
            goodTankDown = ImageUtil.rotateImage(goodTankUp, 180);
            badTankUp = ImageIO.read(classLoader.getResourceAsStream("images/BadTank2.png"));
            badTankLeft = ImageUtil.rotateImage(badTankUp, -90);
            badTankRight = ImageUtil.rotateImage(badTankUp, 90);
            badTankDown = ImageUtil.rotateImage(badTankUp, 180);
            bulletLeft = ImageIO.read(classLoader.getResourceAsStream("images/bulletL.gif"));
            bulletRight = ImageIO.read(classLoader.getResourceAsStream("images/bulletR.gif"));
            bulletUp = ImageIO.read(classLoader.getResourceAsStream("images/bulletU.gif"));
            bulletDown = ImageIO.read(classLoader.getResourceAsStream("images/bulletD.gif"));
            for (int i = 0; i < 16; i++) {
                explore[i] = ImageIO.read(classLoader.getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ResourceManager getInstance() {
        return ManagerHolder.INSTANCE;
    }

    public BufferedImage getGoodTankLeft() {
        return goodTankLeft;
    }

    public BufferedImage getGoodTankRight() {
        return goodTankRight;
    }

    public BufferedImage getGoodTankUp() {
        return goodTankUp;
    }

    public BufferedImage getGoodTankDown() {
        return goodTankDown;
    }

    public BufferedImage getBadTankLeft() {
        return badTankLeft;
    }

    public BufferedImage getBadTankRight() {
        return badTankRight;
    }

    public BufferedImage getBadTankUp() {
        return badTankUp;
    }

    public BufferedImage getBadTankDown() {
        return badTankDown;
    }

    public BufferedImage getBulletLeft() {
        return bulletLeft;
    }

    public BufferedImage getBulletRight() {
        return bulletRight;
    }

    public BufferedImage getBulletUp() {
        return bulletUp;
    }

    public BufferedImage getBulletDown() {
        return bulletDown;
    }

    public BufferedImage[] getExplore() {
        return explore;
    }

    private static class ManagerHolder{
        private static ResourceManager INSTANCE = new ResourceManager();
    }

}