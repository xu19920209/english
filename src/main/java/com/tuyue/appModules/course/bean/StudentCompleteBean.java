package com.tuyue.appModules.course.bean;

import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/15.
 * @Modified By:
 */
public class StudentCompleteBean {
    private String aname;//课程名称
    private String bname;//课时名称
    private double sumworkScore;// 总分
    private int sumctopic;// 总题数
    private int finishsum;// 完成题数
    private List<CompleteBean> completeBeans;
}
