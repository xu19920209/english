package com.tuyue.appModules.personalCenter.controller;

import com.tuyue.appModules.personalCenter.biz.IpersonalCenterBiz;
import com.tuyue.aspect.HttpAspect;
import com.tuyue.pojo.Nstudent;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
@Controller
@ResponseBody
@RequestMapping("app/person")
public class PersonalCenterController {
    private final static Logger logger = LoggerFactory.getLogger(PersonalCenterController.class);
@Autowired
private IpersonalCenterBiz biz;
    /**
     * @Author: 徐慷慨
     * @Description:注册用户
     * @Date: 9:37 2017/9/14
     */
    @RequestMapping("register.do")
    public Result register(Nstudent nstudent) throws Exception{
        logger.error("注册用户");
        if(nstudent.getPassword()==null||nstudent.getPassword()==null){
            return ResultUtil.error(2,"账号密码不能为空！");
        }
        return biz.register(nstudent);
    }
    /**
     * @Author: 徐慷慨
     * @Description:登录
     * @Date: 9:38 2017/9/14
     */
    @RequestMapping("login.do")
    public Result login(Nstudent nstudent) throws Exception{
        logger.error("登录");
        if(nstudent.getUsername()==null||nstudent.getPassword()==null){
            return ResultUtil.error(2,"账号密码不能为空！");
        }
        return biz.login(nstudent);
    }

    /**
     * @Author: 徐慷慨
     * @Description:忘记密码
     * @Date: 9:39 2017/9/14
     */
    @RequestMapping("forgetPwd.do")
    public Result forgetPwd(Nstudent nstudent) throws Exception{
        logger.error("忘记密码");
        if(nstudent.getPassword()==null||nstudent.getPassword()==null){
            return ResultUtil.error(2,"手机号密码不能为空！");
        }
        return biz.forgetPwd(nstudent);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 修改用户
     * @Date: 10:01 2017/9/14
     */
    @RequestMapping("updataUser.do")
    public Result updataUser(Nstudent nstudent, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception{
        logger.error("修改用户");
        System.out.println(":::::::::::::::::::::"+file);
        if(nstudent.getNid()<=0){
            return ResultUtil.error(2,"参数错误！");
        }else{
            Result byId = biz.findById(nstudent.getNid());
            Nstudent nstudent1 =(Nstudent) byId.getData();
            if (file!=null&&!file.isEmpty()) {
                String path = Setting.UPMP3PATH+"/";// 文件路径
                String name = System.currentTimeMillis() + file.getOriginalFilename();// 文件名称
                try {
                    File f = new File(path, name);
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                    file.transferTo(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                nstudent1.setImgUrl("/wordVoice/" + name);
            }
            if(nstudent.getPassword()!=null){
                nstudent1.setPassword(nstudent.getPassword());
            }
            if(nstudent.getSex()!=null){
                nstudent1.setSex(nstudent.getSex());
            }
            if(nstudent.getStudentName()!=null){
                nstudent1.setStudentName(nstudent.getStudentName());
            }
            return biz.updataUser(nstudent1);
        }

    }

    /**
     * @Author: 徐慷慨
     * @Description:根据id查用户
     * @Date: 10:06 2017/9/14
     */
    @RequestMapping("findById.do")
    public Result findById(Integer nid) throws Exception{
        logger.error("根据id查用户");
        if(nid==null){
            return ResultUtil.error(2,"参数为空！");
        }
        return biz.findById(nid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:签到
     * @Date: 20:10 2017/9/14
     */
    @RequestMapping("sign.do")
    public Result sign(Integer nid) throws Exception{
        return biz.sign(nid);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 用户成绩列表
     * @Date: 13:55 2017/11/9
     */
    @RequestMapping("gradeList.do")
    public Result gradeList(Integer nid) throws Exception{
        return biz.gradeList(nid);
    }

}
