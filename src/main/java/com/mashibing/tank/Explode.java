package com.mashibing.tank;

import java.awt.*;

/**
 * @Author : shiwp
 * @Date : 2021/1/23 10:28 下午
 * @Description : com.mashibing.tank
 * @Version : 1.0
 */
public class Explode {

    public static int WIDTH = ResourceManager.getInstance().getExplore()[0].getWidth();
    public static int HEIGHT = ResourceManager.getInstance().getExplore()[0].getHeight();

    private int x, y;
    private boolean living = true;

    private TankFrame tf;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint (Graphics g) {
        if (step < ResourceManager.getInstance().getExplore().length) {
            g.drawImage(ResourceManager.getInstance().getExplore()[step++], x, y, null);
        }
    }
}