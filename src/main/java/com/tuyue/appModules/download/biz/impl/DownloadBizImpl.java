package com.tuyue.appModules.download.biz.impl;

import com.tuyue.appModules.download.bean.DictionariesBean;
import com.tuyue.appModules.download.biz.IDownloadBiz;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Dictionaries;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Setting;
import com.tuyue.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/14.
 * @Modified By:
 */
@Service
@Transactional
public class DownloadBizImpl implements IDownloadBiz {
    @Autowired
    private IBaseDao<Dictionaries> dictionariesIBaseDao;
    @Override
    public DictionariesBean getDownload() throws Exception {
        List<Dictionaries> dictionaries = dictionariesIBaseDao.findList("from Dictionaries where dflag=2");
        DictionariesBean dictionariesBean = new DictionariesBean();
        dictionariesBean.setDictionaries(dictionaries);
        String s = Tools.readTxtFile(Setting.PATH);
        dictionariesBean.setVersions(s);
        return dictionariesBean;
    }

    /**
     * @Author: 徐慷慨
     * @Description:错误单词录音
     * @Date: 8:53 2018/1/20
     */
    @Override
    public Result words(String word,String realyWord) throws Exception {
        List<Dictionaries> dictionaries = dictionariesIBaseDao.findList("from Dictionaries where dword in ("+word+")");
        System.out.println(dictionaries.size());
        List<Dictionaries> list=new ArrayList<Dictionaries>();
        String[] split = realyWord.split(",");
        for (String s : split) {
            System.out.println(s);
            for (Dictionaries dictionary : dictionaries) {
                if(dictionary.getDword().equals(s)){
                    System.out.println("::::::::"+dictionary.getDword());
                    list.add(dictionary);
                }
            }
        }
        for (Dictionaries dictionaries1 : list) {
            System.out.println("::::::::"+dictionaries1.getDword());
        }

        return ResultUtil.success("错误单词录音列表",list);
    }
}
