package com.tuyue.appModules.courseGoods.biz.impl;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import com.tuyue.appModules.courseGoods.bean.CourseGoodsDetail;
import com.tuyue.appModules.courseGoods.bean.OrderDetail;
import com.tuyue.appModules.courseGoods.bean.OrderList;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.appModules.courseGoods.biz.IcourseGoodBiz;
import com.tuyue.util.Page;

import com.tuyue.util.RandomUtil;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.font.TrueTypeFont;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/10/18.
 */
@Service
@Transactional
public class AppCourseGoodBizImpl implements com.tuyue.appModules.courseGoods.biz.IcourseGoodBiz {
    @Autowired
    private IBaseDao<ACourseGood> aCourseGoodDao;
    @Autowired
    private IBaseDao<CourseComment> courseCommentIBaseDao;
    @Autowired
    private IBaseDao<Agent> agentIBaseDao;
    @Autowired
    private IBaseDao<CourseGoodsOrder> courseGoodsOrderIBaseDao;
    @Autowired
    private IBaseDao<Ebranchschool> ebranchschoolIBaseDao;
    @Autowired
    private IBaseDao<ClassHour> classHourIBaseDao;
    @Autowired
    private IBaseDao<StuGoClass> stuGoClassIBaseDao;
    @Autowired
    private IBaseDao<StuAppointment> stuAppointmentIBaseDao;
    @Autowired
    private IBaseDao<AgentAccount> agentAccountIBaseDao;

    /**
     * @Author: 徐慷慨
     * @Description:根据id查看商品
     * @Date: 9:58 2017/10/18
     */
    @Override
    public Result courseGoodsById(Integer id) throws Exception {
        ACourseGood one = aCourseGoodDao.getOne(ACourseGood.class, id);
        if (one != null) {
            List<CourseComment> list = courseCommentIBaseDao.findList(" FROM CourseComment WHERE courseId=" + id);
            int a = 0;
            int b = 0;
            for (CourseComment courseComment : list) {
                a += courseComment.getTeachEnvironment();
                b += courseComment.getTeachQuality();
            }
            if(list.size()>0){
                one.setTeachEnvironment(a / list.size());
                one.setTeachQuality(b / list.size());
            }else{
                one.setTeachEnvironment(5);
                one.setTeachQuality(5);
            }
            List<CourseGoodsOrder> listOrder = courseGoodsOrderIBaseDao.findList(" from CourseGoodsOrder where (orderState=1 or orderState=3) and courseId=" + id);
            Integer flag=1;
            if(listOrder.size()>=one.getPeopleNum()){
                flag=2;
            }else{
                flag=1;
            }
            one.setFlag(flag);
            return ResultUtil.success("根据id查看商品", one);
        }
        return ResultUtil.error(2, "根据id查看商品");
    }

    /**
     * @Author: 徐慷慨
     * @Description:课程商品列表
     * @Date: 10:49 2017/10/18
     */
    @Override
    public Result courseGoodsList(Integer currentPage, Integer pageSiz) throws Exception {
        String hql = "from ACourseGood where isadded=1 order by inputTime desc";
        String count = "select count(courseId) from ACourseGood where isadded=1 order by inputTime desc";
        Page<ACourseGood> page = aCourseGoodDao.findPage(currentPage, pageSiz, hql, count);
        List<ACourseGood> listS =new ArrayList<ACourseGood>();
        if(page!=null&&page.getList()!=null&&page.getList().size()>0){
            for (ACourseGood aCourseGood : page.getList()) {
                List<CourseGoodsOrder> list = courseGoodsOrderIBaseDao.findList(" from CourseGoodsOrder where (orderState=1 or orderState=3) and courseId=" + aCourseGood.getCourseId());
                Ebranchschool one = ebranchschoolIBaseDao.getOne(Ebranchschool.class, aCourseGood.getEid());
                aCourseGood.setAddress(one.getProvince()+one.getCity()+one.getDistricts()+one.getAddress());
                if(list.size()>=aCourseGood.getPeopleNum()){
                    aCourseGood.setFlag(2);
                }else{
                    aCourseGood.setFlag(1);
                }
                listS.add(aCourseGood);
            }
        }
        page.setList(listS);
        return ResultUtil.success("课程商品列表", page);
    }

    /**
     * @Author: 徐慷慨
     * @Description:下订单
     * @Date: 13:33 2017/12/11
     */
    @Override
    public Result orderIn(Integer nid, Integer courseId, String phone) throws Exception {
//        Agent one = agentIBaseDao.findOne(" from Agent where agentTel=" + phone);
//        if (one == null) {
//            return ResultUtil.error(2, "您输入的推荐人查找不到");
//        }
        List<CourseGoodsOrder> list = courseGoodsOrderIBaseDao.findList(" from CourseGoodsOrder where courseId=" + courseId + " and orderState=1");
        ACourseGood one1 = aCourseGoodDao.getOne(ACourseGood.class, courseId);
        if (list.size() == one1.getPeopleNum()) {
            return ResultUtil.error(2, "改课程订购人数已满");
        }
        CourseGoodsOrder courseGoodsOrder = new CourseGoodsOrder();
        courseGoodsOrder.setCourseId(courseId);
        courseGoodsOrder.setNid(nid);
        courseGoodsOrder.setPayMoney(one1.getCoursePrice());
        //courseGoodsOrder.setAgentId();
        String orderNo=System.currentTimeMillis() + RandomUtil.randomString(5);//订单号
        courseGoodsOrder.setOrderNo(orderNo);
        courseGoodsOrder.setOrderTime(new Timestamp(System.currentTimeMillis()));
        courseGoodsOrder.setOrderState(2);
        int i = courseGoodsOrderIBaseDao.save(courseGoodsOrder);
        if (i > 0) {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setAgentName(one.getAgentName());
//            orderDetail.setAgentTel(one.getAgentTel());
//            orderDetail.setPeopleNum(one1.getPeopleNum());
//            orderDetail.setCourseTime(one1.getCourseTime());
//            orderDetail.setOrderNo(orderNo);
//            orderDetail.setCoursePrice(one1.getCoursePrice());
//            orderDetail.setCoursePrice(one1.getVipPrice());
//            orderDetail.setLinkTel(one1.getLinkTel());
//            if (one1.getEid() != null && one1.getEid() > 0) {
//                Ebranchschool one2 = ebranchschoolIBaseDao.getOne(Ebranchschool.class, one1.getEid());
//                orderDetail.setAddress(one2.getProvince() + one2.getCity() + one2.getDistricts() + one2.getAddress());
//            } else {
//                orderDetail.setAddress("待定");
//            }
            Map map =new HashMap();
            map.put("orderNo",orderNo);
            return ResultUtil.success("订单生成成功！", map);
        }
        return ResultUtil.error(2, "订单生成失败！");
    }

    /**
     * @Author: 徐慷慨
     * @Description: 未付款 已付款 已开课列表 type 1：未付款 2 已付款 3 已开课
     * @Date: 15:18 2017/12/11
     */
    @Override
    public Result orderList(Integer currentPage, Integer pageSize, Integer nid, Integer type) throws Exception {
        String hql = "";
        String count = "";
        if (type == 1) {
            hql = " from CourseGoodsOrder where orderState!=1 and nid=" + nid + " order by orderTime desc";
            count = "select count(*) from CourseGoodsOrder where orderState!=1 and nid=" + nid + " order by orderTime desc";
        }
        if (type == 2) {
            hql = " from CourseGoodsOrder where orderState=1 and nid=" + nid + " order by orderTime desc";
            count = "select count(*) from CourseGoodsOrder where orderState=1 and nid=" + nid + " order by orderTime desc";
        }
        if (type == 3) {
            hql = " select a from CourseGoodsOrder a,ACourseGood b where a.courseId=b.courseId and a.orderState=1 and b.isArrang=1 and a.nid=" + nid + " order by a.orderTime desc";
            count = "select count(*) from CourseGoodsOrder a,ACourseGood b where a.courseId=b.courseId and a.orderState=1 and b.isArrang=1 and a.nid=" + nid + " order by a.orderTime desc";
        }
        Page<CourseGoodsOrder> page = courseGoodsOrderIBaseDao.findPage(currentPage, pageSize, hql, count);
        List<OrderList> lists = new ArrayList<OrderList>();
        if (page.getList()!=null&&page.getList().size() > 0) {
            if (type != 3) {
                for (CourseGoodsOrder courseGoodsOrder : page.getList()) {
                    OrderList orderList = new OrderList();
                    ACourseGood aCourseGood = aCourseGoodDao.getOne(ACourseGood.class, courseGoodsOrder.getCourseId());
                    orderList.setCourseName(aCourseGood.getCourseName());
                    orderList.setCover(aCourseGood.getCover());
                    orderList.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                    orderList.setCourseId(courseGoodsOrder.getCourseId());
                    orderList.setCoursePrice(aCourseGood.getCoursePrice());
                    orderList.setVipPrice(aCourseGood.getVipPrice());
                    Integer flag=1;
                    if(type==1){
                       //todo
                        List<CourseGoodsOrder> listOrder = courseGoodsOrderIBaseDao.findList(" from CourseGoodsOrder where (orderState=1 or orderState=3) and courseId=" + courseGoodsOrder.getCourseId());
                        if(listOrder.size()>=aCourseGood.getPeopleNum()){
                            flag=2;
                        }else{
                            flag=1;
                        }
                    }
                    orderList.setOrderNo(courseGoodsOrder.getOrderNo());
                    orderList.setFlag(flag);
                    lists.add(orderList);
                }
            } else {
                for (CourseGoodsOrder courseGoodsOrder : page.getList()) {
                    OrderList orderList = new OrderList();
                    ACourseGood aCourseGood = aCourseGoodDao.getOne(ACourseGood.class, courseGoodsOrder.getCourseId());
                    orderList.setCourseName(aCourseGood.getCourseName());
                    orderList.setCover(aCourseGood.getCover());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = simpleDateFormat.format(new Date());
                    List<ClassHour> list = classHourIBaseDao.findList(" from ClassHour where courseId=" + courseGoodsOrder.getCourseId() + " and goClassTime <='" + format+"'");

                    List<StuGoClass> list1 = stuGoClassIBaseDao.findList("select a from StuGoClass a,ClassHour b where a.nid=" + nid + " and b.courseId=" + courseGoodsOrder.getCourseId() + " and a.isSign=1 and a.classHourId=b.classHourId");
                    orderList.setCourseId(courseGoodsOrder.getCourseId());
                    orderList.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                    orderList.setClassFinshNum(list.size());
                    orderList.setUserFinshNum(list1.size());
                    orderList.setCourseTime(aCourseGood.getCourseTime());
                    orderList.setCoursePrice(aCourseGood.getCoursePrice());
                    orderList.setVipPrice(aCourseGood.getVipPrice());
                    lists.add(orderList);
                }
            }
        }
        Map map = new HashMap();
        map.put("currentPage", page.getCurrentPage());
        map.put("pageSize", page.getPageSize());
        map.put("total",page.getTotal());
        map.put("list", lists);
        map.put("allPage", page.getAllpage());
        return ResultUtil.success("未付款 已付款 已开课列表", map);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 查看课程详情
     * @Date: 8:58 2017/12/12
     */
    @Override
    public Result courseGoodsDetail(Integer courseId, Integer nid) throws Exception {
        ACourseGood one = aCourseGoodDao.getOne(ACourseGood.class, courseId);
        if(one==null){
            return ResultUtil.error(2,"参数错误！");
        }
        String  address="";
        if(one.getEid()!=null){
            Ebranchschool ebranchschool = ebranchschoolIBaseDao.getOne(Ebranchschool.class, one.getEid());
            address=ebranchschool.getProvince() + ebranchschool.getCity() + ebranchschool.getDistricts() + ebranchschool.getAddress();
        }
        List<CourseGoodsOrder> listOrder = courseGoodsOrderIBaseDao.findList(" from CourseGoodsOrder where (orderState=1 or orderState=3) and courseId=" + courseId);
        Integer flag=1;
        if(listOrder.size()>=one.getPeopleNum()){
            flag=2;
        }else{
           flag=1;
        }
        Map map = new HashMap();
        map.put("time",one.getGoClassTime());
        map.put("courseName", one.getCourseName());
        map.put("goClassWeek", one.getGoClassWeek());
        map.put("linkTel", one.getLinkTel());
        map.put("courseInTroduct", one.getCourseIntroduce());
        map.put("address",address );
        map.put("courseId",one.getCourseId());
        map.put("cover",one.getCover());
        map.put("flag",flag);
        List<CourseGoodsDetail> listBean = new ArrayList<CourseGoodsDetail>();
        List<ClassHour> list = classHourIBaseDao.findList(" from ClassHour where courseId=" + courseId);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

        for (ClassHour classHour : list) {
            CourseGoodsDetail courseGoodsDetail = new CourseGoodsDetail();
            courseGoodsDetail.setCover(one.getCover());
            courseGoodsDetail.setGoTime(classHour.getGoClassTime().toString().substring(0,classHour.getGoClassTime().toString().length()-11));
            courseGoodsDetail.setNum(classHour.getClassHourName());
            StuGoClass stuGoClass = stuGoClassIBaseDao.findOne(" from StuGoClass where nid=" + nid + " and classHourId=" + classHour.getClassHourId());
            String substring = classHour.getGoClassTime().toString().substring(0, classHour.getGoClassTime().toString().length() - 11);
            Date parse = simpleDateFormat.parse(substring);
            String format = simpleDateFormat.format(new Date());
            Date date=simpleDateFormat.parse(format);
            StuAppointment one1 = stuAppointmentIBaseDao.findOne(" from StuAppointment where classHourId="+classHour.getClassHourId());


            if(date.getTime()<parse.getTime()){
                courseGoodsDetail.setCourseState(1);
            }else{
                courseGoodsDetail.setCourseState(2);
            }
            if(one1!=null){
                courseGoodsDetail.setFinishState(3);
            }else{
                if (stuGoClass != null) {
                    courseGoodsDetail.setFinishState(1);
                } else {
                    courseGoodsDetail.setFinishState(2);
                }
            }

            listBean.add(courseGoodsDetail);
        }
        map.put("list", listBean);
        return ResultUtil.success("查看课程详情", map);
    }

    /**
     * @Author: 徐慷慨
     * @Description:预约课程列表
     * @Date: 9:44 2017/12/12
     */
    @Override
    public Result courseYuYue(String courseName, Integer num) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        List<ACourseGood> list = aCourseGoodDao.findList(" select a from ACourseGood a,ClassHour b where a.courseName='" + courseName + "' and a.endClassTime >'" + format + "' and a.isAppointment=1 and a.courseId=b.courseId and b.goClassTime >='"+format+"'and  b.classHourName=" + num );
        List<Map> list1 = new ArrayList<Map>();
        if (list.size() > 0) {
            for (ACourseGood aCourseGood : list) {
                Ebranchschool ebranchschool = ebranchschoolIBaseDao.getOne(Ebranchschool.class, aCourseGood.getEid());
                Map map = new HashMap();
                map.put("courseName", aCourseGood.getCourseName());
                map.put("goClassWeek", aCourseGood.getGoClassWeek());
                map.put("linkTel", aCourseGood.getLinkTel());
                map.put("courseInTroduct", aCourseGood.getCourseIntroduce());
                map.put("address", ebranchschool.getProvince() + ebranchschool.getCity() + ebranchschool.getDistricts() + ebranchschool.getAddress());
                list1.add(map);
            }
        }
        Map map = new HashMap();
        map.put("list", list);
        return ResultUtil.success("预约课程列表", map);
    }

    /**
     * @Author: 徐慷慨
     * @Description:预约课程
     * @Date: 10:36 2017/12/12
     */
    @Override
    public Result yuYue(Integer nid, Integer courseId, Integer num) throws Exception {
        ACourseGood one1 = aCourseGoodDao.getOne(ACourseGood.class, courseId);
        List<CourseGoodsOrder> list = courseGoodsOrderIBaseDao.findList(" from CourseGoodsOrder where courseId=" + courseId + " and orderState=1");
        ClassHour one = classHourIBaseDao.findOne(" from ClassHour where courseId=" + courseId + " and classHourName='" + num + "'");
        List<StuAppointment> list1 = stuAppointmentIBaseDao.findList(" from StuAppointment where courseId=" + courseId + " and classHourId=" + one.getClassHourId());
        if((list.size()+list1.size())>=one1.getPeopleNum()){
            return ResultUtil.error(2,"该课程已经预约满了");
        }
        if(one==null){
            return ResultUtil.error(2, "发生null异常");
        }
        StuAppointment stuAppointment = new StuAppointment();
        stuAppointment.setAppointmentTime(new Timestamp(System.currentTimeMillis()));
        stuAppointment.setNid(nid);
        stuAppointment.setClassHourId(one.getClassHourId());
        stuAppointment.setCourseId(courseId);
        int save = stuAppointmentIBaseDao.save(stuAppointment);
        if (save > 0) {
            return ResultUtil.success("预约成功!");
        } else {
            return ResultUtil.error(2, "预约失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:教习评价
     * @Date: 10:38 2017/12/12
     */
    @Override
    public Result teachCommon(CourseComment courseComment) throws Exception {
        System.out.println(courseComment.toString());
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        System.out.println("::::::::::::::::"+courseComment.getCourseId());
        ACourseGood one1 = aCourseGoodDao.findOne(" from ACourseGood where courseId="+courseComment.getCourseId()+" and endClassTime <'"+format+"'");
        //ACourseGood one1 = aCourseGoodDao.findOne(" from ACourseGood where courseId=4");
        if(one1==null){
            return ResultUtil.error(2,"该课程暂未结课，不能评价");
        }
        int save = courseCommentIBaseDao.save(courseComment);
        if (save > 0) {
            return ResultUtil.success("评价成功!");
        } else {
            return ResultUtil.error(2, "评价失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:学生签到
     * @Date: 10:40 2017/12/12
     */
    @Override
    public Result sign(Integer nid, Integer courseId) throws Exception {
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne("from  CourseGoodsOrder where courseId=" + courseId + " and orderState=1 and nid="+nid);
        StuAppointment one1 = stuAppointmentIBaseDao.findOne("from  StuAppointment WHERE courseId=" + courseId + " and nid=" + nid);
        if(one==null&&one1==null){
            return ResultUtil.error(2,"你个傻叉都没报名，签到个屁");
        }
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        ClassHour one2 = classHourIBaseDao.findOne(" from ClassHour where courseId=" + courseId + " and goClassTime like '" + format + "%'");
        if(one2==null){
            return ResultUtil.error(2,"你今天不用上课");
        }
        StuGoClass stuGoClass =new StuGoClass();
        stuGoClass.setNid(nid);
        stuGoClass.setIsSign(1);
        if(one==null){
            stuGoClass.setIsAppointment(1);
        }else{
            stuGoClass.setIsAppointment(2);
        }
        stuGoClass.setClassHourId(one2.getClassHourId());
        StuGoClass one3 = stuGoClassIBaseDao.findOne(" from StuGoClass where nid=" + nid + " and classHourId=" + one2.getClassHourId());
        if(one3!=null){
            return ResultUtil.error(2,"今天已经签到过了！");
        }
        int save = stuGoClassIBaseDao.save(stuGoClass);
        if (save > 0) {
            return ResultUtil.success("上课签到成功!");
        } else {
            return ResultUtil.error(2, "上课签到失败！");
        }
    }
    /**
     * @Author: 徐慷慨
     * @Description: 点击支付回显信息(未支付详情)
     * @Date: 14:06 2017/12/12
     */
    @Override
    public Result orderDetails(String orderNo) throws Exception{
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne("from CourseGoodsOrder where orderNo='" + orderNo + "'");
        ACourseGood aCourseGood = aCourseGoodDao.getOne(ACourseGood.class, one.getCourseId());
        Ebranchschool ebranchschool = ebranchschoolIBaseDao.getOne(Ebranchschool.class, aCourseGood.getEid());
        Agent agent = agentIBaseDao.findOne(" from Agent where nid="+one.getNid());
        Map map = new HashMap();
        map.put("courseName", aCourseGood.getCourseName());
        map.put("peopleNum", aCourseGood.getPeopleNum());
        map.put("num", aCourseGood.getCourseTime());
        map.put("linkTel", aCourseGood.getLinkTel());
        map.put("address", ebranchschool.getProvince() + ebranchschool.getCity() + ebranchschool.getDistricts() + ebranchschool.getAddress());
        map.put("agentName", agent.getAgentName());
        map.put("agentTel", agent.getAgentTel());
        map.put("cover",aCourseGood.getCover());
        map.put("coursePrice",aCourseGood.getCoursePrice());
        map.put("vipPrice",aCourseGood.getVipPrice());
        return ResultUtil.success("点击支付回显信息!",map);
    }
    /**
     * 已支付详情
     * @param orderNo
     * @return
     * @throws Exception
     */
    @Override
    public Result orderPayDetails(String orderNo) throws Exception{
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne("from CourseGoodsOrder where orderNo='" + orderNo + "'");
        ACourseGood aCourseGood = aCourseGoodDao.getOne(ACourseGood.class, one.getCourseId());
        Ebranchschool ebranchschool = ebranchschoolIBaseDao.getOne(Ebranchschool.class, aCourseGood.getEid());
        Agent agent = agentIBaseDao.findOne("from Agent where nid="+one.getNid());
        Map map = new HashMap();
        map.put("courseName", aCourseGood.getCourseName());
        map.put("peopleNum", aCourseGood.getPeopleNum());
        map.put("num", aCourseGood.getCourseTime());
        map.put("linkTel", aCourseGood.getLinkTel());
        map.put("address", ebranchschool.getProvince() + ebranchschool.getCity() + ebranchschool.getDistricts() + ebranchschool.getAddress());
        map.put("agentName", agent!=null?agent.getAgentName():"");
        map.put("agentTel", agent!=null?agent.getAgentTel():"");
        map.put("payMoney",one.getPayMoney());
        map.put("coursePrice",aCourseGood.getCoursePrice());
        map.put("vipPrice",aCourseGood.getVipPrice());
        map.put("payWay",one.getPayWay());
        map.put("cover",aCourseGood.getCover());
        map.put("orderNo",one.getOrderNo());
        return ResultUtil.success("已支付详情!",map);
    }

    /**
     * @Author: 徐慷慨
     * @Description:查询订单是否支付成功
     * @Date: 15:59 2017/12/18
     */
    @Override
    public Result orderPaySuccess(String orderNo) throws Exception{
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne("from CourseGoodsOrder where orderNo='" + orderNo + "'");
        Map map=new HashMap();
        if(one.getOrderState()==1){
            map.put("state","true");
            map.put("orderNo",one.getOrderNo());
            return ResultUtil.success("支付成功",map);
        }else{
            map.put("stata",false);
            map.put("orderNo",one.getOrderNo());
            return ResultUtil.success("支付失败",map);
        }

    }

    /**
     * @Author: 徐慷慨
     * @Description:删除订单
     * @Date: 14:29 2017/12/19
     */
    @Override
    public Result delete(String orderNo) throws Exception{
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne("from CourseGoodsOrder where orderNo='" + orderNo + "'");
        if(one!=null){
            boolean delete = courseGoodsOrderIBaseDao.delete(one);
            if(delete){
                return ResultUtil.success("删除成功！");
            }else{
                return ResultUtil.error(2,"删除失败！");
            }
        }
        return ResultUtil.error(2,"查找不到该订单");
    }
    /**
     * @Author: 徐慷慨
     * @Description:支付时判断该课程是不是已经购买人数满了
     * @Date: 16:52 2017/12/19
     */
    @Override
    public Result isPay(String orderNo) throws Exception {
        CourseGoodsOrder one = courseGoodsOrderIBaseDao.findOne("from CourseGoodsOrder where orderNo='" + orderNo + "'");
        if(one!=null){
            ACourseGood one1 = aCourseGoodDao.getOne(ACourseGood.class, one.getCourseId());

            List<CourseGoodsOrder> list = courseGoodsOrderIBaseDao.findList(" from CourseGoodsOrder where courseId=" + one1.getCourseId()+" and (orderState=1 or orderState=3)");
            if(list.size()<one1.getPeopleNum()){
                 return ResultUtil.success("可以购买");
            }
            return ResultUtil.error(2,"课程已经满了");
        }
        return ResultUtil.error(2,"查找不到该订单");
    }
}
