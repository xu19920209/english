package com.tuyue.appModules.courseGoods.biz.impl;

import com.tuyue.appModules.courseGoods.biz.PartMoneyBiz;
import com.tuyue.appModules.courseGoods.biz.WeChatPayBiz;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/13.
 */
@Service
public class WeChatPayBizImpl implements WeChatPayBiz {
    @Autowired
    private IBaseDao<CourseGoodsOrder> courseGoodsOrderIBaseDao;
    @Autowired
    private IBaseDao<ACourseGood>aCourseGoodIBaseDao;
    @Autowired
    private IBaseDao<Nstudent>nstudentIBaseDao;
    @Autowired
    private IBaseDao<Ebranchschool>ebranchschoolIBaseDao;
    @Autowired
    private IBaseDao<AgentAccount> agentAccountIBaseDao;
    /**
     * 查询订单
     * @param orderNo
     * @param nid
     * @return
     */
    @Override
    @Transactional
    public CourseGoodsOrder unifiedorder(String orderNo, Integer nid) throws Exception {
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne(" FROM CourseGoodsOrder where orderNo='" + orderNo + "' and nid=" + nid);
        if(one!=null){
            ACourseGood one1 = aCourseGoodIBaseDao.getOne(ACourseGood.class, one.getCourseId());
            if(one1!=null){
                if(one1.getIsAdded()==2){
                    return null;
                }
                    one.setPayMoney(one1.getCoursePrice());
            }else{
                return null;
            }
        }
        return one;
    }

    /**
     * 订单支付
     * @param courseGoodsOrder
     * @return
     */
    @Override
    public boolean courseGoodOrder(CourseGoodsOrder courseGoodsOrder) throws Exception {
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne(" FROM CourseGoodsOrder where orderNo='" + courseGoodsOrder.getOrderNo() + "'");
        if(one==null){
            return false;
        }
        one.setOrderState(1);
        one.setPayMoney(courseGoodsOrder.getPayMoney());
        one.setPayTime(courseGoodsOrder.getPayTime());
        one.setPayWay(courseGoodsOrder.getPayWay());
        one.setDetails(courseGoodsOrder.getDetails());
        boolean update = courseGoodsOrderIBaseDao.update(one);
        //分红
        PartMoneyBiz partMoneyBiz=new PartMoneyBizImpl();
            try {
                boolean b = partMoneyBiz.partMoney(courseGoodsOrder.getOrderNo());
            }catch (Exception e){
                throw new RuntimeException("订单"+courseGoodsOrder.getOrderNo()+"分红失败");
            }

        if(update){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 根据订单号查询课程商品
     * @return
     */
    @Override
    @Transactional
    public ACourseGood  selectByOrderNo(String orderNo) throws Exception {
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne(" FROM CourseGoodsOrder where orderNo='" + orderNo + "'");
        ACourseGood one1 = aCourseGoodIBaseDao.getOne(ACourseGood.class, one.getCourseId());
        return one1;
    }
    /**
     * @Author: 徐慷慨
     * @Description:根据订单号查询查询订单
     * @Date: 10:32 2017/12/20
     */
    @Override
    public CourseGoodsOrder booleanOrderNo(String orderNo) throws Exception{
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne(" FROM CourseGoodsOrder where orderNo='" + orderNo + "'");

            return one;

    }

    /**
     * @Author: 徐慷慨
     * @Description:改变订单状态
     * @Date: 11:31 2017/12/20
     */
    @Override
    public boolean OrderState(String orderNo, Integer orderState) throws Exception{
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne(" FROM CourseGoodsOrder where orderNo='" + orderNo + "'");
        if(one!=null){
            one.setOrderState(3);
            boolean update = courseGoodsOrderIBaseDao.update(one);
            return true;
        }
        return false;
    }

    public Nstudent selectNstudentByOrderNo(String orderNo) throws Exception{
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne(" FROM CourseGoodsOrder where orderNo='" + orderNo + "'");
        Nstudent one1 = nstudentIBaseDao.getOne(Nstudent.class, one.getNid());
        return one1;
    }
    /**
     * @Author: 徐慷慨
     * @Description:根据eidchaxunaddress
     * @Date: 16:40 2017/12/26
     */
    public Ebranchschool byEid(Integer eid) throws Exception {
        Ebranchschool one = ebranchschoolIBaseDao.getOne(Ebranchschool.class, eid);
        return one;
    }
}
