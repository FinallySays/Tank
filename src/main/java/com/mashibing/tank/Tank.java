package com.mashibing.tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author : shiwp
 * @Date : 2021/1/23 2:38 下午
 * @Description : com.mashibing.tank
 * @Version : 1.0
 */
public class Tank {

    private int x, y;
    private Direction dir;
    private final int speed = 5;
    private boolean moving = false;
    private TankFrame tf;
    private int width;
    private int height;
    private boolean living = true;
    private Random random = new Random();
    private Group group;
    private int maxX;
    private int maxY;
    private int bulletType = 0;
    private BulletFactory bulletFactory = BulletFactory.getInstance();

    public Tank(int x, int y, Direction dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        width = group == Group.BAD ? ResourceManager.getInstance().getBadTankUp().getWidth() :
                ResourceManager.getInstance().getGoodTankUp().getWidth();
        height = group == Group.BAD ? ResourceManager.getInstance().getBadTankUp().getHeight() :
                ResourceManager.getInstance().getGoodTankUp().getHeight();
        maxX = TankFrame.WINDOE_WIDTH - width;
        maxY = TankFrame.WINDOW_HEIGHT - height;
        if (group == Group.GOOD) bulletType = 7;
    }

    public Tank() {
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.tanks.remove(this);
        }
        move();
        BufferedImage image = null;
        switch (dir) {
            case DOWN:
                image = group == Group.BAD ? ResourceManager.getInstance().getBadTankDown() :
                        ResourceManager.getInstance().getGoodTankDown();
                break;
            case UP:
                image = group == Group.BAD ? ResourceManager.getInstance().getBadTankUp() :
                        ResourceManager.getInstance().getGoodTankUp();
                break;
            case LEFT:
                image = group == Group.BAD ? ResourceManager.getInstance().getBadTankLeft() :
                        ResourceManager.getInstance().getGoodTankLeft();
                break;
            case RIGHT:
                image = group == Group.BAD ? ResourceManager.getInstance().getBadTankRight() :
                        ResourceManager.getInstance().getGoodTankRight();
                break;
        }
        g.drawImage(image, x, y, null);
    }

    public Group getGroup() {
        return group;
    }

    private void move() {
        if (!moving) return ;

        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
        if (group == Group.BAD && random.nextInt(100) > 92) {
            this.fire();
        }
        if (group == Group.BAD && random.nextInt(100) > 92) {
            randomDir();
        }

        boundsCheck();
    }

    private void boundsCheck() {
        boolean needRedir = false;
        if (x > maxX) {
            x = maxX;
            needRedir = true;
        }
        if (x < 0) {
            x = 0;
            needRedir = true;
        } if (y > maxY) {
            y = maxY;
            needRedir = true;
        }
        if (y < 30) {
            y = 30;
            needRedir = true;
        }
        if (needRedir && group == Group.BAD) {
            randomDir();
        }
    }

    private void randomDir() {
        dir = Direction.values()[random.nextInt(4)];
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        tf.bullets.addAll(bulletFactory.create(bulletType, this));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void die() {
        this.living = false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void changeBullet() {
        bulletType = bulletFactory.changeBullet(bulletType);
    }

    public Direction getDir() {
        return dir;
    }

    public TankFrame getTf() {
        return tf;
    }
}