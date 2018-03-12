package com.tuyue.appModules.courseGoods.biz;

import com.tuyue.pojo.ACourseGood;
import com.tuyue.pojo.CourseGoodsOrder;
import com.tuyue.pojo.Ebranchschool;
import com.tuyue.pojo.Nstudent;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/13.
 */
public interface WeChatPayBiz {
    /**
     * 查询订单
     * @param orderNo
     * @param nid
     * @return
     */
    public CourseGoodsOrder unifiedorder(String orderNo, Integer nid) throws Exception;

    /**
     * 订单支付
     * @param courseGoodsOrder
     * @return
     */
    public boolean courseGoodOrder(CourseGoodsOrder courseGoodsOrder) throws Exception;

    /**
     * 根据订单号查询课程商品
     * @return
     */
    public ACourseGood  selectByOrderNo(String orderNo) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:根据订单号查询查询订单
     * @Date: 10:32 2017/12/20
     */
    public CourseGoodsOrder booleanOrderNo(String orderNo) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:改变订单状态
     * @Date: 11:31 2017/12/20
     */
    public boolean OrderState(String orderNo,Integer orderState) throws Exception;

    public Nstudent selectNstudentByOrderNo(String orderNo) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:根据eidchaxunaddress
     * @Date: 16:40 2017/12/26
     */
    public Ebranchschool byEid(Integer eid) throws Exception;
}
