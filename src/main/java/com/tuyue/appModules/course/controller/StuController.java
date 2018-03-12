package com.tuyue.appModules.course.controller;

import com.tuyue.appModules.course.biz.IstudentWorkBiz;
import com.tuyue.pojo.AllTestAnswer;
import com.tuyue.pojo.TestGrade;
import com.tuyue.pojo.TstuWorkFinish;
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
@RequestMapping("app/stu")
public class StuController {
@Autowired
private IstudentWorkBiz biz;
    /**
     * @Author: 徐慷慨
     * @Description:学生作业列表
     * @Date: 16:34 2017/9/14
     */
@RequestMapping("stuWorkList.do")
    public Result stuWorkList(Integer nid) throws Exception{
        return biz.stuWorkList(nid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:学生作业详情
     * @Date: 16:34 2017/9/14
     */
    @RequestMapping("stuWorkDetails.do")
    public Result stuWorkDetails(Integer nid,Integer bid,Integer index,String cids,String time) throws Exception{
        System.out.println("::::::::::::::"+time);
        return biz.stuWorkDetails(nid,bid,index,cids,time);
    }

    /**
     * @Author: 徐慷慨
     * @Description:请教老师/跳过/答题
     * @Date: 16:39 2017/9/14
     */
    @RequestMapping("stuToWork.do")
    public Result stuToWork(TstuWorkFinish tstuWorkFinish, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        if(file!=null){
            String name = System.currentTimeMillis() + file.getOriginalFilename();// 文件名称
            String path = Setting.UPMP3PATH+"/";// 文件路径
            try {
                File f = new File(path, name);
                if (!f.exists()) {
                    f.mkdirs();
                }
                file.transferTo(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tstuWorkFinish.setStudentTape("/wordVoice/" + name);
        }
            tstuWorkFinish.setFinishTime(new Timestamp(System.currentTimeMillis()));
            return biz.stuToWork(tstuWorkFinish);
    }


    /**
     * @Author: 徐慷慨
     * @Description:请教列表
     * @Date: 14:38 2017/9/15
     */
    @RequestMapping("consultList.do")
    public Result consultList(Integer nid) throws Exception {
        return biz.consultList(nid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:请教详情
     * @Date: 15:19 2017/9/15
     */
    @RequestMapping("consultDetiles.do")
    public Result consultDetiles(Integer tid, Integer cid) throws Exception {
        if(tid==null|| cid==null){
            return ResultUtil.error(2,"参数错误");
        }
        return biz.consultDetiles(tid,cid);
    }
    /**
     * @Author: 徐慷慨
     * @Description:全部作业列表
     * @Date: 16:34 2017/9/14
     */
    @RequestMapping("stuWorkListALL.do")
    public Result stuWorkListALL(Integer nid,Integer currentPage, Integer pageSize) throws Exception{
        if(currentPage==null){
            currentPage=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        return biz.stuWorkListALL(nid,currentPage,pageSize);
    }



    /**
     * @Author: 徐慷慨
     * @Description:学生全文列表
     * @Date: 12:02 2017/11/7
     */
    @RequestMapping("allArticle.do")
    public Result allTest(Integer nid) throws Exception{
        return biz.allTest(nid);
    }
    /**
     * @Author: 徐慷慨
     * @Description:学生全文答题
     * @Date: 14:04 2017/11/7
     */
    @RequestMapping("articleAnswer.do")
    public Result allTestAnswer(AllTestAnswer allTestAnswer) throws Exception{
        return biz.allTestAnswer(allTestAnswer);
    }

    /**
     * @Author: 徐慷慨
     * @Description:根据学生查询测试题
     * @Date: 19:49 2017/11/8
     */
    @RequestMapping("selectTestByid.do")
    public Result selectTestByid(Integer nid) throws Exception{
        return biz.selectTestByid(nid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:答测试题
     * @Date: 19:51 2017/11/8
     */
    @RequestMapping("answerTest.do")
    public Result answerTest(TestGrade testGrade){
        return biz.answerTest(testGrade);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 学生答题翻分
     * @Date: 14:38 2017/11/9
     */
    @RequestMapping("breakGrade.do")
    public Result breakGrade(Integer nid,Integer score) throws Exception{
        return biz.breakGrade(nid,score);
    }
}
