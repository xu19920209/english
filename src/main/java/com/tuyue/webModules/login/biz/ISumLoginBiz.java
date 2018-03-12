package com.tuyue.webModules.login.biz;

import com.tuyue.result.Result;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/8.
 * @Modified By:
 */
public interface ISumLoginBiz {
    public Result login( String name , String password) throws Exception;
}
