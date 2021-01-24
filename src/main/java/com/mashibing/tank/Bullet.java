package com.mashibing.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author : shiwp
 * @Date : 2021/1/23 3:22 下午
 * @Description : com.mashibing.tank
 * @Version : 1.0
 */
public class Bullet {

    private final Group group;
    private int x, y;
    private final int speed = 10;
    private Direction dir;
    public static final int WIDTH = ResourceManager.getInstance().getBulletDown().getWidth();
    public static final int HEIGHT = ResourceManager.getInstance().getBulletDown().getHeight();
    private TankFrame tf = null;
    private boolean living = true;


    public Bullet(int x, int y, Direction dir, TankFrame tf,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public void paint(Graphics g) {
        if (x < 0 || x > TankFrame.WINDOE_WIDTH || y < 0 || y > TankFrame.WINDOW_HEIGHT || !living) {
            tf.bullets.remove(this);
        } else {
            move();
            BufferedImage image = null;
            switch (dir) {
                case DOWN:
                    image = ResourceManager.getInstance().getBulletDown();
                    break;
                case UP:
                    image = ResourceManager.getInstance().getBulletUp();
                    break;
                case LEFT:
                    image = ResourceManager.getInstance().getBulletLeft();
                    break;
                case RIGHT:
                    image = ResourceManager.getInstance().getBulletRight();
                    break;
            }
            g.drawImage(image, x, y, null);
        }
    }

    private void move() {
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
    }

    public Group getGroup() {
        return group;
    }

    public void collideWith(Tank tank) {
        final int dx1 = x,
                dy1 = y,
                dx2 = x + ResourceManager.getInstance().getBulletDown().getWidth(),
                dy2 = y + ResourceManager.getInstance().getBulletDown().getHeight(),
                tx1 = tank.getX(),
                ty1 = tank.getY(),
                tx2 = tank.getX() + tank.getWidth(),
                ty2 = tank.getY() + tank.getHeight();

        if (!(dx2 < tx1 ||
                tx2 < dx1 ||
                dy2 < ty1 ||
                ty2 < dy1)) {
            tank.die();
            this.die();
            tf.explodes.add(new Explode(tank.getX(), tank.getY(), tf));
        }

    }

    private void die() {
        living = false;
    }
}