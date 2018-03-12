package com.tuyue.webModules.course.controller;

import com.tuyue.pojo.Dictionaries;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import com.tuyue.util.Setting;
import com.tuyue.util.Tools;
import com.tuyue.webModules.course.biz.IDictionariesBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


/**
 * Created by Administrator on 2017/8/15.
 */
@Controller
@RequestMapping("Dictionaries")
public class DictionariesController {
    private final static Logger logger = LoggerFactory.getLogger(DictionariesController.class);
    @Autowired
    private IDictionariesBiz dictionariesBiz;

    /**
     * @Author: 王金海
     * @Description:数据字典列表
      * @param
     * @Date: 18:21 2017/9/5
     */
    @RequestMapping("list")
    @ResponseBody
    public Result list(String dword,Integer currentPage, Integer pageSize) throws Exception {
        logger.info("数据字典录音");

        Page<Dictionaries> list = dictionariesBiz.list(dword,currentPage,pageSize);
        return ResultUtil.success(list);
    }

    /**
     * @param did
     * @param file
     * @Author: 王金海
     * @Description:数据字典录音
     * @Date: 15:58 2017/9/5
     */
    @RequestMapping("updata")
    @ResponseBody
    public Result insertWord(@RequestParam(value = "file", required = false) MultipartFile file, Integer did) throws Exception {
        logger.info("数据字典录音");
        if (did == null) {
            return ResultUtil.error(2, "id不能为空");
        }
        if (file != null) {
            boolean b = dictionariesBiz.uploud(file, did);
            if (b) {
                //Tools.writeFile(Setting.PATH,String.valueOf(new Date().getTime()));
                return ResultUtil.success("上传录音成功");
            } else {
                return ResultUtil.error(2, "保存失败");
            }
        } else {
            return ResultUtil.error(3, "请输入录音！");
        }
    }

    /**
     * @param dword 单词
     * @Author: 王金海
     * @Description:修改字典单词
     * @Date: 17:23 2017/9/5
     */
    @RequestMapping("updtaWord")
    @ResponseBody
    public Result updtaWord(Integer did, String dword) throws Exception {
        if (did == null) {
            return ResultUtil.error(2, "id不能为空");
        }
        if (dword != null) {
            boolean b = dictionariesBiz.updata(did, dword);
            if (b) {
                //Tools.writeFile(Setting.PATH,String.valueOf(new Date().getTime()));
                return ResultUtil.success("修改成功！");
            } else {
                return ResultUtil.error(2, "保存失败");
            }
        } else {
            return ResultUtil.error(2, "单词为空");
        }
    }

    /**
     * @Author: 王金海
     * @Description:删除单词
      * @param did 单词ID
     * @Date: 17:56 2017/9/5
     */
    @RequestMapping("delWord")
    @ResponseBody
    public Result delWord(Integer did) throws Exception {
        if (did == null) {
            return ResultUtil.error(2, "id不能为空");
        }
        boolean b = dictionariesBiz.del(did);
        if (b) {
            Tools.writeFile(Setting.PATH,String.valueOf(new Date().getTime()));
            return ResultUtil.success("删除失败！");
        } else {
            return ResultUtil.error(2, "保存失败");
        }
    }
    @RequestMapping("create")
    @ResponseBody
    public Result create() throws Exception {
        int i = dictionariesBiz.create();
        return ResultUtil.success("成功增加"+i+"条",1);
    }
}
