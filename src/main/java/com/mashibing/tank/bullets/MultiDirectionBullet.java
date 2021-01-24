package com.mashibing.tank.bullets;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Direction;
import com.mashibing.tank.Tank;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : shiwp
 * @Date : 2021/1/24 3:16 下午
 * @Description : com.mashibing.tank.bullets
 * @Version : 1.0
 */
public class MultiDirectionBullet implements BulletBuilder {

    private final int count;

    public MultiDirectionBullet(int count) {
        this.count = count;
    }

    @Override
    public List<Bullet> create(Tank tank) {
        List<Bullet> list = new ArrayList<>();
        int dx = (tank.getWidth() - Bullet.WIDTH * count) / (count + 1);
        int dy = (tank.getHeight() - Bullet.HEIGHT * count) / (count + 1);

        for (int i = 0; i < count; i++) {
            for (Direction dir : Direction.values()) {
                if (dir == Direction.UP || dir == Direction.DOWN) {
                    list.add(new Bullet(tank.getX() + (i + 1) * dx + i * Bullet.WIDTH,
                            tank.getY() + tank.getHeight() / 2 - Bullet.WIDTH / 2,
                            dir, tank.getTf(), tank.getGroup()));
                } else {
                    list.add(new Bullet(tank.getX() + tank.getWidth() / 2 - Bullet.WIDTH / 2,
                            tank.getY() + (i + 1) * dy + i * Bullet.HEIGHT,
                            dir, tank.getTf(), tank.getGroup()));
                }
            }
        }

        return list;
    }
}