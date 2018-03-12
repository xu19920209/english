package com.tuyue.webModules.courseGoods.controller;

import com.tuyue.pojo.ACourseGood;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.ObjectCopyUtil;
import com.tuyue.util.Tools;
import com.tuyue.webModules.courseGoods.bean.CourseBean;
import com.tuyue.webModules.courseGoods.biz.CourseGoodBiz;
import io.netty.util.internal.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.font.TrueTypeFont;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/10/18.
 */
@Controller
@ResponseBody
@RequestMapping("web/courseGoods")
public class WebCourseController {
    @Autowired
    private CourseGoodBiz courseGoodBiz;

    /**
     * @Author: 徐慷慨
     * @Description:根据id查看商品
     * @Date: 9:58 2017/10/18
     */
    @RequestMapping("courseGoodsById.do")
    public Result courseGoodsById(Integer courseId) throws Exception {
        return courseGoodBiz.courseGoodsById(courseId);
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改课程商品
     * @Date: 9:59 2017/10/18
     */
    @RequestMapping("upCourseGoods.do")
    public Result upCourseGoods(@RequestBody CourseBean courseBean) throws Exception {
        ACourseGood aCourseGood = ObjectCopyUtil.copy(courseBean, CourseBean.class, ACourseGood.class);
        String[] goClassTime = courseBean.getGoClassTime();
        String[] goClassWeek = courseBean.getGoClassWeek();
        String a = "";
        String b = "";
        if (goClassTime != null && goClassTime.length > 0) {
            for (String s : goClassTime) {
                a += s + ",";
            }
        }
        if (a.endsWith(",")) {
            a = a.substring(0, a.length() - 1);
        }
        if (b.endsWith(",")) {
            b = b.substring(0, b.length() - 1);
        }
        if (goClassWeek != null && goClassWeek.length > 0) {
            for (String s : goClassWeek) {
                b += s + ",";
            }
        }
        if (a.endsWith(",")) {
            a = a.substring(0, a.length() - 1);
        }
        if (b.endsWith(",")) {
            b = b.substring(0, b.length() - 1);
        }

        aCourseGood.setGoClassTime(a);
        aCourseGood.setGoClassWeek(b);
        if (courseBean.getCourseId() > 0) {
            aCourseGood.setCourseId(courseBean.getCourseId());
            return courseGoodBiz.upCourseGoods(aCourseGood);
        } else {
            return courseGoodBiz.inCourseGoods(aCourseGood);
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:课程商品列表
     * @Date: 10:49 2017/10/18
     */
    @RequestMapping("courseGoodsList.do")
    public Result courseGoodsList(Integer currentPage, Integer pageSiz) throws Exception {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (pageSiz == null) {
            pageSiz = 10;
        }
        return courseGoodBiz.courseGoodsList(currentPage, pageSiz);
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改上下架
     * @Date: 11:34 2017/12/11
     */
    @RequestMapping("upAdd.do")
    public Result upAdd(@RequestBody ACourseGood aCourseGood) throws Exception {
        return courseGoodBiz.upAdd(aCourseGood);
    }

//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * @Author: 徐慷慨
     * @Description:排课管理列表
     * @Date: 14:19 2017/12/7
     */
    @RequestMapping("paiKeMangerList.do")
    public Result paiKeMangerList(Integer currentPage, Integer pageSize) throws Exception {
        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        return courseGoodBiz.paiKeMangerList(currentPage, pageSize);
    }

    /**
     * @Author: 徐慷慨
     * @Description:课程课时详情列表
     * @Date: 15:19 2017/12/7
     */
    @RequestMapping("courseHourDetails.do")
    public Result courseHourDetails(Integer courseId, Integer currentPage, Integer pageSize) throws Exception {
        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        return courseGoodBiz.courseHourDetails(courseId, currentPage, pageSize);
    }

    /**
     * @Author: 徐慷慨
     * @Description:购买详情列表
     * @Date: 16:08 2017/12/7
     */
    @RequestMapping("payCourseDetails.do")
    public Result payCourseDetails(Integer courseId, Integer currentPage, Integer pageSize) throws Exception {
        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        return courseGoodBiz.payCourseDetails(courseId, currentPage, pageSize);
    }

    /**
     * @Author: 徐慷慨
     * @Description:预约详情列表
     * @Date: 17:27 2017/12/7
     */
    @RequestMapping("appointmentDtail.do")
    public Result appointmentDtail(Integer courseId, Integer currentPage, Integer pageSize) throws Exception {
        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        return courseGoodBiz.appointmentDtail(courseId, currentPage, pageSize);
    }

    /**
     * @Author: 徐慷慨
     * @Description:排课
     * @Date: 9:35 2017/12/8
     */
    @RequestMapping("Pk.do")
    public Result Pk(@RequestBody Map<String, Object> obj) throws Exception {
        Object id = obj.get("courseId");
        String s = id.getClass().toString();

        int courseId = 0;
        if (s.equals("class java.lang.String")) {
            courseId = Integer.parseInt((String) obj.get("courseId"));
        }
        if (s.equals("class java.lang.Integer")) {
            courseId = (Integer) obj.get("courseId");
        }
        String time = (String) obj.get("time");
        return courseGoodBiz.Pk(courseId, time);
    }

    /**
     * @Author: 徐慷慨
     * @Description:开放预约
     * @Date: 9:57 2017/12/8
     */
    @RequestMapping("yuYue.do")
    public Result yuYue(@RequestParam(required = true) Integer courseId) throws Exception {
        return courseGoodBiz.yuYue(courseId);
    }

    /**
     * @Author: 徐慷慨
     * @Description:课表接口
     * @Date: 10:00 2017/12/8
     */
    @RequestMapping("classChart.do")
    public Result classChart(@RequestParam(required = true) Integer courseId) throws Exception {
        return courseGoodBiz.classChart(courseId);
    }
}
