package com.tuyue.appModules.courseGoods.biz;
import com.tuyue.pojo.CourseComment;
import com.tuyue.result.Result;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/10/18.
 */
public interface IcourseGoodBiz {

    /**
     * @Author: 徐慷慨
     * @Description:根据id查看商品
     * @Date: 9:58 2017/10/18
     */
    public Result courseGoodsById(Integer id) throws Exception;


    /**
     * @Author: 徐慷慨
     * @Description:课程商品列表
     * @Date: 10:49 2017/10/18
     */
    public Result courseGoodsList(Integer currentPage, Integer pageSiz) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:下订单
     * @Date: 13:33 2017/12/11
     */
    public Result orderIn(Integer nid, Integer courseId, String phone) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description: 未付款 已付款 已开课列表 type 1：未付款 2 已付款 3 已开课
     * @Date: 15:18 2017/12/11
     */
    public Result orderList(Integer currentPage, Integer pageSize, Integer nid, Integer type) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description: 查看课程详情
     * @Date: 8:58 2017/12/12
     */
    public Result courseGoodsDetail(Integer courseId, Integer nid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:预约课程列表
     * @Date: 9:44 2017/12/12
     */
    public Result courseYuYue(String courseName, Integer num) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:预约课程
     * @Date: 10:36 2017/12/12
     */
   public Result yuYue(Integer nid, Integer courseId, Integer num) throws Exception;

   /**
    * @Author: 徐慷慨
    * @Description:教习评价
    * @Date: 10:38 2017/12/12
    */
   public Result teachCommon(CourseComment courseComment) throws Exception;

   /**
    * @Author: 徐慷慨
    * @Description:学生签到
    * @Date: 10:40 2017/12/12
    */
   public Result sign(Integer nid, Integer courseId) throws Exception;
   /**
    * @Author: 徐慷慨
    * @Description: 点击支付回显信息
    * @Date: 14:06 2017/12/12
    */
   public Result orderDetails(String orderNo) throws Exception;

    /**
     * 已支付详情
     * @param orderNo
     * @return
     * @throws Exception
     */
    public Result orderPayDetails(String orderNo) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:查询订单是否支付成功
     * @Date: 15:59 2017/12/18
     */
    public Result orderPaySuccess(String orderNo) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:删除订单
     * @Date: 14:29 2017/12/19
     */
    public Result delete(String orderNo) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:支付时判断该课程是不是已经购买人数满了
     * @Date: 16:52 2017/12/19
     */
    public Result isPay(String orderNo) throws Exception;


}
