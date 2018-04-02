package com.tuyue.appModules.course.biz;

import com.tuyue.appModules.download.controller.DownloadController;
import com.tuyue.pojo.WadultWorkDetails;
import com.tuyue.result.Result;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
public interface IadultWorkBiz {
    /**
     * @Author: 徐慷慨
     * @Description:成人作业列表
     * @Date: 11:05 2017/9/14
     */
    public Result adultWorkList(Integer nid,Integer levelId) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:成人作业详情
     * @Date: 11:07 2017/9/14
     */
    public Result adultWorkDetails(Integer nid, Integer bid, Integer index, String cids, String time) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:成人答题
     * @Date: 11:08 2017/9/14
     */
    public Result adultToWork(WadultWorkDetails wadultWorkDetails) throws Exception;

    /**
     * 成人课程列表
     * @return
     */
    public Result adultCourse() throws Exception;

    /**
     * 成人课程级别列表
     * @param aid
     * @return
     */
    public Result adultLevel(int aid) throws Exception;
}
