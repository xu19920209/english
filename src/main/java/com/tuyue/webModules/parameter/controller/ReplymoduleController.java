package com.tuyue.webModules.parameter.controller;

import com.tuyue.pojo.Replymodule;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.parameter.biz.IReplymoduleBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/9.
 * @Modified By:
 */
@Controller
@RequestMapping("Replymodule")
public class ReplymoduleController {
    private final static Logger logger = LoggerFactory.getLogger(ReplymoduleController.class);
    @Autowired
    private IReplymoduleBiz replymoduleBiz;


    /**
     * @Author: 王金海
     * @Description:删除消息
     * @param rid 消息ID
     * @Date: 9:01 2017/9/9
     */
    @RequestMapping("del")
    @ResponseBody
    public Result del(Integer rid) throws Exception {
        logger.error("删除消息");
        if (rid==null) {
            return ResultUtil.error(2,"参数不能为空");
        }
        boolean b = replymoduleBiz.del(rid);
        if (b) {
            return ResultUtil.success("删除成功",1);
        }
        return ResultUtil.error(2,"删除失败");
    }

    /**
     * @Author: 王金海
     * @Description:模版列表
     * @Date: 9:02 2017/9/9
     */
    @RequestMapping("list")
    @ResponseBody
    public Result list(Integer currentPage,Integer pageSize) throws Exception {
        logger.error("模版列表");
        if(currentPage==null|| currentPage<0){
            currentPage=1;
        }
        if(pageSize==null||pageSize<=0){
            pageSize=10;
        }
        Page<Replymodule> list = replymoduleBiz.list(currentPage,pageSize);
        return ResultUtil.success(list);
    }

    /**
     * @Author: 王金海
     * @Description:增加修改消息
     * @Date: 9:24 2017/9/9
     */
    @RequestMapping("updata")
    @ResponseBody
    public Result updata(@RequestBody Replymodule replymodule) throws Exception {
        logger.error("增加修改消息");
        if (replymodule==null) {
            return ResultUtil.error(2,"参数不能为空");
        }
        if (replymodule.getRid()<=0) {
            return replymoduleBiz.add(replymodule);
        }
        return replymoduleBiz.updata(replymodule);
    }

}
