package com.tuyue.appModules.download.controller;

import com.alibaba.fastjson.annotation.JSONField;
import com.tuyue.appModules.download.bean.DictionariesBean;
import com.tuyue.appModules.download.biz.IDownloadBiz;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/14.
 * @Modified By:
 */
@Controller
@ResponseBody
@RequestMapping("Download")
public class DownloadController {
    @Autowired
    private IDownloadBiz downloadBiz;
    /**
     * @Author: 王金海
     * @Description:下载单词录音
     * @Date: 17:43 2017/9/14
     */
    @RequestMapping("download")
    public Result download() throws Exception {
        DictionariesBean download = downloadBiz.getDownload();
        return ResultUtil.success(download);
    }

    /**
     * @Author: 徐慷慨
     * @Description:错误单词录音
     * @Date: 8:53 2018/1/20
     */
    @RequestMapping("words")
    @JSONField(serialize = false)
    public Result words(@RequestParam(required = true) String words) throws Exception{
        if(words==null&&words.length()<=0){
            return ResultUtil.error(2,"单词不能为空");
        }

        String[] split = words.split(",");
        String  word="";
        for (String s : split) {
            word+="'"+s+"',";
        }
        if(word.endsWith(",")){
            word= word.substring(0,word.length()-1);
        }
        return downloadBiz.words(word,words);
    }
}
