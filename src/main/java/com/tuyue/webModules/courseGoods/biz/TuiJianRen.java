package com.tuyue.webModules.courseGoods.biz;

import com.tuyue.pojo.Agent;
import com.tuyue.pojo.AgentAccount;
import com.tuyue.pojo.UserWallet;
import com.tuyue.result.Result;
import com.tuyue.webModules.courseGoods.bean.AgentBean;
import com.tuyue.webModules.courseGoods.bean.Remit;
import com.tuyue.webModules.courseGoods.bean.WithdrawBean;

import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/8.
 */
public interface TuiJianRen {
    /**
     * @Author: 徐慷慨
     * @Description:推荐人管理列表
     * @Date: 11:01 2017/12/8
     */
    public Result tuiJianList(Integer type,Integer currentPage,Integer pageSize,String phone) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:添加推荐人
     * @Date: 11:01 2017/12/8
     */
    public Result tuiJianIn(Agent agent);
    /**
     * @Author: 徐慷慨
     * @Description:修改代理人
     * @Date: 13:47 2017/12/8
     */
    public Result tuiJianUp(Agent agent) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:导出excel
     * @Date: 13:51 2017/12/8
     */
    public  List<WithdrawBean> excel(String ids) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:销售业绩列表 1代理人 2 课程名称
     * @Date: 14:07 2017/12/8
     */
    public Result sellList(Integer currentPage,Integer pageSize,String name,Integer type,String time,String courseName) throws Exception;
    
    /**
     * @Author: 徐慷慨
     * @Description:打款
     * @Date: 8:58 2017/12/11
     */
    public Result remit(List<Remit> list);

    /**
     * @Author: 徐慷慨
     * @Description:累计佣金明细/待打款佣金明细
     * @param type 1是待打款2已打款
     * @Date: 9:12 2017/12/11
     */
    public Result payMoneyList(Integer currentPage,Integer pageSize,Integer agentId,Integer type) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:给推荐人添加待打款金额
     * @Date: 11:24 2017/12/26
     */
    public boolean AgentCountIn(AgentAccount agentAccount);

    /**
     * @Author: 徐慷慨
     * @Description:提现管理列表
     * @Date: 17:10 2018/2/23
     */
    public Result withdrawList(Integer status,String phone,Integer currentPage,Integer pageSize) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:处理打款
     * @Date: 15:26 2018/3/5
     */
    public Result remit(int id) throws Exception;

    public UserWallet ceshi() throws Exception;
}
