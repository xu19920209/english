package com.tuyue.webModules.rule.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.rule.bean.*;
import com.tuyue.webModules.rule.biz.IruleBiz;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
@Service
@Transactional
public class RuleBizImpl implements IruleBiz {

    private SessionFactory sessionFactory;
    @Autowired
    private IBaseDao<Rule> ruleIBaseDao;
    @Autowired
    private IBaseDao<RuleLevel> ruleLevelIBaseDao;
    @Autowired
    private IBaseDao<Oclass> oclassIBaseDao;
    @Autowired
    private IBaseDao<Nstudent>nstudentIBaseDao;
    @Autowired
    private IBaseDao<SclassWork>sclassWorkIBaseDao;
    @Autowired
    private IBaseDao<TestGrade> testGradeIBaseDao;
    @Autowired
    private IBaseDao<TstuWorkFinish>tstuWorkFinishIBaseDao;
    @Autowired
    private IBaseDao<Acourse> acourseIBaseDao;

    /**
     * @Author: 徐慷慨
     * @Description:创建规则
     * @Date: 14:34 2017/11/7
     */
    public Result createRule(RuleBean ruleBean) throws Exception {
        Rule rule = new Rule();
        rule.setEid(ruleBean.getEid());
        rule.setRuleName(ruleBean.getRuleName());
        int save = ruleIBaseDao.save(rule);
        if (save > 0) {
            List<RuleLevel> ruleLevelList = ruleBean.getList();
            List<RuleLevel> ruleLevels = new ArrayList<RuleLevel>();
            if(ruleLevelList.size()>0){
                for (RuleLevel ruleLevel : ruleLevelList) {
                    ruleLevel.setRuleId(save);
                    ruleLevels.add(ruleLevel);
                }
            }
            int i = ruleLevelIBaseDao.batchSave(ruleLevels);
            if (i > 0) {
                return ResultUtil.success("创建成功");
            }
            return ResultUtil.error(2, "创建失败！");
        } else {
            return ResultUtil.error(2, "创建失败！");
        }
    }


    /**
     * @Author: 徐慷慨
     * @Description:删除规则
     * @Date: 14:35 2017/11/7
     */
    @Override
    public Result deleteRule(Integer ruleId) throws Exception {
        Rule one = ruleIBaseDao.getOne(Rule.class, ruleId);
        Oclass one1 = oclassIBaseDao.findOne(" from Oclass where eid=" + one.getEid() + " ruleId=" + ruleId);
        if(one1!=null){
            return ResultUtil.error(2,"现有规则已被使用，不能删除");
        }
        int i = ruleLevelIBaseDao.deleteWithHql(" delete from RuleLevel where ruleId=" + ruleId);
        int i1 = ruleIBaseDao.deleteWithHql(" delete from Rule where ruleId=" + ruleId);
        if(i>0||i1>0){
            return ResultUtil.success("删除成功！");
        }
        return ResultUtil.error(2,"删除失败！");
    }

    /**
     * @Author: 徐慷慨
     * @Description:规则列表
     * @Date: 14:36 2017/11/7
     */
    @Override
    public Result ruleList(Integer eid) throws Exception {
        List<Rule> list = ruleIBaseDao.findList(" from Rule where eid=" + eid);
        return  ResultUtil.success("规则列表",list);
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改规则
     * @Date: 14:36 2017/11/7
     */
    @Override
    public Result upRule(RuleBean ruleBean) throws Exception {
        Rule one = ruleIBaseDao.getOne(Rule.class, ruleBean.getRuleId());
        Rule rule = new Rule();
        rule.setRuleId(ruleBean.getRuleId());
        rule.setEid(one.getEid());
        rule.setRuleName(ruleBean.getRuleName());
        Session sessions = ruleIBaseDao.getSessions();
        sessions.evict(one);
        boolean update = ruleIBaseDao.update(rule);
        int i1 = ruleLevelIBaseDao.deleteWithHql(" delete from RuleLevel where ruleId=" + ruleBean.getRuleId());//删除该规则详细信息
        if (update) {
            List<RuleLevel> ruleLevelList = ruleBean.getList();
            List<RuleLevel> ruleLevels = new ArrayList<RuleLevel>();
            for (RuleLevel ruleLevel : ruleLevelList) {
                ruleLevel.setRuLeId(ruleBean.getRuleId());
                ruleLevels.add(ruleLevel);
            }
            int i = ruleLevelIBaseDao.batchSave(ruleLevels);//添加新规则详细信息
            if (i > 0) {
                // 规则添加成功！
                List<Oclass> list = oclassIBaseDao.findList(" from Oclass where eid=" + one.getEid()+" and ruleId="+ruleBean.getRuleId());//使用该规则的班级
                for (Oclass oclass : list) {
                    //SclassWork sclassWork = sclassWorkIBaseDao.findOne(" from sclassWork where oid="+oclass.getOid());
                    long count = nstudentIBaseDao.findCount(" select count(*) from Nstudent where oid=" + oclass.getOid());//该班级所有人数
                    long count1 = testGradeIBaseDao.findCount(" select count(*) from TestGrade where oid=" + oclass.getOid() + " and testId=" + oclass.getTestId());//该班级答测试题人数
                    if(count==count1){ // 该班级所有人已经测试过了，对学生进行归档
                        //第一步将所有学生归档为4真实档
                        int k = nstudentIBaseDao.deleteWithHql(" update Nstudent set level=4, isTest=1 where oid=" + oclass.getOid());
                        //第二步 计算每个档次的人数
                        List<TestGrade> gradeList = testGradeIBaseDao.findList("from TestGrade where oid=" + oclass.getOid() + " and testId=" + oclass.getTestId()+" and grade<95 order by grade"); //查询该班级所有学生的测试题答题情况除过95分以上的 （95分直接进入真实档）
                        //第三步拿到所有成绩的最高分和最低分
                        TestGrade max = testGradeIBaseDao.findOne("from TestGrade where oid=" + oclass.getOid() + " and grade=(select max(grade) from TestGrade where oid=" + oclass.getOid() + ")");
                        TestGrade min = testGradeIBaseDao.findOne("from TestGrade where oid=" + oclass.getOid() + " and grade=(select min(grade) from TestGrade where oid=" + oclass.getOid() + ")");
                        int onenum=0;
                        int two=0;
                        int three=0;
                        String oneScope="";
                        String twoScope="";
                        String threeScope="";
                        //查询新规则
                        List<RuleLevel> newruleLevel = ruleLevelIBaseDao.findList(" from RuleLevel where ruleId="+ruleBean.getRuleId());
                        for (RuleLevel ruleLevel : newruleLevel) {
                            if (ruleLevel.getLevel()==1) {
                                onenum= (int) (gradeList.size()*(ruleLevel.getNum()/100));
                                oneScope=ruleLevel.getGradeScope();
                            }
                            if (ruleLevel.getLevel()==2) {
                                two= (int) (gradeList.size()*(ruleLevel.getNum()/100));
                                twoScope=ruleLevel.getGradeScope();
                            }
                            if (ruleLevel.getLevel()==3) {
                                three= (int) (gradeList.size()*(ruleLevel.getNum()/100));
                                threeScope=ruleLevel.getGradeScope();
                            }
                        }
                        String oneid="";
                        String twoid="";
                        String threeid="";
                        for (int j= 0; j <onenum; j++) {
                            oneid+=gradeList.get(j).getNid()+",";
                        }
                        for (int j = onenum; j< onenum+two; j++) {
                            twoid+=gradeList.get(j).getNid()+",";
                        }
                        for (int j = onenum+two; j < ruleLevels.size(); j++) {
                            threeid+=gradeList.get(j).getNid()+",";
                        }
                        if(oneid.endsWith(",")){
                            oneid=oneid.substring(0,oneid.length()-1);
                        }
                        if(twoid.endsWith(",")){
                            twoid=twoid.substring(0,twoid.length()-1);
                        }
                        if(threeid.endsWith(",")){
                            threeid=threeid.substring(0,threeid.length()-1);
                        }
                        //重新归档
                        int oneNum = nstudentIBaseDao.deleteWithHql(" update Nstudent set level=1 where nid in("+oneid+")");
                        int twoNum = nstudentIBaseDao.deleteWithHql(" update Nstudent set level=2 where nid in("+twoid+")");
                        int threeNum= nstudentIBaseDao.deleteWithHql(" update Nstudent set level=3 where nid in("+threeid+")");
                        //计算每个学生的系数
                        int lower=0;//最低基准分
                        int high=0;//最高基准分
                        String[] strings =new String[2];
                        //计算第一档系数
                        String[] onesplit = oneid.split(",");
                        strings = oneScope.split("-");
                        lower= Integer.parseInt(strings[0]);
                        high= Integer.parseInt(strings[1]);
                        for (String s : onesplit) {
                            //查询该学生的成绩
                            TestGrade one1 = testGradeIBaseDao.findOne(" from TestGrade where nid="+Integer.parseInt(s)+" and testId="+oclass.getTestId());
                            int i2 = (int) (lower + (high - lower) * ((one1.getGrade() - min.getGrade()) / (max.getGrade() - min.getGrade())));
                            double f1 = new BigDecimal((float)i2/one1.getGrade()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            one1.setSysGrade(i2);
                            one1.setCoefficient(f1);
                            testGradeIBaseDao.update(one1);
                            int withHql = nstudentIBaseDao.deleteWithHql(" update Nstudent set coefficient=" + f1 + " and nid=" + Integer.parseInt(s));
                        }
                        //计算第2档系数
                        String[] twosplit = twoid.split(",");
                        strings = twoScope.split("-");
                        lower= Integer.parseInt(strings[0]);
                        high= Integer.parseInt(strings[1]);
                        for (String s : twosplit) {
                            //查询该学生的成绩
                            TestGrade one1 = testGradeIBaseDao.findOne(" from TestGrade where nid="+Integer.parseInt(s)+" and testId="+oclass.getTestId());
                            int i2 = (int) (lower + (high - lower) * ((one1.getGrade() - min.getGrade()) / (max.getGrade() - min.getGrade())));
                            double f1 = new BigDecimal((float)i2/one1.getGrade()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            one1.setSysGrade(i2);
                            one1.setCoefficient(f1);
                            testGradeIBaseDao.update(one1);
                            int withHql = nstudentIBaseDao.deleteWithHql(" update Nstudent set coefficient=" + f1 + " and nid=" + Integer.parseInt(s));
                        }

                        //计算第3档系数
                        String[] threesplit = twoid.split(",");
                        strings = threeScope.split("-");
                        lower= Integer.parseInt(strings[0]);
                        high= Integer.parseInt(strings[1]);
                        for (String s : threesplit) {
                            //查询该学生的成绩
                            TestGrade one1 = testGradeIBaseDao.findOne(" from TestGrade where nid="+Integer.parseInt(s)+" and testId="+oclass.getTestId());
                            int i2 = (int) (lower + (high - lower) * ((one1.getGrade() - min.getGrade()) / (max.getGrade() - min.getGrade())));
                            double f1 = new BigDecimal((float)i2/one1.getGrade()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            one1.setSysGrade(i2);
                            one1.setCoefficient(f1);
                            testGradeIBaseDao.update(one1);
                            int withHql = nstudentIBaseDao.deleteWithHql(" update Nstudent set coefficient=" + f1 + " and nid=" + Integer.parseInt(s));
                        }
                    }
                }
                return ResultUtil.success("修改规则成功");
            }
            return ResultUtil.error(2, "修改规则失败！");
        } else {
            return ResultUtil.error(2, "修改规则失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改班级规则
     * @Date: 14:39 2017/11/7
     */
    public Result upRuleClass(Integer oid,Integer ruleId) throws Exception {
        Oclass one1 = oclassIBaseDao.getOne(Oclass.class, oid);
        one1.setRuleId(ruleId);
        boolean update = oclassIBaseDao.update(one1);
        if(update){
            SclassWork sclassWork = sclassWorkIBaseDao.findOne(" from sclassWork where oid="+oid);
            long count = nstudentIBaseDao.findCount(" select count(*) from Nstudent where oid=" + oid);//该班级所有人数
            long count1 = testGradeIBaseDao.findCount(" select count(*) from TestGrade where oid=" + oid + " and testId=" + one1.getTestId());//该班级答测试题人数
            if(count==count1){ // 该班级所有人已经测试过了，对学生进行归档

                    //第一步将所有学生归档为4真实档
                    int i = nstudentIBaseDao.deleteWithHql(" update Nstudent set level=4, isTest=1  where oid=" + oid);
                    //第二步 计算每个档次的人数
                    List<TestGrade> gradeList = testGradeIBaseDao.findList("from TestGrade where oid=" + oid + " and testId=" + one1.getTestId()+" and grade<95 order by grade"); //查询该班级所有学生的测试题答题情况除过95分以上的 （95分直接进入真实档）
                //第三步拿到所有成绩的最高分和最低分
                TestGrade max = testGradeIBaseDao.findOne("from TestGrade where oid=" + oid + " and grade=(select max(grade) from TestGrade where oid=)" + oid + "");
                TestGrade min = testGradeIBaseDao.findOne("from TestGrade where oid=" + oid + " and grade=(select min(grade) from TestGrade where oid=)" + oid + "");
                    int one=0;
                    int two=0;
                    int three=0;
                String oneScope="";
                String twoScope="";
                String threeScope="";
                    //查询新规则
                    List<RuleLevel> ruleLevels = ruleLevelIBaseDao.findList(" from RuleLevel where ruleId="+ruleId);
                    for (RuleLevel ruleLevel : ruleLevels) {
                        if (ruleLevel.getLevel()==1) {
                            one= (int) (gradeList.size()*(ruleLevel.getNum()/100));
                            oneScope=ruleLevel.getGradeScope();
                        }
                        if (ruleLevel.getLevel()==2) {
                            two= (int) (gradeList.size()*(ruleLevel.getNum()/100));
                            twoScope=ruleLevel.getGradeScope();
                        }
                        if (ruleLevel.getLevel()==3) {
                            three= (int) (gradeList.size()*(ruleLevel.getNum()/100));
                            threeScope=ruleLevel.getGradeScope();
                        }
                    }
                    String oneid="";
                    String twoid="";
                    String threeid="";
                    for (int i1 = 0; i1 < one; i1++) {
                        oneid+=gradeList.get(i1).getNid()+",";
                    }
                    for (int i1 = one; i1 < one+two; i1++) {
                        twoid+=gradeList.get(i1).getNid()+",";
                    }
                    for (int i1 = one+two; i1 < ruleLevels.size(); i1++) {
                        threeid+=gradeList.get(i1).getNid()+",";
                    }
                    if(oneid.endsWith(",")){
                        oneid=oneid.substring(0,oneid.length()-1);
                    }
                    if(twoid.endsWith(",")){
                        twoid=twoid.substring(0,twoid.length()-1);
                    }
                    if(threeid.endsWith(",")){
                        threeid=threeid.substring(0,threeid.length()-1);
                    }
                    //重新归档
                    int oneNum = nstudentIBaseDao.deleteWithHql(" update Nstudent set level=1 where nid in("+oneid+")");
                    int twoNum = nstudentIBaseDao.deleteWithHql(" update Nstudent set level=2 where nid in("+twoid+")");
                    int threeNum= nstudentIBaseDao.deleteWithHql(" update Nstudent set level=3 where nid in("+threeid+")");


                //计算每个学生的系数
                int lower=0;//最低基准分
                int high=0;//最高基准分
                String[] strings =new String[2];
                //计算第一档系数
                String[] onesplit = oneid.split(",");
                strings = oneScope.split("-");
                lower= Integer.parseInt(strings[0]);
                high= Integer.parseInt(strings[1]);
                for (String s : onesplit) {
                    //查询该学生的成绩
                    TestGrade testGrade = testGradeIBaseDao.findOne(" from TestGrade where nid="+Integer.parseInt(s)+" and testId="+one1.getTestId());
                    int i2 = (int) (lower + (high - lower) * ((testGrade.getGrade() - min.getGrade()) / (max.getGrade() - min.getGrade())));
                    double f1 = new BigDecimal((float)i2/testGrade.getGrade()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    testGrade.setSysGrade(i2);
                    testGrade.setCoefficient(f1);
                    testGradeIBaseDao.update(testGrade);
                    int withHql = nstudentIBaseDao.deleteWithHql(" update Nstudent set coefficient=" + f1 + " and nid=" + Integer.parseInt(s));
                }
                //计算第2档系数
                String[] twosplit = twoid.split(",");
                strings = twoScope.split("-");
                lower= Integer.parseInt(strings[0]);
                high= Integer.parseInt(strings[1]);
                for (String s : twosplit) {
                    //查询该学生的成绩
                    TestGrade testGrade = testGradeIBaseDao.findOne(" from TestGrade where nid="+Integer.parseInt(s)+" and testId="+one1.getTestId());
                    int i2 = (int) (lower + (high - lower) * ((testGrade.getGrade() - min.getGrade()) / (max.getGrade() - min.getGrade())));
                    double f1 = new BigDecimal((float)i2/testGrade.getGrade()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    testGrade.setSysGrade(i2);
                    testGrade.setCoefficient(f1);
                    testGradeIBaseDao.update(testGrade);
                    int withHql = nstudentIBaseDao.deleteWithHql(" update Nstudent set coefficient=" + f1 + " and nid=" + Integer.parseInt(s));
                }

                //计算第3档系数
                String[] threesplit = twoid.split(",");
                strings = threeScope.split("-");
                lower= Integer.parseInt(strings[0]);
                high= Integer.parseInt(strings[1]);
                for (String s : threesplit) {
                    //查询该学生的成绩
                    TestGrade testGrade = testGradeIBaseDao.findOne(" from TestGrade where nid="+Integer.parseInt(s)+" and testId="+one1.getTestId());
                    int i2 = (int) (lower + (high - lower) * ((testGrade.getGrade() - min.getGrade()) / (max.getGrade() - min.getGrade())));
                    double f1 = new BigDecimal((float)i2/testGrade.getGrade()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    testGrade.setSysGrade(i2);
                    testGrade.setCoefficient(f1);
                    testGradeIBaseDao.update(testGrade);
                    int withHql = nstudentIBaseDao.deleteWithHql(" update Nstudent set coefficient=" + f1 + " and nid=" + Integer.parseInt(s));
                }
           }
        }
        return ResultUtil.success("修改班级规则成功！");

    }

    /**
     * @Author: 徐慷慨
     * @Description:根据id查看规则
     * @Date: 18:11 2017/11/7
     */
    public Result ruleById(Integer ruleId) throws Exception{
        Rule one = ruleIBaseDao.getOne(Rule.class, ruleId);
        RuleBean ruleBean =new RuleBean();
        ruleBean.setEid(one.getEid());
        ruleBean.setRuleName(one.getRuleName());
        ruleBean.setRuleId(one.getRuleId());
        List<RuleLevel> list = ruleLevelIBaseDao.findList("from RuleLevel where ruleId="+ruleId);
        ruleBean.setList(list);
        return ResultUtil.success("根据id查看规则",ruleBean);
    }

    /**
     * @Author: 徐慷慨
     * @Description:班级规则列表
     * @Date: 11:59 2017/11/8
     */
    public Result ruleClssList(Integer currentPage,Integer pageSize,Integer eid) throws Exception {
        String HQL="select o from Oclass o,Rule r where o.ruleId=r.ruleId AND o.eid="+eid;
        String count="select count(o) from Oclass o,Rule r where o.ruleId=r.ruleId AND o.eid="+eid;
//        Page<Oclass> page = oclassIBaseDao.findPage(currentPage, pageSize, HQL, count);
        List<Oclass> list = oclassIBaseDao.findList("select o from Oclass o,Rule r where o.ruleId=r.ruleId AND o.eid="+eid);
        List< RuleClass> ruleClassList =new ArrayList<RuleClass>();
        for (Oclass oclass : list) {
            RuleClass ruleClass =new RuleClass();
            ruleClass.setClassName(oclass.getClassName());
            ruleClass.setOid(oclass.getOid());
            Rule one = ruleIBaseDao.getOne(Rule.class, oclass.getRuleId());
            ruleClass.setRuleId(one.getRuleId());
            ruleClass.setRuleName(one.getRuleName());
            ruleClassList.add(ruleClass);
        }
//        Map map = new HashMap();
//        map.put("list", ruleClassList);
//        map.put("total", page.getTotal());
//        map.put("allPage", page.getAllpage());
//        map.put("pageSize", page.getPageSize());
//        map.put("currentPage", page.getCurrentPage());
        return ResultUtil.success("班级规则列表",ruleClassList);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 班级规则详细列表
     * @Date: 14:19 2017/11/8
     */

    public Result ruleClssListDetiles(Integer currentPage,Integer pageSize,Integer eid) throws Exception{
//        String HQL="select o from Oclass o,Rule r where o.ruleId=r.ruleId AND o.eid="+eid;
//        String count="select count(o) from Oclass o,Rule r where o.ruleId=r.ruleId AND o.eid="+eid;
//        Page<Oclass> page = oclassIBaseDao.findPage(currentPage, pageSize, HQL, count);
        List<Oclass> list = oclassIBaseDao.findList("select o from Oclass o,Rule r where o.ruleId=r.ruleId AND o.eid="+eid);
        List<RuleClssListDetiles> ruleClssListDetiles =new ArrayList<RuleClssListDetiles>();

        for (Oclass oclass : list) {
            //该班级的规则
            Rule one = ruleIBaseDao.getOne(Rule.class, oclass.getRuleId());
            List<RuleLevel> list1 = ruleLevelIBaseDao.findList("from RuleLevel where ruleId="+oclass.getRuleId());
            RuleLelveBean ruleLelveBean =new RuleLelveBean();
            for (RuleLevel ruleLevel : list1) {
                if(ruleLevel.getLevel()==1){
                    ruleLelveBean.setOne(ruleLevel.getNum()+"%,"+ruleLevel.getGradeScope());
                }
                if(ruleLevel.getLevel()==2){
                    ruleLelveBean.setTwo(ruleLevel.getNum()+"%,"+ruleLevel.getGradeScope());
                }
                if(ruleLevel.getLevel()==3){
                    ruleLelveBean.setThree(ruleLevel.getNum()+"%,"+ruleLevel.getGradeScope());
                }
                ruleLelveBean.setReal("95以上");
            }
           //该班级每个规则人数
            long countOne = nstudentIBaseDao.findCount("select count(*) from Nstudent where level=1 and oid=" + oclass.getOid());
            long countTwo = nstudentIBaseDao.findCount("select count(*) from Nstudent where level=2 and oid=" + oclass.getOid());
            long countThree = nstudentIBaseDao.findCount("select count(*) from Nstudent where level=3 and oid=" + oclass.getOid());
            long countreal = nstudentIBaseDao.findCount("select count(*) from Nstudent where level=4 and oid=" + oclass.getOid());

            RuleClssListDetiles.levelNum levelNum =new RuleClssListDetiles.levelNum();
            levelNum.setOneNum((int) countOne);
            levelNum.setTwoNum((int) countTwo);
            levelNum.setThreeNum((int) countThree);
            levelNum.setRealNum((int) countreal);
            levelNum.setTime("今天");
            List<RuleClssListDetiles.levelNum> numList =new LinkedList<RuleClssListDetiles.levelNum>();
            RuleClssListDetiles.levelNum levelNum1 ;
            RuleClssListDetiles.levelNum levelNum2 ;
            levelNum1=levelNum.clone();
            levelNum2=levelNum.clone();
            levelNum1.setTime("昨天");
            levelNum2.setTime("前天");
            numList.add(levelNum);
            numList.add(levelNum1);
            numList.add(levelNum2);
            RuleClssListDetiles ruleClssListDetiles1 =new RuleClssListDetiles();
            ruleClssListDetiles1.setRuleLelveBean(ruleLelveBean);
            ruleClssListDetiles1.setClassName(oclass.getClassName());
            ruleClssListDetiles1.setOid(oclass.getOid());
            ruleClssListDetiles1.setLevelNums(numList);
            ruleClssListDetiles1.setNum((int) (countOne+countTwo+countThree+countreal));
            ruleClssListDetiles1.setRuleName(one.getRuleName());
            ruleClssListDetiles.add(ruleClssListDetiles1);
        }

//        Map map = new HashMap();
//        map.put("list", ruleClssListDetiles);
//        map.put("total", page.getTotal());
//        map.put("allPage", page.getAllpage());
//        map.put("pageSize", page.getPageSize());
//        map.put("currentPage", page.getCurrentPage());
        return ResultUtil.success("班级规则详细列表",ruleClssListDetiles);
    }

    /**
     * @Author: 徐慷慨
     * @Description:查询每个班级每个档次的详细信息
     * date 1:今天 2昨天 3 前天
     * level 1 第一 2 第二档
     * @Date: 14:56 2017/11/8
     */

    @Override
    public Result classRuledetiles(Integer oid, Integer date , Integer level) throws Exception{
        List<Nstudent> allNun = nstudentIBaseDao.findList(" from Nstudent where oid=" + oid);
        Oclass oclass = oclassIBaseDao.getOne(Oclass.class, oid);
        List<RuleLevel> list2 = ruleLevelIBaseDao.findList(" from RuleLevel where ruleId = "+oclass.getRuleId());
        String scope="";
        String num="";
        for (RuleLevel ruleLevel : list2) {
            if(ruleLevel.getLevel()==level){
                scope=ruleLevel.getGradeScope();
                num=ruleLevel.getNum()+"%";
            }
        }
        Rule one1 = ruleIBaseDao.getOne(Rule.class, oclass.getRuleId());
        //查询改等级的所有学生
        List<Nstudent> nstudents = nstudentIBaseDao.findList(" from Nstudent where oid=" + oid + " and level=" + level);
        String nids="";
        if(nstudents.size()>0){
            for (Nstudent nstudent : nstudents) {
                nids+=nstudent.getNid()+",";
            }
            if(nids.endsWith(",")){
                nids=nids.substring(0,nids.length()-1);
            }
        }
        //查询改等级今天所完成的作业
        List<TstuWorkFinish> list =new ArrayList<TstuWorkFinish>();
        if(date==1){
            list = tstuWorkFinishIBaseDao.findList(" FROM TstuWorkFinish where nid in ("+nids+") and isConsult=2 and finishTime in (SELECT MAX(finishTime) FROM TstuWorkFinish  GROUP BY nid) AND TO_DAYS(finishTime) = TO_DAYS(NOW()) order by workScore desc");
        }
       if(date==2){
           list = tstuWorkFinishIBaseDao.findList(" FROM TstuWorkFinish where nid in ("+nids+") and isConsult=2 and finishTime in (SELECT MAX(finishTime) FROM TstuWorkFinish  GROUP BY nid) AND TO_DAYS(finishTime) = (TO_DAYS(NOW())-1) order by workScore desc");
       }
        if(date==3){
            list = tstuWorkFinishIBaseDao.findList(" FROM TstuWorkFinish where nid in ("+nids+")isConsult=2 and finishTime in (SELECT MAX(finishTime) FROM TstuWorkFinish  GROUP BY nid) AND TO_DAYS(finishTime) = (TO_DAYS(NOW())-2) order by workScore desc");
        }
        List<ClassRuledetiles> list1 =new ArrayList<ClassRuledetiles>();
        for (Nstudent nstudent : nstudents) {
            ClassRuledetiles classRuledetiles =new ClassRuledetiles();
            classRuledetiles.setStudentName(nstudent.getStudentName());
            classRuledetiles.setSex(nstudent.getSex());
            classRuledetiles.setPhone(nstudent.getUrgentTel());
            classRuledetiles.setRealGrade(0);
            classRuledetiles.setSysGrade(0);
            classRuledetiles.setCoefficient(1.0);
            if(list.size()>0){
                for (TstuWorkFinish tstuWorkFinish : list) {
                    if(tstuWorkFinish.getNid()==nstudent.getNid()){
                        SclassWork one = sclassWorkIBaseDao.findOne("from SclassWork where sid='"+tstuWorkFinish.getSid()+"'" );
                        Acourse acourse = acourseIBaseDao.getOne(Acourse.class, one.getAid());
                        classRuledetiles.setCourseName(acourse.getAname());
                        double workScore = tstuWorkFinish.getWorkScore();
                        classRuledetiles.setRealGrade((int) workScore);
                        classRuledetiles.setSysGrade(tstuWorkFinish.getSysGrade());
                        classRuledetiles.setCoefficient(tstuWorkFinish.getCoefficient());
                    }
                }
            }
            list1.add(classRuledetiles);
        }
        Collections.sort(list1, new Comparator<ClassRuledetiles>(){

            /*
             * int compare(Student o1, Student o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            public int compare(ClassRuledetiles o1, ClassRuledetiles o2) {
                if(o1.getRealGrade() > o2.getRealGrade()){
                    return -1;
                }
                if(o1.getRealGrade() == o2.getRealGrade()){
                    return 0;
                }
                return 1;
            }
        });
        Map<String ,Object> map =new HashMap();
        map.put("gradeScope",scope);
        map.put("list",list1);
        map.put("className",oclass.getClassName());
        map.put("classNum",allNun.size());
        map.put("levelNum",nstudents.size());
        map.put("ruleName",one1.getRuleName());
        map.put("num",num);
        return ResultUtil.success("查询每个班级每个档次的详细信息",map);
    }
}
