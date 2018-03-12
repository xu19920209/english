package com.tuyue.webModules.courseGoods.controller;

import com.tuyue.pojo.Agent;
import com.tuyue.pojo.UserWallet;
import com.tuyue.result.Result;
import com.tuyue.webModules.courseGoods.bean.AgentBean;
import com.tuyue.webModules.courseGoods.bean.Remit;
import com.tuyue.webModules.courseGoods.bean.WithdrawBean;
import com.tuyue.webModules.courseGoods.biz.TuiJianRen;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.poi.hssf.usermodel.*;
import sun.misc.Request;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/8.
 */
@Controller
@ResponseBody
@RequestMapping("web/tuiJian")
public class TuijianController {
    @Autowired
    private TuiJianRen tuiJianRen;
    /**
     * @Author: 徐慷慨
     * @Description:推荐人管理列表
     * @Date: 11:01 2017/12/8
     */
    @RequestMapping("tuiJianList")
    public Result tuiJianList(Integer type,Integer currentPage, Integer pageSize, String phone) throws Exception{

        if(currentPage==null||currentPage==0){
            currentPage=1;
        }
        if(pageSize==null||pageSize==0){
            pageSize=10;
        }
        return tuiJianRen.tuiJianList(type,currentPage,pageSize,phone);
    }
    /**
     * @Author: 徐慷慨
     * @Description:添加推荐人
     * @Date: 11:01 2017/12/8
     */
    @RequestMapping("tuiJianIn.do")
    public Result tuiJianIn(@RequestBody  Agent agent){

        return tuiJianRen.tuiJianIn(agent);
    }
    /**
     * @Author: 徐慷慨
     * @Description:修改代理人
     * @Date: 13:47 2017/12/8
     */
    @RequestMapping("tuiJianUp")
    public Result tuiJianUp(@RequestBody Agent agent) throws Exception{
        if(agent.getAgentId()==0){
            return tuiJianRen.tuiJianIn(agent);
        }
        return tuiJianRen.tuiJianUp(agent);
    }

    /**
     * @Author: 徐慷慨
     * @Description:导出excel
     * @Date: 13:51 2017/12/8
     */
    @RequestMapping("excel.do")
    public void excel(String ids,HttpServletResponse response) throws Exception{
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("账号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("收款人");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("金额");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("收款银行");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("收款银行支行");
        cell.setCellStyle(style);

        cell = row.createCell((short) 6);
        cell.setCellValue("状态");
        cell.setCellStyle(style);
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<WithdrawBean>list= tuiJianRen.excel(ids);
        System.out.println("excel"+list.size());
        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            WithdrawBean agentBean = list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(agentBean.getBankNumber());
            row.createCell((short) 1).setCellValue(agentBean.getUserName());
            row.createCell((short) 2).setCellValue(agentBean.getMoney());
            row.createCell((short) 3).setCellValue("暂无");
            row.createCell((short) 4).setCellValue(agentBean.getOpenBank());
            row.createCell((short) 5).setCellValue(agentBean.getBranchBank());
            System.out.println("><><"+agentBean.getMoneyStatus());
                    if(agentBean.getMoneyStatus()==1){
                        row.createCell((short) 6).setCellValue("已处理");
                    }else{
                        row.createCell((short) 6).setCellValue("未处理");
                    }
        }

        OutputStream out = null;
        try {
            out = response.getOutputStream();
            String fileName = "agentMoney.xls";// 文件名
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "UTF-8"));
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * @Author: 徐慷慨
     * @Description:销售业绩列表 1代理人 2 课程名称
     * @Date: 14:07 2017/12/8
     */
    @RequestMapping("sellList.do")
    public Result sellList(Integer currentPage,Integer pageSize,String name,Integer type,String time,String courseName) throws Exception{
        if(currentPage==null||currentPage==0){
            currentPage=1;
        }
        if(pageSize==null||pageSize==0){
            pageSize=10;
        }
        return tuiJianRen.sellList(currentPage,pageSize,name,type,time,courseName);
    }
    /**
     * @Author: 徐慷慨
     * @Description:打款
     * @Date: 8:58 2017/12/11
     */
    @RequestMapping("remit.do")
    public Result remit(@RequestBody Remit[] list){
        List<Remit> remits = Arrays.asList(list);
        return tuiJianRen.remit(remits);
    }

    /**
     * @Author: 徐慷慨
     * @Description:累计佣金明细/待打款佣金明细
     * @param type 1是待打款2已打款
     * @Date: 9:12 2017/12/11
     */
    @RequestMapping("payMoneyList.do")
    public Result payMoneyList(Integer currentPage, Integer pageSize, @RequestParam(required = true) Integer agentId, @RequestParam(required = true) Integer type) throws Exception{
        if(currentPage==null||currentPage==0){
            currentPage=1;
        }
        if(pageSize==null||pageSize==0){
            pageSize=10;
        }
        return tuiJianRen.payMoneyList(currentPage,pageSize,agentId,type);
    }
    /**
     * @Author: 徐慷慨
     * @Description:提现管理列表
     * @Date: 15:10 2018/2/27
     */
    @RequestMapping("withdrawList")
    public Result withdrawList(Integer type,String phone,Integer currentPage,Integer pageSize) throws Exception{
        if(currentPage==null||currentPage==0){
            currentPage=1;
        }
        if(pageSize==null||pageSize==0){
            pageSize=10;
        }
        return tuiJianRen.withdrawList(type,phone,currentPage,pageSize);
    }
    /**
     * @Author: 徐慷慨
     * @Description:处理打款
     * @Date: 15:26 2018/3/5
     */
    @RequestMapping("remit")
    public Result remit(int id) throws Exception {
        return  tuiJianRen.remit(id);
    }

}
