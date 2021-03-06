package com.mashibing.tank;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author : shiwp
 * @Date : 2021/1/23 9:42 上午
 * @Description : com.mashibing.tank
 * @Version : 1.0
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        for (int i = 0; i < PropertiesManager.getInstance().getInt("initialCount"); i++) {
            Tank tank = new Tank(50 + i * 10, 100, Direction.DOWN, tankFrame, Group.BAD);
            tank.setMoving(true);
            tankFrame.tanks.add(tank);

        }
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}