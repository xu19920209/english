package com.tuyue.exception;


import com.tuyue.result.ResultEnum;

/**
 * 异常处理类
 * Created by Administrator on 2017/5/25.
 */
public class MyException extends RuntimeException {
    private Integer code;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
