package com.tuyue.appModules.personalCenter.biz.impl;

import com.tuyue.appModules.personalCenter.bean.UserArrays;
import com.tuyue.appModules.personalCenter.biz.IpersonalCenterBiz;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Md5Util;
import com.tuyue.util.ObjectCopyUtil;
import com.tuyue.webModules.rule.bean.ClassRuledetiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
@Service
@Transactional
public class PersonalCenterBizImpl implements IpersonalCenterBiz {
    @Autowired
    private IBaseDao<Nstudent> ndao;
    @Autowired
    private IBaseDao<Xsignal> xdao;
    @Autowired
    private IBaseDao<Ysignaldetails> ydao;
    @Autowired
    private IBaseDao<TstuWorkFinish> tstuWorkFinishIBaseDao;
    @Autowired
    private IBaseDao<AllText> allTextIBaseDao;
    @Autowired
    private IBaseDao<PointLike> pointLikeIBaseDao;
    @Autowired
    private IBaseDao<AllTestAnswer> allTestAnswerIBaseDao;

    /**
     * @Author: 徐慷慨
     * @Description:注册用户
     * @Date: 9:37 2017/9/14
     */
    public Result register(Nstudent nstudent) throws Exception {
        System.out.println("openId"+nstudent.getOpenId());
        System.out.println("student = "+nstudent.toString());
        Nstudent one = ndao.findOne("from Nstudent where username='" + nstudent.getUsername() + "'");
        System.out.println("one = "+one);
        if (one != null) {
            System.out.println("已注册");
            return ResultUtil.error(2, "改手机号已注册过，请您登录");
        } else {
            System.out.println("---------------------");
            nstudent.setCreateTime(new Timestamp(System.currentTimeMillis()));
            nstudent.setIsType(2);
            nstudent.setSex(1);
            int save = ndao.save(nstudent);
            System.out.println("save = "+save);
            if (save > 0) {
                Nstudent one1 = ndao.getOne(Nstudent.class, save);
                Result success = ResultUtil.success("注册成功！",one1);
                System.out.println("success = "+success);
                return success;
            } else {
                System.out.println("注册失败");
                return ResultUtil.error(2, "注册失败！");
            }
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:登录
     * @Date: 9:38 2017/9/14
     */
    public Result login(Nstudent nstudent) throws Exception {
        Nstudent one = ndao.findOne(" from Nstudent where username='" + nstudent.getUsername() + "' and password='" + nstudent.getPassword() + "'");
        if (one != null) {
            String username = one.getUsername();
            String userNameMd5 = Md5Util.toMD5(username);
            one.setAlias(userNameMd5);
            return ResultUtil.success("登陆成功！", one);
        } else {
            return ResultUtil.error(2, "用户名或密码错误！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:忘记密码
     * @Date: 9:39 2017/9/14
     */
    public Result forgetPwd(Nstudent nstudent) throws Exception {
        Nstudent one = ndao.findOne(" from Nstudent where username='" + nstudent.getUsername() + "'");
        if (one != null) {
            one.setPassword(nstudent.getPassword());
            boolean update = ndao.update(one);
            if (update) {
                return ResultUtil.success("修改成功！");
            } else {
                return ResultUtil.error(2, "修改失败！");
            }
        }
        return ResultUtil.error(2, "手机号不存在");
    }

    /**
     * @Author: 徐慷慨
     * @Description: 修改用户
     * @Date: 10:01 2017/9/14
     */
    public Result updataUser(Nstudent nstudent) throws Exception {
        Nstudent one = ndao.getOne(Nstudent.class, nstudent.getNid());
        if (one == null) {
            return ResultUtil.error(2, "参数错误！");
        }
        if(nstudent.getSex()!=null){
            one.setSex(nstudent.getSex());
        }
        if(nstudent.getStudentName()!=null){
            one.setStudentName(nstudent.getStudentName());
        }
        if(nstudent.getImgUrl()!=null){
            one.setImgUrl(nstudent.getImgUrl());
        }
        boolean update = ndao.update(one);
        Nstudent one1 = ndao.getOne(Nstudent.class, nstudent.getNid());
        if (update) {
            return ResultUtil.success("修改成功！", one1);
        } else {
            return ResultUtil.error(2, "修改失败！");
        }
    }


    /**
     * @Author: 徐慷慨
     * @Description:根据id查用户
     * @Date: 10:06 2017/9/14
     */
    public Result findById(Integer nid) throws Exception {
        Nstudent one = ndao.getOne(Nstudent.class, nid);
        if (one == null) {
            return ResultUtil.error(2, "参数错误！");
        } else {
            String username = one.getUsername();
            String userNameMd5 = Md5Util.toMD5(username);
            one.setAlias(userNameMd5);
            return ResultUtil.success("查找用户成功！", one);
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:签到
     * @Date: 20:10 2017/9/14
     */
    public Result sign(Integer nid) throws Exception {
        Nstudent one1 = ndao.getOne(Nstudent.class, nid);
        if (one1 == null) {
            return ResultUtil.error(2, "参数错误！");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String time = sdf.format(date);
        Calendar lastDate = Calendar.getInstance();
        lastDate.roll(Calendar.DATE, -1);//日期回滚1天
        String format = sdf.format(lastDate.getTime());
        Xsignal one = xdao.findOne(" from Xsignal where nid=" + nid + " and time='" + time + "'");
        int num = 0;
        if (one == null) {
            Xsignal exist = xdao.findOne(" from Xsignal where nid=" + nid);
            Xsignal yesteday = xdao.findOne(" from Xsignal where nid=" + nid + " and time='" + format + "'");
            if (exist == null) {
                Xsignal xsignal = new Xsignal();
                if (yesteday != null) {
                    xsignal.setCount(yesteday.getCount() + 1);
                    num = yesteday.getCount() + 1;
                } else {
                    xsignal.setCount(1);
                    num = 1;
                }
                xsignal.setTime(time);
                xsignal.setNid(nid);
                int save = xdao.save(xsignal);
                if (save > 0) {
                    Ysignaldetails ysignaldetails = new Ysignaldetails();
                    ysignaldetails.setNid(nid);
                    ysignaldetails.setTime(time);
                    ydao.save(ysignaldetails);
                    if (one1.getIntegral() != null) {
                        one1.setIntegral(one1.getIntegral() + 5);
                    } else {
                        one1.setIntegral(5);
                    }
                    ndao.update(one1);
                    Map map = new HashMap();
                    // TODO: 2017/9/15
                    map.put("num", num);
                    return ResultUtil.success("签到成功！", map);
                } else {
                    return ResultUtil.error(2, "签到失败！");
                }
            } else {
                if (yesteday != null) {
                    exist.setCount(yesteday.getCount() + 1);
                    num = yesteday.getCount() + 1;
                } else {
                    exist.setCount(1);
                    num = 1;
                }
                exist.setTime(time);
                boolean update = xdao.update(exist);
                if (update) {
                    Ysignaldetails ysignaldetails = new Ysignaldetails();
                    ysignaldetails.setNid(nid);
                    ysignaldetails.setTime(time);
                    ydao.save(ysignaldetails);
                    if (one1.getIntegral() != null) {
                        one1.setIntegral(one1.getIntegral() + 5);
                    } else {
                        one1.setIntegral(5);
                    }
                    ndao.update(one1);
                    Map map = new HashMap();
                    map.put("num", num);
                    return ResultUtil.success("签到成功！", map);
                } else {
                    return ResultUtil.error(2, "签到失败！");
                }
            }
        } else {
            return ResultUtil.error(2, "今天已经签到过了！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description: 用户成绩列表
     * @Date: 13:55 2017/11/9
     */
    public Result gradeList(Integer nid) throws Exception {
        Nstudent one = ndao.getOne(Nstudent.class, nid);
        List<Nstudent> nstudents = ndao.findList(" from Nstudent where oid=" + one.getOid());
        String nids = "";
        for (Nstudent nstudent : nstudents) {
            nids += nstudent.getNid() + ",";
        }
        nids = nids.substring(0, nids.length() - 1);
        List<TstuWorkFinish> list = tstuWorkFinishIBaseDao.findList(" FROM TstuWorkFinish where nid in (" + nids + ") and isConsult=2 and finishTime in (SELECT MAX(finishTime) FROM TstuWorkFinish  GROUP BY nid) AND TO_DAYS(finishTime) = TO_DAYS(NOW()) order by sysGrade  desc");
        List<AllTestAnswer> list2 = allTestAnswerIBaseDao.findList(" FROM AllTestAnswer where nid in (" + nids + ")  and answerTime in (SELECT MAX(answerTime) FROM answerTime  GROUP BY nid) AND TO_DAYS(answerTime) = TO_DAYS(NOW()) order by grade desc");
        TstuWorkFinish tstuWorkFinish1 = null;
        int j = 0;
        int rank = 0;
        UserArrays userArrays = new UserArrays();
        List<UserArrays> list1 = new ArrayList<UserArrays>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (list.size() > 0) {
            for (TstuWorkFinish tstuWorkFinish : list) {
                if (tstuWorkFinish.getNid() == nid) {
                    tstuWorkFinish1 = tstuWorkFinish.clone();
                }
                if (list2.size() > 0) {
                    for (AllTestAnswer allTestAnswer : list2) {
                        if (tstuWorkFinish.getNid() == allTestAnswer.getNid()) {
                            if (allTestAnswer.getGrade() > tstuWorkFinish.getSysGrade()) {
                                double a = allTestAnswer.getGrade();
                                tstuWorkFinish.setSysGrade((int) a);
                                tstuWorkFinish.setStartTime(allTestAnswer.getStartTime());
                                tstuWorkFinish.setEndTime(allTestAnswer.getEndTime());
                            }
                        }
                    }
                }
            }
            Collections.sort(list, new Comparator<TstuWorkFinish>() {
                public int compare(TstuWorkFinish o1, TstuWorkFinish o2) {
                    if (o1.getSysGrade() > o2.getSysGrade()) {
                        return -1;
                    }
                    if (o1.getSysGrade() == o2.getSysGrade()) {
                        return 0;
                    }
                    return 1;
                }
            });
            if (tstuWorkFinish1 != null)
                list.add(0, tstuWorkFinish1);
            else {
                tstuWorkFinish1 = new TstuWorkFinish();
                tstuWorkFinish1.setNid(nid);
                tstuWorkFinish1.setSysGrade(0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(new Date());
                tstuWorkFinish1.setStartTime(format);
                tstuWorkFinish1.setEndTime(format);
                list.add(0, tstuWorkFinish1);
            }


            for (int i = 0; i < list.size(); i++) {
                UserArrays userArrays1 = new UserArrays();
                TstuWorkFinish tstuWorkFinish = list.get(i);
                Nstudent one1 = ndao.getOne(Nstudent.class, tstuWorkFinish.getNid());
                userArrays1.setImgUrl(one1.getImgUrl());
                userArrays1.setGrade(tstuWorkFinish.getSysGrade());
                userArrays1.setUsername(one1.getStudentName()!=null ? one1.getStudentName():" ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date2 = sdf.parse(tstuWorkFinish.getEndTime());
                Date date1 = sdf.parse(tstuWorkFinish.getStartTime());
                int c = (int) ((date2.getTime() - date1.getTime()) / 1000);
                userArrays1.setTime(String.valueOf(c));
                list1.add(userArrays1);
            }
            UserArrays arrays = list1.get(0);
            list1.remove(0);
            Collections.sort(list1, new Comparator<UserArrays>() {
                public int compare(UserArrays o1, UserArrays o2) {
                    if (o1.getGrade() == o2.getGrade()) {
                        if (Integer.parseInt(o1.getTime()) < Integer.parseInt(o2.getTime())) {
                            return -1;
                        }
                    }
                    return 0;
                }
            });
            UserArrays ll = list1.get(0);
            userArrays.setTime(ll.getTime());
            userArrays.setUsername(ll.getUsername());
            userArrays.setGrade(ll.getGrade());
            userArrays.setImgUrl(ll.getImgUrl());
            userArrays.setNum(ll.getNum());
            userArrays.setType(ll.getType());
            for (UserArrays userArrays1 : list1) {
                j++;
                if (userArrays1.getUsername().equals(arrays.getUsername())) {
                    rank = j;
                    break;
                }
            }
            list1.add(0, arrays);
            map.put("one", userArrays);
            map.put("list", list1);
            map.put("rank", rank);
        }else{
            userArrays.setTime("0");
            userArrays.setUsername(one.getStudentName()!=null ? one.getStudentName():" ");
            userArrays.setGrade(0);
            userArrays.setImgUrl(one.getImgUrl());
            userArrays.setNum(0);
            userArrays.setType(0);
            UserArrays userArrays1 = ObjectCopyUtil.copy(userArrays, UserArrays.class, UserArrays.class);
            list1.add(userArrays1);
            map.put("one", userArrays);
            map.put("list", list1);
            map.put("rank", 1);
        }
        return ResultUtil.success("用户成绩列表", map);
    }

    /**
     * @Author: 徐慷慨
     * @Description:点赞
     * @Date: 16:17 2017/11/9
     */
    public Result pointLike(PointLike pointLike) throws Exception {
        PointLike one = pointLikeIBaseDao.findOne(" from PointLike where nid=" + pointLike.getNid() + " and byNid=" + pointLike.getNid() + " and AND TO_DAYS(pointTime) = TO_DAYS(NOW())");
        if (one != null) {
            pointLikeIBaseDao.delete(one);
            return ResultUtil.success("取消点赞成功");
        } else {
            pointLike.setPointTime(new Timestamp(System.currentTimeMillis()));
            pointLikeIBaseDao.save(pointLike);
        }
        return ResultUtil.success("点赞成功！");
    }
    /**
     * @Author: 徐慷慨
     * @Description:根据微信的openId判断用户是否存在
     * @Date: 19:38 2017/12/18
     */
    @Override
    public Nstudent selectByopenId(String openId) throws Exception{
        Nstudent one = ndao.findOne(" from Nstudent where openId='" + openId + "'");
        return one;
    }
}
