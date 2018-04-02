package com.tuyue.util.ss;

import com.tuyue.util.RandomUtil;
import redis.clients.jedis.Jedis;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/4/2.
 */
public class test {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        String random = RandomUtil.random(4);
        jedis.set("15399485889", "6749");
        jedis.expire("15399485889", 60);
        String s = jedis.get("15399485889");
        System.out.println(s);
    }
}
