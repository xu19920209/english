package com.tuyue.appModules.course.biz;

import com.tuyue.appModules.course.bean.AdultCompleteBean;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/15.
 * @Modified By:
 */
public interface IAdultCompleteBiz {
    /**
     * @Author: 王金海
     * @Description: 成人成绩单
      * @param aid
      * @param bid
      * @param cids
     * @Date: 12:03 2017/9/15
     */
    AdultCompleteBean list(Integer aid,Integer bid, String cids,Integer nid) throws Exception;
    /**
     * @Author: 王金海
     * @Description: 学生成绩单
     * @param aid
     * @param bid
     * @param cids
     * @Date: 12:03 2017/9/15
     */
    AdultCompleteBean Studentlist(Integer aid,Integer bid, String cids,String layoutTime,Integer nid) throws Exception;
}
