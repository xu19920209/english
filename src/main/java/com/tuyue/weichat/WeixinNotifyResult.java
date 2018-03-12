package com.tuyue.weichat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/14.
 */
@JacksonXmlRootElement(localName = "xml")
public class WeixinNotifyResult {
    private String return_code;
    private String return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}
