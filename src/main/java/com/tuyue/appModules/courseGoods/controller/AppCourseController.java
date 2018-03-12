package com.tuyue.appModules.courseGoods.controller;
import com.tuyue.pojo.CourseComment;
import com.tuyue.result.Result;
import com.tuyue.appModules.courseGoods.biz.IcourseGoodBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/10/18.
 */
@RestController
@RequestMapping("app/courseGoods")
public class AppCourseController {
    @Autowired
    private IcourseGoodBiz courseGoodBiz;
    /**
     * @Author: 徐慷慨
     * @Description:根据id查看商品
     * @Date: 9:58 2017/10/18
     */

    @GetMapping("courseGoodsById.do")
    public Result courseGoodsById(Integer courseId) throws Exception{

        return courseGoodBiz.courseGoodsById(courseId);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 课程商品列表
     * @param currentPage
     * @param pageSiz
     * @Date: 16:47 2017/12/12
     */
    /**
     *
     * @param currentPage
     * @param pageSiz
     * @return
     * @throws Exception
     */
    @GetMapping("courseGoodsList.do")
    public Result courseGoodsList(Integer currentPage,Integer pageSiz) throws Exception{

        if(currentPage==null){
            currentPage=1;
        }
        if(pageSiz==null){
            pageSiz=10;
        }
        return courseGoodBiz.courseGoodsList(currentPage,pageSiz);
    }


    /**
     * @Author: 徐慷慨
     * @Description:下订单
     * @Date: 13:33 2017/12/11
     */
    @GetMapping("orderIn.do")
    public Result orderIn(Integer nid,Integer courseId,String phone) throws Exception{
        return courseGoodBiz.orderIn(nid,courseId,phone);
    }
    /**
     * @Author: 徐慷慨
     * @Description: 未付款 已付款 已开课列表 type 1：未付款 2 已付款 3 已开课
     * @Date: 15:18 2017/12/11
     */
    @GetMapping("orderList.do")
    public Result orderList(Integer currentPage, Integer pageSize, @RequestParam(required = true) Integer nid,@RequestParam(required = true) Integer type) throws Exception{
        if(currentPage==null){
            currentPage=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        return courseGoodBiz.orderList(currentPage,pageSize,nid,type);
    }
    /**
     * @Author: 徐慷慨
     * @Description: 查看课程详情
     * @Date: 8:58 2017/12/12
     */
    @GetMapping("courseGoodsDetail.do")
    public Result courseGoodsDetail(@RequestParam(required = true) Integer courseId,@RequestParam(required = true) Integer nid) throws Exception{
        return courseGoodBiz.courseGoodsDetail(courseId,nid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:预约课程列表
     * @Date: 9:44 2017/12/12
     */
    @GetMapping("courseYuYue.do")
    public Result courseYuYue(@RequestParam(required = true)String courseName,@RequestParam(required = true)Integer num) throws Exception{
        //courseName=new String(courseName.getBytes("iso-8859-1"),"UTF-8");
        return courseGoodBiz.courseYuYue(courseName,num);
    }

    /**
     * @Author: 徐慷慨
     * @Description:预约课程
     * @Date: 10:36 2017/12/12
     */
    @GetMapping("yuYue.do")
    public Result yuYue(@RequestParam(required = true)Integer nid,@RequestParam(required = true)Integer courseId,@RequestParam(required = true)Integer num) throws Exception{
        return courseGoodBiz.yuYue(nid,courseId,num);
    }

    /**
     * @Author: 徐慷慨
     * @Description:教习评价
     * @Date: 10:38 2017/12/12
     */
    @RequestMapping("teachCommon.do")
    public Result teachCommon(Integer nid,Integer courseId,Integer teachEnvironment,Integer teachQuality) throws Exception{
        CourseComment courseComment =new CourseComment();
        courseComment.setNid(nid);
        courseComment.setCourseId(courseId);
        courseComment.setTeachEnvironment(teachEnvironment);
        courseComment.setTeachQuality(teachQuality);
        return courseGoodBiz.teachCommon(courseComment);
    }

    /**
     * @Author: 徐慷慨
     * @Description:学生签到
     * @Date: 10:40 2017/12/12
     */
    @GetMapping("sign.do")
    public Result sign(Integer nid,Integer courseId) throws Exception{
        System.out.println("fdsfdsfdsssssssssssssssssssssssssss学生签到");
        return courseGoodBiz.sign(nid,courseId);
    }
    /**
     * @Author: 徐慷慨
     * @Description: 点击支付回显信息
     * @Date: 14:06 2017/12/12
     */
    @GetMapping("orderDetails.do")
    public Result orderDetails(@RequestParam(required = true) String orderNo) throws Exception{
        return courseGoodBiz.orderDetails(orderNo);

    }
    /**
     * 已支付详情
     * @param orderNo
     * @return
     * @throws Exception
     */
    @RequestMapping("orderPayDetails.do")
    public Result orderPayDetails(String orderNo) throws Exception{
        return courseGoodBiz.orderPayDetails(orderNo);
    }

    /**
     * @Author: 徐慷慨
     * @Description:查询订单是否支付成功
     * @Date: 15:59 2017/12/18
     */
    @RequestMapping("orderState.do")
    public Result orderPaySuccess(String orderNo) throws Exception{
        return courseGoodBiz.orderPaySuccess(orderNo);
    }

    /**
     * @Author: 徐慷慨
     * @Description:删除订单
     * @Date: 14:29 2017/12/19
     */
    @RequestMapping("deleteOrder.do")
    public Result delete(String orderNo) throws Exception{
        return courseGoodBiz.delete(orderNo);
    }
    /**
     * @Author: 徐慷慨
     * @Description:支付时判断该课程是不是已经购买人数满了
     * @Date: 16:52 2017/12/19
     */
    @RequestMapping("isPay.do")
    public Result isPay(String orderNo) throws Exception{
        return courseGoodBiz.isPay(orderNo);
    }
}
