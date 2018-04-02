package com.tuyue.appModules.course.controller;

import com.tuyue.appModules.course.biz.IadultWorkBiz;
import com.tuyue.pojo.WadultWorkDetails;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
@Controller
@ResponseBody
@RequestMapping("app/adult")
public class AppAdultController {
    @Autowired
    private IadultWorkBiz biz;
    /**
     * @Author: 徐慷慨
     * @Description:成人作业列表
     * @Date: 11:05 2017/9/14
     */
    @RequestMapping("adultWorkList.do")
    public Result adultWorkList(Integer nid,Integer levelId) throws Exception{
      return   biz.adultWorkList(nid,levelId);
    }

    /**
     * @Author: 徐慷慨
     * @Description:成人作业详情
     * @Date: 11:07 2017/9/14
     */
    @RequestMapping("adultWorkDetails.do")
    public Result adultWorkDetails(Integer nid,Integer bid,Integer index,String cids,String time) throws Exception{
        System.out.println("::::::::::::::"+time);
        return   biz.adultWorkDetails(nid,bid,index,cids,time);
    }
    /**
     * @Author: 徐慷慨
     * @Description:成人答题
     * @Date: 11:08 2017/9/14
     */
    @RequestMapping("adultToWork.do")
    public Result adultToWork(WadultWorkDetails wadultWorkDetails, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        Integer nid = wadultWorkDetails.getNid();
        if(nid==null){
            return  ResultUtil.error(2,"用户id不能为空！");
        }
        if (file!=null&&!file.isEmpty()) {
            String path = Setting.UPMP3PATH+"/";// 文件路径
            String name = System.currentTimeMillis() + file.getOriginalFilename();// 文件名称
            try {
                File f = new File(path, name);
                if (!f.exists()) {
                    f.mkdirs();
                }
                file.transferTo(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
            wadultWorkDetails.setAdultTape("/wordVoice/" + name);
        }
        wadultWorkDetails.setFinishTime(new Timestamp(System.currentTimeMillis()));
        return  biz.adultToWork(wadultWorkDetails);
    }


    /**
     * 成人课程列表
     * @return
     */
    @RequestMapping(value = "adultCourse",produces = {"application/json;UTF-8"})
    public Result adultCourse() throws Exception{
        return biz.adultCourse();
    }

    /**
     * 成人课程级别列表
     * @param aid
     * @return
     */
    @RequestMapping("adultLevel")
    public Result adultLevel(@RequestParam(required = true) int aid) throws Exception{
        return biz.adultLevel(aid);
    }
}
