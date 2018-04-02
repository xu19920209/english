package com.tuyue.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/11.
 */
public class RandomUtil {
    public static String randomString(int length){
        //String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        String base="0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int number = 0;
        for (int i = 0; i < length; i++) {
            number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String random(int length){
        //String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        String base="0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int number = 0;
        for (int i = 0; i < length; i++) {
            number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
