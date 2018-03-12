package com.tuyue.webModules.parameter.biz;

import com.tuyue.pojo.Replymodule;
import com.tuyue.result.Result;
import com.tuyue.util.Page;



/**
 * @Author: 王金海
 * @Description: 消息模版
 * @Date: Created by Administrator on 2017/9/9.
 * @Modified By:
 */
public interface IReplymoduleBiz {
    /**
     * @Author: 王金海
     * @Description: 增加模版消息
     * @Date: 8:59 2017/9/9
     */
    public Result add(Replymodule replymodule) throws Exception;
    /**
     * @Author: 王金海
     * @Description:删除消息
      * @param rid 消息ID
     * @Date: 9:01 2017/9/9
     */
    public boolean del(int rid) throws Exception;
    /**
     * @Author: 王金海
     * @Description:模版列表
     * @Date: 9:02 2017/9/9
     */
    public Page<Replymodule> list(Integer page, Integer size) throws Exception;
    /**
     * @Author: 王金海
     * @Description:修改消息
     * @Date: 9:24 2017/9/9
     */
    public Result updata(Replymodule replymodule) throws Exception;
}
