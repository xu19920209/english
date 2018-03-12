package com.tuyue.task;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Grole;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/15.
 */
//@Component("TaskJob")
public class task {

    @Autowired
    private IBaseDao<Grole> groleIBaseDao;

    @Scheduled(fixedDelay = 1000 * 60 * 2)
    public void doSomething() throws Exception {
//        List<Grole> list = groleIBaseDao.findList(" from Grole");
//        System.out.println("::"+list.size());
//        System.out.println("定时任务");
        String apiurl = "http://124.47.6.211/ssh4/Adult/list";
        System.out.println(apiurl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(apiurl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            result = responseContent;
            System.out.println(result);
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
        }
    }
}

