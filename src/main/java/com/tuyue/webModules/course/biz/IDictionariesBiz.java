package com.tuyue.webModules.course.biz;

import com.tuyue.pojo.Dictionaries;
import com.tuyue.util.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Administrator on 2017/9/4.
 */
public interface IDictionariesBiz {
    public Dictionaries findbyid(Integer did) throws Exception;
    public boolean updata(Integer did,String dword) throws Exception;
    public boolean del(int did) throws Exception;
    /**
     * @Author: 王金海
     * @Description: 从课程库导入数据字典库
     * @Date: 13:37 2017/9/7
     */
    public int create() throws Exception;
    /**
     * @Author: 王金海
     * @Description: 数据字典列表
     * @Date: 15:32 2017/9/5
     */
    public Page<Dictionaries> list(String dword,Integer currentPage, Integer pageSize) throws Exception;


    /**
     * @Author: 王金海
     * @Description: 数据字典录音
      * @param did
     * @Date: 15:54 2017/9/5
     */
    public boolean uploud(MultipartFile file, Integer did) throws IOException, Exception;
}
