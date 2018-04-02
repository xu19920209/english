package com.tuyue.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.*;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2017/11/14.
 */
public class JPushUtil {
    private static JPushClient  jPushClient = new JPushClient("f9fb0afd3b3c4aaef20b7e42","5bb0d6eda9c5b23a60b1d08f");

    /**
     * @param alert  通知标题的内容
     * @param msg   自带的消息
     * @param map   额外的消息
     * @param alias 别名
     */
    public static  void sendMsgToApp(String alert,String title, String msg, @NotNull Map<String,String> map , String...alias) {
        try {
            jPushClient.sendPush(PushPayload.newBuilder()
                    .setPlatform(Platform.android())  //ios , android
                    .setAudience(Audience.alias(alias))
                    .setNotification(Notification.android(alert,title,map))
                    .setMessage(Message.newBuilder()
                            .setMsgContent(msg)
                            .addExtras(map)
                           .build()
                    )
                    .build());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
    public static  void sendMsgToIos(String alert,String title, String msg, @NotNull Map<String,String> map , String...alias) {
        try {
            jPushClient.sendPush(PushPayload.newBuilder()
                    .setPlatform(Platform.ios())  //ios , android
                    .setAudience(Audience.alias(alias))
                    .setNotification(Notification.newBuilder()
                            .addPlatformNotification(IosNotification.newBuilder()
                    .setBadge(0).setSound("defalut").setAlert(alert).addExtras(map).build()).build())
                    .setOptions(Options.newBuilder().setApnsProduction(true).build())
                    .build());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HashMap<String, String> maps = new HashMap<String, String>();
        maps.put("type", String.valueOf(1));
        //sendMsgToIos("你有新的作业了","","老师布置作业了",maps,"175A2F9207F25959FE6913F3D19159ED");
       sendMsgToIos("你有新的作业了","","老师布置作业了",maps,"175A2F9207F25959FE6913F3D19159ED");
    }
}
