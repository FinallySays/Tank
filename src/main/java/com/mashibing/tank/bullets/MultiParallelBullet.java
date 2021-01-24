package com.mashibing.tank.bullets;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Direction;
import com.mashibing.tank.Tank;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : shiwp
 * @Date : 2021/1/24 2:57 下午
 * @Description : com.mashibing.tank.bullets
 * @Version : 1.0
 */
public class MultiParallelBullet implements BulletBuilder {

    private final int count;

    public MultiParallelBullet(int count) {
        this.count = count;
    }

    @Override
    public List<Bullet> create(Tank tank) {
        List<Bullet> list = new ArrayList<>();
        int dx = (tank.getWidth() - Bullet.WIDTH * count) / (count + 1);
        int dy = (tank.getHeight() - Bullet.HEIGHT * count) / (count + 1);
        Direction dir = tank.getDir();

        for (int i = 0; i < count; i++) {
            if (dir == Direction.UP || dir == Direction.DOWN) {
                list.add(new Bullet(tank.getX() + (i + 1) * dx + i * Bullet.WIDTH,
                        tank.getY() + tank.getHeight() / 2 - Bullet.WIDTH / 2,
                        tank.getDir(), tank.getTf(), tank.getGroup()));
            } else {
                list.add(new Bullet(tank.getX() + tank.getWidth() / 2 - Bullet.WIDTH / 2,
                        tank.getY() + (i + 1) * dy + i * Bullet.HEIGHT,
                        tank.getDir(), tank.getTf(), tank.getGroup()));
            }
        }

        return list;
    }
}