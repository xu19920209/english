package com.tuyue.webModules.adult.biz;


import com.tuyue.result.Result;
import com.tuyue.util.Page;
import com.tuyue.webModules.adult.bean.InsertWorBean;
import com.tuyue.webModules.adult.bean.VadultWorkBean;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/11.
 */
public interface IAdultWorkBiz {


    /**
     * @Author: 徐慷慨
     * @Description:给成人布置作业
     * @Date: 15:48 2017/9/13
     */
    public Result insetAdltWork(InsertWorBean insertWorBean) throws Exception;


    /**
     * @Author: 王金海
     * @Description: 成人布置作业伪删除
     * @Date: 9:44 2017/9/14
     */
    boolean del(String vids) throws Exception;

    /**
     * @Author: 王金海
     * @Description: 成人布置作业列表
     * @Date: 10:16 2017/9/14
     */
    Page<VadultWorkBean> list(Integer currentPage, Integer pageSize) throws Exception;
}
