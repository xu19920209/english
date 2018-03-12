package com.tuyue.appModules.course.biz;

import com.tuyue.appModules.download.controller.DownloadController;
import com.tuyue.pojo.AllTestAnswer;
import com.tuyue.pojo.TestGrade;
import com.tuyue.pojo.TstuWorkFinish;
import com.tuyue.pojo.WadultWorkDetails;
import com.tuyue.result.Result;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
public interface IstudentWorkBiz {
    /**
     * @Author: 徐慷慨
     * @Description:学生作业列表
     * @Date: 16:34 2017/9/14
     */
    public Result stuWorkList(Integer nid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:学生作业详情
     * @Date: 16:34 2017/9/14
     */
    public Result stuWorkDetails(Integer nid,Integer bid,Integer index,String cids,String time) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:请教老师/跳过
     * @Date: 16:39 2017/9/14
     */
    public Result stuToWork(TstuWorkFinish tstuWorkFinish) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:请教列表
     * @Date: 14:38 2017/9/15
     */
    public Result consultList(Integer nid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:请教详情
     * @Date: 15:19 2017/9/15
     */
    public Result consultDetiles(Integer tid,Integer cid) throws Exception;


    /**
     * @Author: 徐慷慨
     * @Description:全部作业列表
     * @Date: 16:34 2017/9/14
     */
    public Result stuWorkListALL(Integer nid,Integer currentPage, Integer pageSize) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:学生全文列表
     * @Date: 12:02 2017/11/7
     */
   public Result allTest(Integer nid) throws Exception;
     /**
      * @Author: 徐慷慨
      * @Description:学生全文答题
      * @Date: 14:04 2017/11/7
      */
     public Result allTestAnswer(AllTestAnswer allTestAnswer) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:根据学生查询测试题
     * @Date: 19:49 2017/11/8
     */
    public Result selectTestByid(Integer nid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:答测试题
     * @Date: 19:51 2017/11/8
     */
    public Result answerTest(TestGrade testGrade);

    /**
     * @Author: 徐慷慨
     * @Description: 学生答题翻分
     * @Date: 14:38 2017/11/9
     */
    public Result breakGrade(Integer nid,Integer score) throws Exception;
}
