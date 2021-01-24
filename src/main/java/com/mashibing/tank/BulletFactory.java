package com.mashibing.tank;

import com.mashibing.tank.bullets.BulletBuilder;
import com.mashibing.tank.bullets.MultiDirectionBullet;
import com.mashibing.tank.bullets.MultiParallelBullet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : shiwp
 * @Date : 2021/1/24 2:37 下午
 * @Description : com.mashibing.tank
 * @Version : 1.0
 */
public class BulletFactory {

    Map<Integer, BulletBuilder> bulletsMap = new HashMap<>();

    private BulletFactory() {
        bulletsMap.put(0, new MultiParallelBullet(1));
        bulletsMap.put(1, new MultiParallelBullet(2));
        bulletsMap.put(2, new MultiParallelBullet(3));
        bulletsMap.put(3, new MultiParallelBullet(4));
        bulletsMap.put(4, new MultiDirectionBullet(1));
        bulletsMap.put(5, new MultiDirectionBullet(2));
        bulletsMap.put(6, new MultiDirectionBullet(3));
        bulletsMap.put(7, new MultiDirectionBullet(4));
    }

    public static BulletFactory getInstance() {
        return Holder.INSTANCE;
    }

    public List<Bullet> create(int bulletType, Tank tank) {
        return bulletsMap.get(bulletType).create(tank);
    }

    public int changeBullet(int bulletType) {
        return (bulletType + 1) % bulletsMap.size();
    }


    private static class Holder {
        private static BulletFactory INSTANCE = new BulletFactory();
    }

}