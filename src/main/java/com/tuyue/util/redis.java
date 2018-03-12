package com.tuyue.util;

import redis.clients.jedis.Jedis;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/2/28.
 */
public class redis {

    public static void main (String[] args){
        Jedis jedis=new Jedis("123.56.19.93",6379);
        String s = jedis.get("name");
        System.out.println(s);
    }
}
