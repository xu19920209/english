package com.tuyue.handle;


import com.tuyue.exception.MyException;
import com.tuyue.result.Result;
import com.tuyue.result.ResultEnum;
import com.tuyue.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/5/25.
 */
//@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof MyException){
            MyException adminException= (MyException) e;
            return ResultUtil.error(adminException.getCode(),adminException.getMessage());
        }else {
            logger.error("【系统异常】 {}",e.getMessage());
            return ResultUtil.error(ResultEnum.UNKONW_ERROR);
        }
    }

}
