package com.tuyue.webModules.courseGoods.biz;

import com.tuyue.appModules.download.controller.DownloadController;
import com.tuyue.pojo.ACourseGood;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.sql.SQLException;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/10/18.
 */
public interface CourseGoodBiz {
    /**
     * @Author: 徐慷慨
     * @Description:添加商品
     * @Date: 9:56 2017/10/18
     */
    public Result inCourseGoods(ACourseGood aCourseGood);

    /**
     * @Author: 徐慷慨
     * @Description:根据id查看商品
     * @Date: 9:58 2017/10/18
     */
    public Result courseGoodsById(Integer id) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:修改课程商品
     * @Date: 9:59 2017/10/18
     */
    public Result upCourseGoods(ACourseGood aCourseGood) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:课程商品列表
     * @Date: 10:49 2017/10/18
     */
    public Result courseGoodsList(Integer currentPage, Integer pageSiz) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:修改上下架
     * @Date: 9:32 2017/10/20
     */
    public Result upAdd(ACourseGood aCourseGood) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:排课管理列表
     * @Date: 14:19 2017/12/7
     */
    public Result paiKeMangerList(Integer currentPage,Integer pageSize) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:课程课时详情列表
     * @Date: 15:19 2017/12/7
     */
    public Result courseHourDetails(@RequestParam(required = true) Integer courseId, Integer currentPage, Integer pageSize) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:购买详情列表
     * @Date: 16:08 2017/12/7
     */
    public Result payCourseDetails(Integer courseId,Integer currentPage,Integer pageSize) throws Exception;
    
    /**
     * @Author: 徐慷慨
     * @Description:预约详情列表
     * @Date: 17:27 2017/12/7
     */
    public Result appointmentDtail(Integer courseId,Integer currentPage,Integer pageSize) throws Exception;
    
    /**
     * @Author: 徐慷慨
     * @Description:排课
     * @Date: 9:35 2017/12/8
     */
    public Result Pk(Integer courseId,String time) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:开放预约
     * @Date: 9:57 2017/12/8
     */
    public Result yuYue(Integer courseId) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:课表接口
     * @Date: 10:00 2017/12/8
     */
    public Result classChart(Integer courseId) throws Exception;
}
