package com.tuyue.appModules.weChatGongZhong.biz;

import com.tuyue.appModules.weChatGongZhong.util.WeiUtil;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/18.
 */
public class WeiChatGongZhone {
    /**
     * @Author: 徐慷慨
     * @Description: 创建自定义菜单
     * @Date: 10:18 2017/12/18
     */
    public void createMenu(){
         //获取accessToken
        String accessToken = WeiUtil.accessToken();
        if(accessToken!=null){
         String str="{\n" +
                 "     \"button\":[\n" +
                 "     {    \n" +
                 "          \"type\":\"view\",\n" +
                 "          \"name\":\"我要报名\",\n" +
                 "          \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe3a90d951480bf12&redirect_uri=http://www.ai4001120150.com/aiwebapp/list.html?page=1&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect\"\n" +
                 "      },\n" +
                 "      {\n" +
                 "           \"name\":\"个人中心\",\n" +
                 "           \"sub_button\":[\n" +
                 "           {\n" +
                 "                    \"type\": \"scancode_waitmsg\", \n" +
                 "                    \"name\": \"扫码签到\", \n" +
                 "                    \"key\": \"sacn01\" \n" +
                 "                   \n" +
                 "            },\n" +
                 "            {\n" +
                 "                 \"type\":\"view\",\n" +
                 "                 \"name\":\"注册\",\n" +
                 "                  \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe3a90d951480bf12&redirect_uri=http://www.ai4001120150.com/aiwebapp/list.html?page=3&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect\"\n" +
                 "             },\n" +
                 "            {\n" +
                 "               \"type\":\"view\",\n" +
                 "               \"name\":\"我的课程\",\n" +
                 "              \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe3a90d951480bf12&redirect_uri=http://www.ai4001120150.com/aiwebapp/list.html?page=2&response_type=code&scope=snsapi_userinfo&state=1&connect_redirect=1#wechat_redirect\"\n" +
                 "            }]\n" +
                 "       }]\n" +
                 "}";
          WeiUtil.createMenu(str,accessToken);
        }
    }
    public static void main(String[] args) {
        String accessToken = WeiUtil.accessToken();
       System.out.println("::::::" + accessToken);
    }
}
