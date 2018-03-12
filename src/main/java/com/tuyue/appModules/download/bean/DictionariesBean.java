package com.tuyue.appModules.download.bean;

import com.tuyue.pojo.Dictionaries;

import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/14.
 * @Modified By:
 */
public class DictionariesBean {
    private String versions;
    private List<Dictionaries> dictionaries;

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public List<Dictionaries> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List<Dictionaries> dictionaries) {
        this.dictionaries = dictionaries;
    }
}
