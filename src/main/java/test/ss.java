package test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.tuyue.aLi.AliParam;
import com.tuyue.aLi.AliUtil;
import com.tuyue.aLi.Base64;
import com.tuyue.aLi.Base64Util;
import com.tuyue.weichat.WeChatPaySignature;
import com.tuyue.weichat.WeChatUtil;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/19.
 */
public class ss {
    public static void main(String[] args) throws IllegalAccessException, AlipayApiException {
        String xmlStr="<xml><gmt_create>2017-12-20 14:51:31</gmt_create><charset>utf-8</charset><seller_email>aaai@ai4001120150.com</seller_email><subject>aa</subject><sign>TS0lRcZrYHWP/3s/dRUercRyVq/TtHHpRaY7RZUMuzwbIGJBGDsZpLcZmKrgj8BTdddacIjfu+lvf1TDd7rpUzJNs7ijoFLi1Ah8a+2FFKNYn8NxKe4YCuEX9NskGr7xyfhMLrXb1nQUTRXuChBicyqM8H29zGBLHxMEuA6q/9c=</sign><body>aa</body><buyer_id>2088802629714474</buyer_id><invoice_amount>0.01</invoice_amount><notify_id>5be5c67241b6fb994d58a5a52d42fd5jmm</notify_id><fund_bill_list>[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}]</fund_bill_list><notify_type>trade_status_sync</notify_type><trade_status>TRADE_SUCCESS</trade_status><receipt_amount>0.01</receipt_amount><app_id>2017121400753768</app_id><buyer_pay_amount>0.01</buyer_pay_amount><sign_type>RSA</sign_type><seller_id>2088821769587661</seller_id><gmt_payment>2017-12-20 14:51:31</gmt_payment><notify_time>2017-12-20 14:51:32</notify_time><version>1.0</version><out_trade_no>151375267014185034</out_trade_no><total_amount>0.01</total_amount><trade_no>2017122021001004470257147177</trade_no><auth_app_id>2017121400753768</auth_app_id><buyer_logon_id>134****5189</buyer_logon_id><point_amount>0.00</point_amount></xml>";
        //将微信传来的xml转map

        Map<String, String> map = WeChatUtil.toXml2(xmlStr.toString());
        boolean flag = AlipaySignature.rsaCheckV1(map, AliParam.alipaypublickey, "UTF-8", "RSA");
        System.out.println(flag);
        map.remove("sign");
        map.remove("sign_type");
        //2：第二步： 将剩下参数进行url_decode, 然后进行字典排序，组成字符串，得到待签名字符串：
        String str = AliUtil.getSignMap(map).substring(0,AliUtil.getSignMap(map).length()-1);
        System.out.println(str);
        //3：第三步： 将签名参数（sign）使用base64解码为字节码串。
        String encode = Base64Util.decode(str,"UTF-8");
//        System.out.println(str);
//        byte[] decode = Base64.decode(str);
//        //String encode = new String(decode, Charset.forName("utf-8"));

        //4：第四步： 使用RSA的验签方法，通过签名字符串、签名参数（经过base64解码）及支付宝公钥验证签名。

//            public static boolean rsaCheckV2(Map<String, String> params, String publicKey, String charset)
//            功能：RSA验签
//            输入：params 签名参数内容map
//            publicKey 公钥
//            charset 签名编码格式
//            输出：验签结果

//            public static boolean rsaCheckContent(String content, String sign, String publicKey,String charset)
//            功能：RSA验签
//            输入：content 签名参数内容字符串
//            sign 签名
//            publicKey 公钥
//            charset 签名编码格式
//            输出：验签结果
        boolean b = AlipaySignature.rsaCheckContent(str, "TS0lRcZrYHWP/3s/dRUercRyVq/TtHHpRaY7RZUMuzwbIGJBGDsZpLcZmKrgj8BTdddacIjfu+lvf1TDd7rpUzJNs7ijoFLi1Ah8a+2FFKNYn8NxKe4YCuEX9NskGr7xyfhMLrXb1nQUTRXuChBicyqM8H29zGBLHxMEuA6q/9c=", AliParam.alipaypublickey, "UTF-8");
         System.out.println(b);
//        boolean c = AlipaySignature.rsaCheckV2(map,  AliParam.alipaypublickey, "UTF-8");
//        System.out.println(c);

    }
}
