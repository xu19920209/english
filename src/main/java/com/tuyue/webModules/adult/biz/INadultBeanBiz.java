package com.tuyue.webModules.adult.biz;

import com.tuyue.util.Page;
import com.tuyue.webModules.adult.bean.NadultBean;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/14.
 * @Modified By:
 */
public interface INadultBeanBiz {

    Page<NadultBean> list(Integer currentPage, Integer pageSize) throws Exception;
}
