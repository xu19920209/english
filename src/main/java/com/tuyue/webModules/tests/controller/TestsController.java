package com.tuyue.webModules.tests.controller;

import com.tuyue.pojo.Tests;
import com.tuyue.result.Result;
import com.tuyue.webModules.tests.biz.ItestBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/8.
 */
@Controller
@ResponseBody
@RequestMapping("web/tests")
public class TestsController {
    @Autowired
    private ItestBiz itestBiz;

    /**
     * @Author: 徐慷慨
     * @Description:添加测试题
     * @Date: 8:58 2017/11/8
     */
@RequestMapping("inTest.do")
    public Result inTest(@RequestBody Tests tests) throws Exception {
    if(tests.getTestId()>0){
        return itestBiz.upTest(tests);
    }
    return itestBiz.inTest(tests);
    }


    /**
     * @Author: 徐慷慨
     * @Description: 删除测试题
     * @Date: 9:00 2017/11/8
     */
    @RequestMapping("deTest.do")
    public Result deTest(Integer testId) throws Exception {
        return itestBiz.deTest(testId);
    }

    /**
     * @param testId
     * @Author: 徐慷慨
     * @Description:根据id查询试题
     * @Date: 9:00 2017/11/8
     */
    @RequestMapping("testById.do")
    public Result testById(Integer testId) throws Exception {
        return itestBiz.testById(testId);
    }

    /**
     * @param tests
     * @Author: 徐慷慨
     * @Description: 修改测试题
     * @Date: 9:02 2017/11/8
     */
    @RequestMapping("upTest.do")
    public Result upTest(@RequestBody Tests tests) throws Exception {
        if(tests.getTestId()>0){
            return itestBiz.upTest(tests);
        }
        return itestBiz.inTest(tests);
    }

    /**
     * @Author: 徐慷慨
     * @Description:测试列表
     * @Date: 9:03 2017/11/8
     */
    @RequestMapping("testList.do")
    public Result testList(Integer eid) throws Exception {
        return itestBiz.testList(eid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改班级测试题
     * @Date: 9:05 2017/11/8
     */
    @RequestMapping("upTestClass.do")
    public Result upTestClass(Integer oid, Integer testId) throws Exception {
        return itestBiz.upTestClass(oid, testId);
    }

    /**
     * @Author: 徐慷慨
     * @Description:班级测试题列表 完成情况
     * @Date: 14:56 2017/11/9
     */
    @RequestMapping("classTestList.do")
    public Result classTestList(Integer currentPage,Integer pageSize,Integer eid) throws Exception{
        if(currentPage==null||currentPage<=0){
            currentPage=1;
        }
        if(pageSize==null||pageSize<=0){
            pageSize=10;
        }
        return itestBiz.classTestList(currentPage,pageSize,eid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:归档
     * @Date: 15:16 2017/11/9
     */
    @RequestMapping("backlevl.do")
    public Result backlevl(Integer oid) throws Exception{
        return itestBiz.backlevl(oid);
    }
}