package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : shiwp
 * @Date : 2021/1/23 9:57 上午
 * @Description : com.mashibing.tank
 * @Version : 1.0
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(600, 200, Direction.DOWN, this, Group.GOOD);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
    static final int WINDOE_WIDTH = 800, WINDOW_HEIGHT = 600;

    public TankFrame() {
        setSize(WINDOE_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        addKeyListener(new MykeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(WINDOE_WIDTH, WINDOW_HEIGHT);
        }

        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, WINDOE_WIDTH, WINDOW_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {

        myTank.paint(g);
        for (int i = 0; i < bullets.size(); ++i) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                if (bullets.get(i).getGroup() == tanks.get(j).getGroup()) {
                    continue;
                }
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }

    class MykeyListener extends KeyAdapter {

        boolean bl = false;
        boolean br = false;
        boolean bu = false;
        boolean bd = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
            }
            setMainTankDir();
            repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_SPACE :
                    myTank.fire();
                    break;
                case KeyEvent.VK_CONTROL :
                    myTank.changeBullet();
                    break;
            }
            setMainTankDir();
        }


        private void setMainTankDir() {
            if (!(bl || br || bu || bd)) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bl) myTank.setDir(Direction.LEFT);
                if (br) myTank.setDir(Direction.RIGHT);
                if (bu) myTank.setDir(Direction.UP);
                if (bd) myTank.setDir(Direction.DOWN);
            }
        }
    }


}