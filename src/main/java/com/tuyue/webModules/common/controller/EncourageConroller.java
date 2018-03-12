package com.tuyue.webModules.common.controller;

import com.tuyue.pojo.Encourage;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.webModules.common.biz.EncourageBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/21.
 */
@Controller
@ResponseBody
@RequestMapping("web/encourage")
public class EncourageConroller {
    @Autowired
    private EncourageBiz encourageBiz;
    /**
     * @Author: 徐慷慨
     * @Description:添加鼓励
     * @Date: 9:56 2017/11/21
     */
    @RequestMapping("inEncourage.do")
    public Result inEncourage(@RequestBody Encourage encourage){
        return encourageBiz.inEncourage(encourage);
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改鼓励语
     * @Date: 9:58 2017/11/21
     */
    @RequestMapping("upEncourage.do")
    public Result upEncourage(@RequestBody Encourage encourage) throws Exception{
        if(encourage.getId()>0){
            return encourageBiz.upEncourage(encourage);
        }else{
            return encourageBiz.inEncourage(encourage);
        }

    }
    /**
     * @Author: 徐慷慨
     * @Description:删除鼓励语
     * @Date: 9:59 2017/11/21
     */
    @RequestMapping("delEncourage.do")
    public Result delEncourage(String ids){
        if(ids.length()<=0){
            return ResultUtil.error(2,"请传参");
        }
        return encourageBiz.delEncourage(ids);
    }
    /**
     * @Author: 徐慷慨
     * @Description:鼓励语列表
     * @Date: 9:59 2017/11/21
     */
    @RequestMapping("encourageList.do")
    public Result encourageList(Integer currentPage ,Integer pageSize,Integer type) throws Exception {
        if(currentPage==null||currentPage==0){
            currentPage=1;
        }
        if(pageSize==null||pageSize==0){
            pageSize=10;
        }
        if(type==null||type==0){
            type=0;
        }
        return encourageBiz.encourageList(currentPage,pageSize,type);
    }
}
