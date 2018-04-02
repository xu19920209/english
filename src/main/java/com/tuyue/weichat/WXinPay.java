package com.tuyue.weichat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/13.
 */
@JacksonXmlRootElement(localName = "xml")
public class WXinPay {
    private String appid;
    /** 商户号 */
    @ApiModelProperty(value = "商户号", required = true)
    @JacksonXmlProperty(localName = "mch_id")
    private String mch_id;
    @JacksonXmlProperty(localName = "nonce_str")
    private String nonce_str;
    private String sign;
    @JacksonXmlProperty(localName = "sign_type")
    private String sign_type;
    private String body;
    @JacksonXmlProperty(localName = "out_trade_no")
    private String out_trade_no;
    @JacksonXmlProperty(localName = "total_fee")
    private int total_fee;
    @JacksonXmlProperty(localName = "spbill_create_ip")
    private String spbill_create_ip;
    @JacksonXmlProperty(localName = "notify_url")
    private String notify_url;
    @JacksonXmlProperty(localName = "trade_type")
    private String trade_type;
    private String openid;
    @JacksonXmlProperty(localName = "scene_info")
    private String scene_info;

    public String getScene_info() {
        return scene_info;
    }

    public void setScene_info(String scene_info) {
        this.scene_info = scene_info;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
}
