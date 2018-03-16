package com.tuyue.appModules.download.biz;

import com.tuyue.appModules.download.bean.DictionariesBean;
import com.tuyue.pojo.Dictionaries;
import com.tuyue.result.Result;

import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/14.
 * @Modified By:
 */
public interface IDownloadBiz {
    DictionariesBean getDownload() throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:错误单词录音
     * @Date: 8:53 2018/1/20
     */
    public Result words(String word, String realyWord) throws Exception;
}
