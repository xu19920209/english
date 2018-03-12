package com.tuyue.webModules.course.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Ctopic;
import com.tuyue.pojo.Dictionaries;
import com.tuyue.util.Page;
import com.tuyue.util.Setting;
import com.tuyue.util.Tools;
import com.tuyue.webModules.course.biz.IDictionariesBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/4.
 */
@Service
@Transactional
public class DictionariesBizImpl implements IDictionariesBiz {
    @Autowired
    private IBaseDao<Dictionaries> dictionariesIBaseDao;
    @Autowired
    private IBaseDao<Ctopic> ctopicIBaseDao;
    private String voice;

    @Override
    public Dictionaries findbyid(Integer did) throws Exception {
        Dictionaries dictionaries = dictionariesIBaseDao.getOne(Dictionaries.class, did);
        return dictionaries;
    }



    @Override
    public boolean updata(Integer did,String dword) throws Exception {
        Dictionaries dictionaries = dictionariesIBaseDao.getOne(Dictionaries.class, did);
        dictionaries.setDword(dword);
        boolean flag = dictionariesIBaseDao.update(dictionaries);
        return flag;
    }

    @Override
    public boolean del(int did) throws Exception {
        Dictionaries one = dictionariesIBaseDao.getOne(Dictionaries.class, did);
        boolean b = dictionariesIBaseDao.delete(one);
        return b;
    }

    @Override
    public int create() throws Exception {
        int a=0;
        List<Ctopic> list1 = ctopicIBaseDao.findList("from Ctopic  ORDER BY bid,cid ");
        if (list1==null) {
            return 0;
        }
        for (Ctopic ctopic : list1) {
            List<String> set = Tools.zhuhuan(ctopic.getCsentence());
            List<Dictionaries> list = dictionariesIBaseDao.findList("select dword from Dictionaries");
            set.removeAll(list);
            int i=0;
            for(String word : set){
                Dictionaries dictionaries = new Dictionaries();
                dictionaries.setDword(word);
                dictionaries.setDflag(1);
                dictionariesIBaseDao.save(dictionaries);
                i++;
            }
            a+=i;
        }
        return a;
    }

    @Override
    public Page<Dictionaries> list(String dword,Integer currentPage,Integer pageSize) throws Exception {

        String hql="from Dictionaries  ";
        StringBuffer sb = new StringBuffer(hql);
        if (dword!=null) {
            sb.append("WHERE dword like '%"+dword+"%'");
        }
        //sb.append(" ORDER BY dword");
        String chql="select count (*) from Dictionaries ";
        StringBuffer csb = new StringBuffer(chql);
        if (dword!=null) {
            csb.append("WHERE dword like '%"+dword+"%'");
        }
        //csb.append(" ORDER BY dword");
        Page<Dictionaries> dictionariesPage = dictionariesIBaseDao.findPage(currentPage, pageSize, sb.toString(), csb.toString());
        return dictionariesPage;
    }

    @Override
    public boolean uploud(MultipartFile file, Integer did) throws Exception {
        String path = Setting.UPMP3PATH;// 文件路径
        long l = System.currentTimeMillis();
        String name = String.valueOf(l);// 文件名称
        name += ".mp3";

        File f = new File(path, name);
        if (!f.exists()) {
            f.mkdirs();
        }
        file.transferTo(f);
        String voice = "/wordVoice/" + name;
        Dictionaries dictionaries = dictionariesIBaseDao.getOne(Dictionaries.class, did);
        dictionaries.setDadd( voice);
        dictionaries.setDflag(2);
        boolean b = dictionariesIBaseDao.update(dictionaries);
        return b;
    }
}
