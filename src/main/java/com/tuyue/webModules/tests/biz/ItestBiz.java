package com.tuyue.webModules.tests.biz;

import com.tuyue.pojo.Tests;
import com.tuyue.result.Result;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/8.
 */
public interface ItestBiz {

    /**
     * @Author: 徐慷慨
     * @Description:添加测试题
     * @Date: 8:58 2017/11/8
     */
    public Result inTest(Tests tests);

    /**
     * @Author: 徐慷慨
     * @Description: 删除测试题
     * @Date: 9:00 2017/11/8
     */

    public Result deTest(Integer testId) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:根据id查询试题
       * @param testId
     * @Date: 9:00 2017/11/8
     */

    public Result testById(Integer testId) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description: 修改测试题
       * @param tests
     * @Date: 9:02 2017/11/8
     */
    public Result upTest(Tests tests) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:测试列表
     * @Date: 9:03 2017/11/8
     */
    public Result testList(Integer eid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:修改班级测试题
     * @Date: 9:05 2017/11/8
     */

    public Result upTestClass(Integer oid,Integer testId) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:班级测试题列表 完成情况
     * @Date: 14:56 2017/11/9
     */
    public Result classTestList(Integer currentPage,Integer pageSize,Integer eid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:归档
     * @Date: 15:16 2017/11/9
     */
    public Result backlevl(Integer oid) throws Exception;
}
