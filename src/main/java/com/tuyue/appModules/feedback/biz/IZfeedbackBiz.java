package com.tuyue.appModules.feedback.biz;

import com.tuyue.pojo.Zfeedback;
import com.tuyue.util.Page;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/15.
 * @Modified By:
 */
public interface IZfeedbackBiz {
    /**
     * @Author: 王金海
     * @Description:增加意见反馈
     * @Date: 15:40 2017/9/15
     */
    boolean add(Zfeedback zfeedback);

    Page<Zfeedback> list(Integer currentPage, Integer pageSize) throws Exception;

    boolean del(Integer zid) throws Exception;
}
