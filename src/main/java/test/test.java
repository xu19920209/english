package test;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tuyue.pojo.Encourage;
import com.tuyue.util.JPushUtil;
import com.tuyue.util.Md5Util;
import com.tuyue.weichat.WeChatPayMD5;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/9.
 */
public class test {
    public static void main (String[] args){
//        String s = "1,2,3,5,2,4,6,4,9,8";
//        String[] split = s.split(",");
//        List<String> list = Arrays.asList(split);
//        Collections.sort(list);
//        RandomUtil random =new RandomUtil();
//        int nextInt = random.nextInt(8);
//        System.out.println(nextInt);
//        Integer A=null;
//        Integer b=null;
//        float num= (float)(1)/3;
//        DecimalFormat df = new DecimalFormat("0.0");//格式化小数
//        String s = df.format(num);
//        System.out.println(s);

//        List<Student> list = new ArrayList<Student>();
//
//        //创建3个学生对象，年龄分别是20、19、21，并将他们依次放入List中
//        Student s1 = new Student();
//        s1.setAge(20);
//        Student s2 = new Student();
//        s2.setAge(19);
//        Student s3 = new Student();
//        s3.setAge(21);
//        list.add(s1);
//        list.add(s2);
//        list.add(s3);
//
//        System.out.println("排序前："+list);
//
//        Collections.sort(list, new Comparator<Student>(){
//
//            /*
//             * int compare(Student o1, Student o2) 返回一个基本类型的整型，
//             * 返回负数表示：o1 小于o2，
//             * 返回0 表示：o1和o2相等，
//             * 返回正数表示：o1大于o2。
//             */
//            public int compare(Student o1, Student o2) {
//
//                //按照学生的年龄进行升序排列
//                if(o1.getAge() > o2.getAge()){
//                    return -1;
//                }
//                if(o1.getAge() == o2.getAge()){
//                    return 0;
//                }
//                return 1;
//            }
//        });
//        System.out.println("排序后："+list);
//    }
//}
//class Student{
//
//    private int age;
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//    @Override
//    public String toString() {
//        return getAge()+"";

    /*    int a=1099;
        int b=93;
        double f1 = new BigDecimal((float)a/b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("ddd==="+f1);*/
//        HashMap<String, String> maps = new HashMap<String, String>();
//        maps.put("type", String.valueOf(1));  //type  = 1 布置作业 type = 2  老师鼓励学生了
//        JPushUtil.sendMsgToApp("你有新的作业了","老师布置作业了，快去完成","type",maps,"E4A9C734BB4EA5DD5C0766925CA1DFB2");
//String ss="2017年11月20";
//        Timestamp timestamp = Timestamp.valueOf(ss+" 00:00:00");
//        System.out.println(timestamp);
//        Calendar calendar =new GregorianCalendar(2017,12,8);
//        System.out.println(calendar);
//        Date date=new Date();
//        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
//        String format = dateFm.format(date);
//        System.out.println(format);
      String  result = WeChatPayMD5.MD5Encode("appId=wxe3a90d951480bf12&nonceStr=BssBdOiOgO4mQykL&package=prepay_id=wx20171227144059e17bca82380085900121&signType=MD5&timestamp=1514356860&key=eE6Pamc354YtqWsKV6k4AJehQoRZqEfQ").toUpperCase();
        System.out.println(result);
        String s = Md5Util.toMD5("appId=wxe3a90d951480bf12&nonceStr=BssBdOiOgO4mQykL&package=prepay_id=wx20171227144059e17bca82380085900121&signType=MD5&timestamp=1514356860&key=eE6Pamc354YtqWsKV6k4AJehQoRZqEfQ");
        System.out.println(s);
    }
}
