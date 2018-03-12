package com.tuyue.appModules.weChatGongZhong.bean;

import com.google.gson.annotations.SerializedName;
import com.tuyue.appModules.weChatGongZhong.util.WeiUtil;

/**
 * 项目名：AEnglishApp
 * 包名：com.tuyue.aenglishapp.entity
 * 创建者：mmcc
 * 创建时间：2017/12/26 15:22
 * 描述：TODO
 */


public class KaiKe {
    public void sendWxTemplate(String openId,String template_id,String userName,String courseName,String date,String access_token) {
        if (template_id==null) {
            template_id = "wYvuGrmgrdQ3GIe2PeNfLk6x1EP981xdyxj1MMxbfDY";
        }
        String json = "{\n" +
                "    \"touser\": \""+openId+"\", \n" +
                "    \"template_id\":\""+template_id+"\", \n" +
                "    \"data\": {\n" +
                "        \"userName\": {\n" +
                "            \"value\": \""+userName+"\", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }, \n" +
                "        \"courseName\": {\n" +
                "            \"value\": \""+date+"\", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }, \n" +
                "        \"date\": {\n" +
                "            \"value\": \""+date+"\", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }, \n" +
                "        \"remark\": {\n" +
                "            \"value\": \"欢迎再次购买！\", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        WeiUtil.sendTemplateMsg(json, access_token);
    }

    public void pay(String openId,String template_id,String title,String courseName,String time,String addr,String access_token) {
        if (template_id==null) {
            template_id = "wO7RyHGgvoFQCMR_ynIr-GGGPQwIHMJxXvwyaqbq3WI";
        }
        String json = "{\n" +
                "    \"touser\": \""+openId+"\", \n" +
                "    \"template_id\":\""+template_id+"\", \n" +
                "    \"data\": {\n" +
                "        \"first\": {\n" +
                "            \"value\": \""+title+"\", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }, \n" +
                "        \"class\": {\n" +
                "            \"value\": \""+courseName+"\", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }, \n" +
                "        \"time\": {\n" +
                "            \"value\": \""+time+"\", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }, \n" +
                "        \"add\": {\n" +
                "            \"value\": \""+addr+"\", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }, \n" +
                "        \"remark\": {\n" +
                "            \"value\": \"欢迎再次购买！\", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        System.out.println("::::::::::::::::::::"+json);
        WeiUtil.sendTemplateMsg(json, access_token);
    }


}
