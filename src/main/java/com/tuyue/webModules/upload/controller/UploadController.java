package com.tuyue.webModules.upload.controller;

import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Setting;
import com.tuyue.util.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/5.
 */
@Controller
@ResponseBody
@RequestMapping("upload")
public class UploadController {
    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("fileUpload.do")
    public Result fileUpload(@RequestParam(value = "file", required = true) MultipartFile[] file) {
        logger.error("上传文件");
        if (file == null) {
            return ResultUtil.error(2, "文件不能为空！");
        }
        StringBuffer fileName=new StringBuffer();
        if (file.length > 0) {
            for (int i = 0; i < file.length; i++) {
                MultipartFile fil = file[i];
                String path = Setting.UPMP3PATH+"/";// 文件路径
                long l = System.currentTimeMillis();
                String name = String.valueOf(l);// 文件名称
                String filename = fil.getOriginalFilename();
                String[] split = filename.split("\\.");
                name += Tools.getRandomNum() + "." + split[split.length - 1];
                try {
                    File f = new File(path, name);
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                    fil.transferTo(f);
                    fileName.append("wordVoice/" + name+",");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Map map = new HashMap();
        map.put("path", fileName.subSequence(0,fileName.length()-1));
        return ResultUtil.success("添加成功", map);
    }
}