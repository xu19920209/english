package com.tuyue.webModules.tests.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.tests.bean.ClassTestList;
import com.tuyue.webModules.tests.biz.ItestBiz;
import org.hibernate.cfg.annotations.MapBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/8.
 */
@Service
@Transactional
public class TestBizImpl implements ItestBiz {
    @Autowired
    private IBaseDao<Rule> ruleIBaseDao;
    @Autowired
    private IBaseDao<RuleLevel> ruleLevelIBaseDao;
    @Autowired
    private IBaseDao<Oclass> oclassIBaseDao;
    @Autowired
    private IBaseDao<Nstudent> nstudentIBaseDao;
    @Autowired
    private IBaseDao<SclassWork> sclassWorkIBaseDao;
    @Autowired
    private IBaseDao<TestGrade> testGradeIBaseDao;
    @Autowired
    private IBaseDao<Tests> testsIBaseDao;

    /**
     * @Author: 徐慷慨
     * @Description:添加测试题
     * @Date: 8:58 2017/11/8
     */
    public Result inTest(Tests tests) {
        tests.setUpTime(new Timestamp(System.currentTimeMillis()));
        int save = testsIBaseDao.save(tests);
        if (save > 0) {
            return ResultUtil.success("添加成功！");
        }
        return ResultUtil.error(2, "添加失败！");
    }

    /**
     * @Author: 徐慷慨
     * @Description: 删除测试题
     * @Date: 9:00 2017/11/8
     */

    public Result deTest(Integer testId) throws Exception {
        Tests one = testsIBaseDao.getOne(Tests.class, testId);
        if (one != null) {
            List<Oclass> list = oclassIBaseDao.findList(" from Oclass where testid=" + testId);
            if (list.size()>0) {
                return ResultUtil.error(2, "该测试题正被班级所用不能删除");
            } else {
                boolean delete = testsIBaseDao.delete(one);
                if (delete) {
                    return ResultUtil.success("删除成功！");
                } else {
                    return ResultUtil.error(2, "删除失败！");
                }
            }

        }
        return ResultUtil.error(2, "删除失败！找不到改试题");

    }

    /**
     * @param testId
     * @Author: 徐慷慨
     * @Description:根据id查询试题
     * @Date: 9:00 2017/11/8
     */

    public Result testById(Integer testId) throws Exception {
        Tests one = testsIBaseDao.getOne(Tests.class, testId);
        if (one != null) {
            return ResultUtil.success("查找成功！", one);
        }
        return ResultUtil.error(2, "找不到改试题");
    }

    /**
     * @param tests
     * @Author: 徐慷慨
     * @Description: 修改测试题
     * @Date: 9:02 2017/11/8
     */
    @Override
    public Result upTest(Tests tests) throws Exception {
        tests.setUpTime(new Timestamp(System.currentTimeMillis()));
        boolean update = testsIBaseDao.update(tests);
        if (update) {
            return ResultUtil.success("修改测试题成功！");
        }
        return ResultUtil.error(2, "修改测试题失败！");
    }

    /**
     * @Author: 徐慷慨
     * @Description:测试列表
     * @Date: 9:03 2017/11/8
     */
    @Override
    public Result testList(Integer eid) throws Exception {
        List<Tests> list = testsIBaseDao.findList(" from Tests where eid=" + eid);
        return ResultUtil.success("测试列表！", list);
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改班级测试题
     * @Date: 9:05 2017/11/8
     */

    @Override
    public Result upTestClass(Integer oid, Integer testId) throws Exception {
        if (oid == null || oid <= 0 || testId == null || testId <= 0) {
            return ResultUtil.error(2, "参数不能为空");
        }
        int i = oclassIBaseDao.deleteWithHql("update Oclass set testId=" + testId + " where oid=" + oid);

        if (i > 0) {
            nstudentIBaseDao.deleteWithHql("update Nstudent set isTest=2 where oid="+oid);
            testGradeIBaseDao.deleteWithHql(" delete from TestGrade where oid="+oid);
            return ResultUtil.success("修改成功！");
        }
        return ResultUtil.error(2, "修改是失败！");
    }

    /**
     * @Author: 徐慷慨
     * @Description:班级测试题列表 完成情况
     * @Date: 14:56 2017/11/9
     */
    public Result classTestList(Integer currentPage, Integer pageSize, Integer eid) throws Exception {
        String HQL = "from Oclass where eid=" + eid + " and testId>0";
        String count = "select count(*) from Oclass where eid=" + eid + "testId>0";
        Page<Oclass> page = oclassIBaseDao.findPage(currentPage, pageSize, HQL, count);
        List<ClassTestList> listList = new ArrayList<ClassTestList>();
        for (Oclass oclass : page.getList()) {
            Tests one = testsIBaseDao.getOne(Tests.class, oclass.getTestId());
            if (oclass.getTestId() > 0&&one!=null) {
                ClassTestList classTestList = new ClassTestList();
                classTestList.setClassName(oclass.getClassName());
                classTestList.setOid(oclass.getOid());
                List<Nstudent> nstudents = nstudentIBaseDao.findList(" from Nstudent where oid=" + oclass.getOid());
                if(nstudents==null||nstudents.size()==0){
                    continue;
                }
                classTestList.setClassNum(nstudents.size());
                String nid = "";
                for (Nstudent nstudent : nstudents) {
                    nid += nstudent.getNid() + ",";
                }
                nid = nid.substring(0, nid.length() - 1);
                long count1 = testGradeIBaseDao.findCount(" select count(*) from TestGrade where nid in (" + nid + ") and testId="+oclass.getTestId());

                classTestList.setTestName(one.getName());
                classTestList.setFinishNum((int) count1);
                Timestamp time = one.getUpTime();
                Date date = new Date();
                date = time;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                classTestList.setTime(sdf.format(date));
                listList.add(classTestList);
            }
        }
        Map map = new HashMap();
        map.put("list", listList);
        map.put("total", page.getTotal());
        map.put("allPage", page.getAllpage());
        map.put("pageSize", page.getPageSize());
        map.put("currentPage", page.getCurrentPage());
        return ResultUtil.success("班级测试题列表 完成情况", map);
    }


    /**
     * @Author: 徐慷慨
     * @Description:归档
     * @Date: 15:16 2017/11/9
     */
    public Result backlevl(Integer oid) throws Exception {
        try {
            Oclass one1 = oclassIBaseDao.getOne(Oclass.class, oid);
            if(one1==null){
                return ResultUtil.error(2,"参数不能为空！");
            }
            List<TestGrade> list = testGradeIBaseDao.findList(" from TestGrade where oid="+one1.getOid());
            if(list.size()<=0){
                return ResultUtil.error(2,"学生未完成测试题，不能归档");
            }
            //第一步将所有学生归档为4真实档
            int i = nstudentIBaseDao.deleteWithHql(" update Nstudent set level=4  where oid=" + oid);
            //第二步 计算每个档次的人数
            List<TestGrade> gradeList = testGradeIBaseDao.findList("from TestGrade where oid=" + oid + " and testId=" + one1.getTestId() + " and grade<95 order by grade desc"); //查询该班级所有学生的测试题答题情况除过95分以上的 （95分直接进入真实档）
            //第三步拿到所有成绩的最高分和最低分
            TestGrade max = testGradeIBaseDao.findOne("from TestGrade where oid=" + oid + " and grade=(select max(grade) from TestGrade where oid=" + oid +")");
            TestGrade min = testGradeIBaseDao.findOne("from TestGrade where oid=" + oid + " and grade=(select min(grade) from TestGrade where oid=" + oid +")");
            int one = 0;
            int two = 0;
            int three = 0;
            String oneScope = "";
            String twoScope = "";
            String threeScope = "";
            //查询新规则
            List<RuleLevel> ruleLevels = ruleLevelIBaseDao.findList(" from RuleLevel where ruleId=" + one1.getRuleId());
            for (RuleLevel ruleLevel : ruleLevels) {
                if (ruleLevel.getLevel() == 1) {
                    one = (int) (gradeList.size() * (ruleLevel.getNum() / 100));
                    oneScope = ruleLevel.getGradeScope();
                }
                if (ruleLevel.getLevel() == 2) {
                    two = (int) (gradeList.size() * (ruleLevel.getNum() / 100));
                    twoScope = ruleLevel.getGradeScope();
                }
                if (ruleLevel.getLevel() == 3) {
                    three = (int) (gradeList.size() * (ruleLevel.getNum() / 100));
                    threeScope = ruleLevel.getGradeScope();
                }
            }
            if(one==0){
                return ResultUtil.success("归档成功！");
            }
            String oneid = "";
            String twoid = "";
            String threeid = "";
            for (int i1 = 0; i1 < one; i1++) {
                oneid += gradeList.get(i1).getNid() + ",";
            }
            for (int i1 = one; i1 < one + two; i1++) {
                twoid += gradeList.get(i1).getNid() + ",";
            }
            for (int i1 = one + two; i1 < gradeList.size(); i1++) {
                threeid += gradeList.get(i1).getNid() + ",";
            }
            if (oneid.endsWith(",")) {
                oneid = oneid.substring(0, oneid.length() - 1);
            }
            if (twoid.endsWith(",")) {
                twoid = twoid.substring(0, twoid.length() - 1);
            }
            if (threeid.endsWith(",")) {
                threeid = threeid.substring(0, threeid.length() - 1);
            }
            if (oneid.length() <= 0) {
                oneid = "-1,-2";
            }
            if (twoid.length() <= 0) {
                twoid = "-1,-2";
            }
            if (threeid.length() <= 0) {
                threeid = "-1,-2";
            }
            //重新归档
            int oneNum = nstudentIBaseDao.deleteWithHql(" update Nstudent set level=1 where nid in(" + oneid + ")");
            int twoNum = nstudentIBaseDao.deleteWithHql(" update Nstudent set level=2 where nid in(" + twoid + ")");
            int threeNum = nstudentIBaseDao.deleteWithHql(" update Nstudent set level=3 where nid in(" + threeid + ")");
            //计算每个学生的系数
            int lower = 0;//最低基准分
            int high = 0;//最高基准分
            String[] strings = new String[2];
            //计算第一档系数
            String[] onesplit = oneid.split(",");
            strings = oneScope.split("-");
            lower = Integer.parseInt(strings[0]);
            high = Integer.parseInt(strings[1]);
            for (String s : onesplit) {
                //查询该学生的成绩
                TestGrade testGrade = testGradeIBaseDao.findOne(" from TestGrade where nid=" + Integer.parseInt(s) + " and testId=" + one1.getTestId());
                if(testGrade!=null&&min!=null&&max!=null){
                    int i2 = (int) (lower + (high - lower) * ((testGrade.getGrade() - min.getGrade()) / (max.getGrade() - min.getGrade())));
                    double f1 = new BigDecimal((float) i2 / testGrade.getGrade()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    testGrade.setSysGrade(i2);
                    testGrade.setCoefficient(f1);
                    testGradeIBaseDao.update(testGrade);
                    int withHql = nstudentIBaseDao.deleteWithHql(" update Nstudent set coefficient=" + f1 + " and nid=" + Integer.parseInt(s));
                }
            }
            //计算第2档系数
            String[] twosplit = twoid.split(",");
            strings = twoScope.split("-");
            lower = Integer.parseInt(strings[0]);
            high = Integer.parseInt(strings[1]);
            for (String s : twosplit) {
                //查询该学生的成绩
                    TestGrade testGrade = testGradeIBaseDao.findOne(" from TestGrade where nid=" + Integer.parseInt(s) + " and testId=" + one1.getTestId());
                if (testGrade != null && min != null && max != null) {
                    int i2 = (int) (lower + (high - lower) * ((testGrade.getGrade() - min.getGrade()) / (max.getGrade() - min.getGrade())));
                    double f1 = new BigDecimal((float) i2 / testGrade.getGrade()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    testGrade.setSysGrade(i2);
                    testGrade.setCoefficient(f1);
                    testGradeIBaseDao.update(testGrade);
                    int withHql = nstudentIBaseDao.deleteWithHql(" update Nstudent set coefficient=" + f1 + " and nid=" + Integer.parseInt(s));
                }
            }
            //计算第3档系数
            String[] threesplit = twoid.split(",");
            strings = threeScope.split("-");
            lower = Integer.parseInt(strings[0]);
            high = Integer.parseInt(strings[1]);
            for (String s : threesplit) {
                //查询该学生的成绩
                TestGrade testGrade = testGradeIBaseDao.findOne(" from TestGrade where nid=" + Integer.parseInt(s) + " and testId=" + one1.getTestId());
                if (testGrade != null && min != null && max != null) {
                    int i2 = (int) (lower + (high - lower) * ((testGrade.getGrade() - min.getGrade()) / (max.getGrade() - min.getGrade())));
                    double f1 = new BigDecimal((float) i2 / testGrade.getGrade()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    testGrade.setSysGrade(i2);
                    testGrade.setCoefficient(f1);
                    testGradeIBaseDao.update(testGrade);
                    int withHql = nstudentIBaseDao.deleteWithHql(" update Nstudent set coefficient=" + f1 + " and nid=" + Integer.parseInt(s));
                }
            }
            return ResultUtil.success("归档成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(2, "归档失败！");
        }
    }
}
