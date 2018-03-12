package com.tuyue.webModules.courseGoods.biz.impl;

import com.tuyue.appModules.weChatGongZhong.bean.KaiKe;
import com.tuyue.appModules.weChatGongZhong.util.WeiUtil;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Dateutil;
import com.tuyue.util.JPushUtil;
import com.tuyue.util.Md5Util;
import com.tuyue.util.Page;
import com.tuyue.webModules.courseGoods.bean.ClassChart;
import com.tuyue.webModules.courseGoods.bean.CourseHourDetailsList;
import com.tuyue.webModules.courseGoods.bean.PkMangerList;
import com.tuyue.webModules.courseGoods.bean.StuPayList;
import com.tuyue.webModules.courseGoods.biz.CourseGoodBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
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
public class WebCourseGoodBizImpl implements CourseGoodBiz {
    @Autowired
    private IBaseDao<ACourseGood> aCourseGoodDao;
    @Autowired
    private IBaseDao<Ebranchschool> ebranchschoolIBaseDao;
    @Autowired
    private IBaseDao<CourseGoodsOrder> courseGoodsOrderIBaseDao;
    @Autowired
    private IBaseDao<StuAppointment> stuAppointmentIBaseDao;
    @Autowired
    private IBaseDao<ClassHour> classHourIBaseDao;
    @Autowired
    private IBaseDao<StuGoClass> stuGoClassIBaseDao;
    @Autowired
    private IBaseDao<Nstudent> nstudentIBaseDao;

    /**
     * @Author: 徐慷慨
     * @Description:添加商品
     * @Date: 9:56 2017/10/18
     */
    @Override
    public Result inCourseGoods(ACourseGood aCourseGood) {
        aCourseGood.setInputTime(new Timestamp(System.currentTimeMillis()));
        String valueOf = String.valueOf(System.currentTimeMillis());
        String s = String.valueOf((int) (Math.random() * 9 + 1) * 1000);
        aCourseGood.setItemNo(valueOf + s);
        aCourseGood.setIsAdded(1);
        aCourseGood.setIsArrang(2);
        aCourseGood.setIsAppointment(2);
        ACourseGood aCourseGood1 = new ACourseGood();
        int save = aCourseGoodDao.save(aCourseGood);
        if (save > 0) {
            return ResultUtil.success("添加商品列表成功！");
        } else {
            return ResultUtil.error(2, "创建失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:根据id查看商品
     * @Date: 9:58 2017/10/18
     */
    @Override
    public Result courseGoodsById(Integer id) throws Exception {
        ACourseGood one = aCourseGoodDao.getOne(ACourseGood.class, id);
        Ebranchschool ebranchschool = ebranchschoolIBaseDao.getOne(Ebranchschool.class, one.getEid());
        one.setAddress(ebranchschool.getName());
        if (one != null) {
            return ResultUtil.success("根据id查看商品", one);
        }
        return ResultUtil.error(2, "根据id查看商品");
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改课程商品
     * @Date: 9:59 2017/10/18
     */
    @Override
    public Result upCourseGoods(ACourseGood aCourseGood) throws Exception {
        ACourseGood one = aCourseGoodDao.getOne(ACourseGood.class, aCourseGood.getCourseId());
        if (aCourseGood.getIsAdded() != null && aCourseGood.getIsAdded() == 2) {
            if (aCourseGood.getEndClassTime() != null) {
                if (aCourseGood.getIsArrang() == 1 && (aCourseGood.getEndClassTime().getTime() > System.currentTimeMillis())) {
                    return ResultUtil.error(2, "该课程还未结课不能下架");
                }
            }
            List<CourseGoodsOrder> list = courseGoodsOrderIBaseDao.findList("from CourseGoodsOrder where courseId=" + aCourseGood.getCourseId());
            if (list.size() > 0) {
                return ResultUtil.error(2, "该课程已被学生购买不能下架");
            }
        }
        if (aCourseGood.getCourseName() != null && aCourseGood.getCourseName().trim().length() > 0) {
            one.setCourseName(aCourseGood.getCourseName());
        }
        if (aCourseGood.getCoursePrice() != null && aCourseGood.getCoursePrice() > 0) {
            one.setCoursePrice(aCourseGood.getCoursePrice());
        }
        if (aCourseGood.getCourseTime() != null && aCourseGood.getCourseTime() > 0) {
            one.setCourseTime(aCourseGood.getCourseTime());
        }
        if (aCourseGood.getPeopleNum() != null && aCourseGood.getPeopleNum() > 0) {
            one.setPeopleNum(aCourseGood.getPeopleNum());
        }
        if (aCourseGood.getCommission() != null && aCourseGood.getCommission() > 0) {
            one.setCommission(aCourseGood.getCommission());
        }
        if (aCourseGood.getVipPrice() != null && aCourseGood.getVipPrice() > 0) {
            one.setVipPrice(aCourseGood.getVipPrice());
        }
        if (aCourseGood.getEid() != null && aCourseGood.getEid() > 0) {
            one.setEid(aCourseGood.getEid());
        }
        if (aCourseGood.getGoClassWeek() != null && aCourseGood.getGoClassWeek().trim().length() > 0) {
            one.setGoClassWeek(aCourseGood.getGoClassWeek());
        }
        if (aCourseGood.getGoClassTime() != null && aCourseGood.getGoClassTime().trim().length() > 0) {
            one.setGoClassTime(aCourseGood.getGoClassTime());
        }
        if (aCourseGood.getTeacherName() != null && aCourseGood.getTeacherName().trim().length() > 0) {
            one.setTeacherName(aCourseGood.getTeacherName());
        }
        if (aCourseGood.getLinkTel() != null && aCourseGood.getLinkTel().trim().length() > 0) {
            one.setLinkTel(aCourseGood.getLinkTel());
        }
        if (aCourseGood.getIsAdded() != null && aCourseGood.getIsAdded() > 0) {
            one.setIsAdded(aCourseGood.getIsAdded());
        }
        if (aCourseGood.getCover() != null && aCourseGood.getCover().trim().length() > 0) {
            one.setCover(aCourseGood.getCover());
        }
        if (aCourseGood.getCourseIntroduce() != null && aCourseGood.getCourseIntroduce().trim().length() > 0) {
            one.setCourseIntroduce(aCourseGood.getCourseIntroduce());
        }
        if (aCourseGood.getDetails() != null && aCourseGood.getDetails().trim().length() > 0) {
            one.setDetails(aCourseGood.getDetails());
        }
        boolean update = aCourseGoodDao.update(one);
        if (update) {
            return ResultUtil.success("修改课程商品成功");
        } else {
            return ResultUtil.error(2, "修改课程商品失败");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:课程商品列表
     * @Date: 10:49 2017/10/18
     */
    @Transactional
    public Result courseGoodsList(Integer currentPage, Integer pageSiz) throws Exception {
        String hql = "from ACourseGood";
        String count = "select count(courseId) from ACourseGood";
        Page<ACourseGood> page = aCourseGoodDao.findPage(currentPage, pageSiz, hql, count);
        return ResultUtil.success("课程商品列表", page);
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改上下架
     * @Date: 9:32 2017/10/20
     */
    @Override
    public Result upAdd(ACourseGood aCourseGood) throws Exception {
        ACourseGood one = aCourseGoodDao.getOne(ACourseGood.class, aCourseGood.getCourseId());
        one.setIsAdded(aCourseGood.getIsAdded());
        boolean update = aCourseGoodDao.update(one);
        if (aCourseGood.getIsAdded() != null && aCourseGood.getIsAdded() == 2) {
            if (aCourseGood.getEndClassTime() != null) {
                if (aCourseGood.getIsArrang() == 1 && (aCourseGood.getEndClassTime().getTime() > System.currentTimeMillis())) {
                    return ResultUtil.error(2, "该课程还未结课不能下架");
                }
            }
            List<CourseGoodsOrder> list = courseGoodsOrderIBaseDao.findList("from CourseGoodsOrder where courseId" + aCourseGood.getCourseId());
            if (list.size() > 0) {
                return ResultUtil.error(2, "该课程已被学生购买不能下架");
            }

        }
        if (update) {
            return ResultUtil.success("修改上下架");
        } else {
            return ResultUtil.error(2, "修改上下架失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:排课管理列表
     * @Date: 14:19 2017/12/7
     */

    @Override
    public Result paiKeMangerList(Integer currentPage, Integer pageSize) throws Exception {
        String hql = " from ACourseGood";
        String count = "select count(*) from ACourseGood";
        Page<ACourseGood> page = aCourseGoodDao.findPage(currentPage, pageSize, hql, count);
        List<PkMangerList> pkMangerLists = new ArrayList<PkMangerList>();
        if (page.getList().size() <= 0) {
            return ResultUtil.success("排课管理列表", page);
        }
        for (ACourseGood aCourseGood : page.getList()) {
            PkMangerList pkMangerList = new PkMangerList();
            pkMangerList.setCourseId(aCourseGood.getCourseId());
            pkMangerList.setCourseName(aCourseGood.getCourseName());
            if (aCourseGood.getEid() != null) {
                Ebranchschool one = ebranchschoolIBaseDao.getOne(Ebranchschool.class, aCourseGood.getEid());
                if (one != null) {
                    pkMangerList.setSchoolName(one.getName());
                    pkMangerList.setAddress(one.getAddress());
                }

            }

            pkMangerList.setCourseTime(aCourseGood.getCourseTime());//节数
            pkMangerList.setPeopleNum(aCourseGood.getPeopleNum());//座位总数
            List<CourseGoodsOrder> list = courseGoodsOrderIBaseDao.findList(" from CourseGoodsOrder where courseId=" + aCourseGood.getCourseId() + " and orderState=1");
            pkMangerList.setPayPeopleNum(list.size());
            pkMangerList.setIsArrang(aCourseGood.getIsArrang());
            pkMangerList.setIsAppointment(aCourseGood.getIsAppointment());
            pkMangerLists.add(pkMangerList);
        }
        Page pages = new Page();
        pages.setList(pkMangerLists);
        pages.setCurrentPage(page.getCurrentPage());
        pages.setPageSize(page.getPageSize());
        pages.setallPage(page.getAllpage());
        pages.setTotal(page.getTotal());
        return ResultUtil.success("排课管理列表", pages);
    }

    /**
     * @Author: 徐慷慨
     * @Description:课程课时详情列表
     * @Date: 15:19 2017/12/7
     */
    @Override
    public Result courseHourDetails(Integer courseId, Integer currentPage, Integer pageSize) throws Exception {
        List<CourseHourDetailsList> lists = new ArrayList<CourseHourDetailsList>();//返回的list
        ACourseGood one = aCourseGoodDao.getOne(ACourseGood.class, courseId);
        if(one==null){
            return ResultUtil.error(2,"查询不到该商品");
        }
        if (one.getIsArrang() == 2) {
            Map map = new HashMap();
            map.put("currentPage", 0);
            map.put("pageSize", 0);
            map.put("list", lists);
            map.put("allPage", 0);
            map.put("total", 0);
            map.put("courseName", one.getCourseName());
            if (one.getStartClassTime() != null) {
                map.put("startTime", one.getStartClassTime().toString());
            } else {
                map.put("startTime", "");
            }

            return ResultUtil.success("课程课时详情列表", map);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String hql = "from ClassHour where goClassTime <'" + df + "' and courseId=" + courseId;
        String count = "select count(*) from ClassHour where goClassTime <'" + df + "' and courseId=" + courseId;
        Page<ClassHour> page = classHourIBaseDao.findPage(currentPage, pageSize, hql, count);

        int i = 0;
        for (ClassHour classHour : page.getList()) {
            i = i++;
            long counts = stuGoClassIBaseDao.findCount("select count(*) from StuGoClass where  classHourId=" + classHour.getClassHourId());
            CourseHourDetailsList courseHourDetailsList = new CourseHourDetailsList();
            courseHourDetailsList.setCurrentHour(Integer.parseInt(classHour.getClassHourName()));
            courseHourDetailsList.setGoClassNum((int) counts);
            courseHourDetailsList.setPeopleNum(one.getPeopleNum());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(classHour.getGoClassTime());
            courseHourDetailsList.setGoClassTime(format);
            lists.add(courseHourDetailsList);

        }
        Map map = new HashMap();
        map.put("currentPage", page.getCurrentPage());
        map.put("pageSize", page.getPageSize());
        map.put("list", lists);
        map.put("total", page.getTotal());
        map.put("allPage", page.getAllpage());
        map.put("courseName", one.getCourseName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(one.getStartClassTime());
        map.put("startTime", format);
        return ResultUtil.success("课程课时详情列表", map);
    }

    /**
     * @Author: 徐慷慨
     * @Description:购买详情列表
     * @Date: 16:08 2017/12/7
     */
    @Override
    public Result payCourseDetails(Integer courseId, Integer currentPage, Integer pageSize) throws Exception {
        String hql = "from CourseGoodsOrder where orderState=1 and courseId=" + courseId;
        String count = "select count(*) from CourseGoodsOrder where orderState=1 and courseId=" + courseId;
        Page<CourseGoodsOrder> page = courseGoodsOrderIBaseDao.findPage(currentPage, pageSize, hql, count);

        List<StuPayList> lists = new ArrayList<StuPayList>();
        if (page==null||page.getList()==null||page.getList().size() <= 0) {
            Map map = new HashMap();
            map.put("currentPage", 0);
            map.put("pageSize", 0);
            map.put("list", lists);
            map.put("allPage", 0);
            map.put("total", 0);
            return ResultUtil.success("购买详情列表", map);
        }
        for (CourseGoodsOrder courseGoodsOrder : page.getList()) {
            StuPayList stuPayList = new StuPayList();
            Nstudent one = nstudentIBaseDao.getOne(Nstudent.class, courseGoodsOrder.getNid());
            if (one.getStudentName()!=null&&one.getStudentName().length() > 0) {
                stuPayList.setName(one.getStudentName());
            } else {
                stuPayList.setName("注册用户");
            }
            stuPayList.setSex(one.getSex() == 1 ? "男" : "女");
            if (one.getIsType() == 1) {
                stuPayList.setPhone(one.getUrgentTel() != null ? one.getUrgentTel() : "暂无电话");
            } else {
                stuPayList.setPhone(one.getUsername());
            }

            stuPayList.setTime(courseGoodsOrder.getPayTime().toString());
            lists.add(stuPayList);
        }
        Map map = new HashMap();
        map.put("currentPage", page.getCurrentPage());
        map.put("pageSize", page.getPageSize());
        map.put("list", lists);
        map.put("allPage", page.getAllpage());
        map.put("total", page.getTotal());
        return ResultUtil.success("购买详情列表", map);
    }

    /**
     * @Author: 徐慷慨
     * @Description:预约详情列表
     * @Date: 17:27 2017/12/7
     */
    @Override
    public Result appointmentDtail(Integer courseId, Integer currentPage, Integer pageSize) throws Exception {
        String hql = "from StuAppointment where  courseId=" + courseId;
        String count = "select count(*) from StuAppointment  where courseId=" + courseId;
        Page<StuAppointment> page = stuAppointmentIBaseDao.findPage(currentPage, pageSize, hql, count);
        List<StuPayList> lists = new ArrayList<StuPayList>();
        if (page.getList().size() <= 0) {
            Map map = new HashMap();
            map.put("currentPage", 0);
            map.put("pageSize", 0);
            map.put("list", lists);
            map.put("allPage", 0);
            map.put("total", 0);
            return ResultUtil.success("预约详情列表", map);
        }
        if (page != null && page.getList() != null && page.getList().size() > 0) {
            for (StuAppointment stuAppointment : page.getList()) {
                StuPayList stuPayList = new StuPayList();
                Nstudent one = nstudentIBaseDao.getOne(Nstudent.class, stuAppointment.getNid());
                String name = "";
                if (one.getStudentName().length() > 0) {
                    name = one.getStudentName();
                } else {
                    name="注册用户";
                }
                stuPayList.setName(name);
                stuPayList.setSex(one.getSex() == 1 ? "男" : "女");
                if(one.getIsType()==1){//学生
                    stuPayList.setPhone(one.getUrgentTel());
                }else{
                    stuPayList.setPhone(one.getUsername());
                }
                stuPayList.setTime(stuAppointment.getAppointmentTime().toString().substring(0,stuAppointment.getAppointmentTime().toString().length()-2));
                ClassHour classHour = classHourIBaseDao.getOne(ClassHour.class, stuAppointment.getClassHourId());
                stuPayList.setCurrentHour(classHour.getClassHourName());
                lists.add(stuPayList);
            }
        }
        Map map = new HashMap();
        map.put("currentPage", page.getCurrentPage());
        map.put("pageSize", page.getPageSize());
        map.put("list", lists);
        map.put("allPage", page.getAllpage());
        map.put("total", page.getTotal());
        return ResultUtil.success("预约详情列表", map);
    }

    /**
     * @Author: 徐慷慨
     * @Description:排课
     * @Date: 9:35 2017/12/8
     */
    @Override
    @Transactional
    public Result Pk(Integer courseId, String time) throws Exception {
        ACourseGood one = aCourseGoodDao.getOne(ACourseGood.class, courseId);
        if (one == null) {
            return ResultUtil.error(2, "参数错误！");
        }

        Timestamp timestamp = Timestamp.valueOf(time+" 00:00:00");
        one.setStartClassTime(timestamp);
        one.setIsArrang(1);
        boolean update = aCourseGoodDao.update(one);
        if (update) {
            //添加课程课时
            List<ClassHour> classHours = new ArrayList<ClassHour>();
            int j = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(time.substring(0,10));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(calendar.DATE, -1);
            String format = sdf.format(calendar.getTime());
            String times = "";
            for (; 1 == 1; ) {
                Date date1 = sdf.parse(format);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(date1);
                calendar1.add(calendar1.DATE, 1);
                String format1 = sdf.format(calendar1.getTime());
                format = format1;
                String s = Dateutil.currentWeek(format1.toString());
                if (one.getGoClassWeek().indexOf(s) != -1) { //判断今天日期是否是上课日期
                    if (j >= one.getCourseTime()) {
                        break;
                    } else {
                        j++;
                        ClassHour classHour = new ClassHour();
                        classHour.setCourseId(one.getCourseId());
                        classHour.setClassHourName(String.valueOf(j));
                        classHour.setGoClassTime(Timestamp.valueOf(format1+" 00:00:00"));
                        classHours.add(classHour);
                        times = format1;
                    }
                }
            }
            one.setEndClassTime(Timestamp.valueOf(times+" 00:00:00"));
            aCourseGoodDao.update(one);
            classHourIBaseDao.batchSave(classHours);
            List<CourseGoodsOrder> list = courseGoodsOrderIBaseDao.findList("from CourseGoodsOrder where courseId=" + courseId);
            HashMap<String, String> maps = new HashMap<String, String>();
            maps.put("type", String.valueOf(3));  //type  = 1 布置作业 type = 2  老师鼓励学生了
            if (list.size() > 0) {
                String ids = "";
                for (CourseGoodsOrder courseGoodsOrder : list) {
                    ids += courseGoodsOrder.getNid() + ",";
                }
                List<Nstudent> list1 = nstudentIBaseDao.findList(" from Nstudent where nid in (" + ids.substring(0,ids.length()-1) + ")");
                String string[] = new String[list1.size()];
                for (int i = 0; i < list1.size(); i++) {
                    string[i] = Md5Util.toMD5(list1.get(i).getUsername());
                }
                JPushUtil.sendMsgToApp("您的课程信息更新了", "开课提醒", "您购买的课程开课了，快去看看", maps, string);
                JPushUtil.sendMsgToIos("您的课程信息更新了", "您购买的课程开课了，快去看看", "type", maps, string);
                for (CourseGoodsOrder courseGoodsOrder : list) {
                    Nstudent one1 = nstudentIBaseDao.getOne(Nstudent.class, courseGoodsOrder.getNid());
                    if(one1.getOpenId()!=null){
                        KaiKe kaiKe=new KaiKe();
                        kaiKe.sendWxTemplate(one1.getOpenId(),null,one1.getUsername(),one.getCourseName(),one.getGoClassTime(),WeiUtil.accessToken());
                    }
                }
            }
            return ResultUtil.success("排课成功");
        }
        return ResultUtil.error(2, "排课失败！");
    }

    /**
     * @Author: 徐慷慨
     * @Description:开放预约
     * @Date: 9:57 2017/12/8
     */
    @Override
    public Result yuYue(Integer courseId) throws Exception {
        ACourseGood one = aCourseGoodDao.getOne(ACourseGood.class, courseId);
        if (one == null) {
            return ResultUtil.error(2, "参数错误！");
        }
        one.setIsAppointment(1);
        boolean update = aCourseGoodDao.update(one);
        if (update) {
            List<CourseGoodsOrder> list = courseGoodsOrderIBaseDao.findList("from CourseGoodsOrder where courseId=" + courseId);
            HashMap<String, String> maps = new HashMap<String, String>();
            maps.put("type", String.valueOf(3));  //type  = 1 布置作业 type = 2  老师鼓励学生了
            if (list.size() > 0) {
                String ids = "";
                for (CourseGoodsOrder courseGoodsOrder : list) {
                    ids += courseGoodsOrder.getNid() + ",";
                }
                List<Nstudent> list1 = nstudentIBaseDao.findList(" from Nstudent where nid in (" + ids + ")");
                String string[] = new String[list1.size()];
                for (int i = 0; i < list1.size(); i++) {
                    string[i] = Md5Util.toMD5(list1.get(i).getUsername());
                }
                JPushUtil.sendMsgToApp("你有课程可以预约了", "你有课程可以预约了，快去看看", "type", maps, string);
            }
            return ResultUtil.success("开放预约成功");
        }
        return ResultUtil.error(2, "开放预约失败！");
    }

    /**
     * @Author: 徐慷慨
     * @Description:课表接口
     * @Date: 10:00 2017/12/8
     */
    @Override
    public Result classChart(Integer courseId) throws Exception {
        ACourseGood one = aCourseGoodDao.getOne(ACourseGood.class, courseId);
        if (one == null) {
            return ResultUtil.error(2, "参数错误！");
        }
        ClassChart classChart = new ClassChart();
        classChart.setCourseTime(one.getCourseTime());
        classChart.setGoClassWeek(one.getGoClassWeek());
        classChart.setGoClassTime(one.getGoClassTime());
        if(one.getStartClassTime()!=null){

            classChart.setStartClassTime(one.getStartClassTime().toString().substring(0,11));
        }

        return ResultUtil.success("课表接口", classChart);
    }
}
