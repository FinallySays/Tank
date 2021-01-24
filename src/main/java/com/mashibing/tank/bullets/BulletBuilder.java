package com.mashibing.tank.bullets;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Tank;

import java.util.List;

/**
 * @Author : shiwp
 * @Date : 2021/1/24 2:46 下午
 * @Description : com.mashibing.tank
 * @Version : 1.0
 */
public interface BulletBuilder {
    List<Bullet> create(Tank tank);
}
