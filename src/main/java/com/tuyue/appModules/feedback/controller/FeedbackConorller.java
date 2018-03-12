package com.tuyue.appModules.feedback.controller;
import com.tuyue.appModules.feedback.biz.IZfeedbackBiz;
import com.tuyue.pojo.Zfeedback;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/15.
 * @Modified By:
 */
@Controller
@ResponseBody
@RequestMapping("Feedback")
public class FeedbackConorller {

    @Autowired
    private IZfeedbackBiz zfeedbackBiz;

    /**
     * @Author: 王金海
     * @Description: APP反馈
     * @Date: 15:52 2017/9/15
     */
    @RequestMapping("add")
    public Result add(Zfeedback zfeedback){
        boolean b = zfeedbackBiz.add(zfeedback);
        if (b) {
            return ResultUtil.success("反馈成功");
        }
        return ResultUtil.error(2,"反馈失败");
    }

    /**
     * @Author: 王金海
     * @Description:反馈列表
     * @Date: 15:52 2017/9/15
     */
    @RequestMapping("list")
    public Result list(Integer currentPage,Integer pageSize) throws Exception {
        Page<Zfeedback> list = zfeedbackBiz.list(currentPage,pageSize);
        return ResultUtil.success(list);
    }

    @RequestMapping("del")
    public Result del(Integer zid) throws Exception {
        boolean b= zfeedbackBiz.del(zid);
        if (b) {
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.error(2,"删除失败");
    }
}
