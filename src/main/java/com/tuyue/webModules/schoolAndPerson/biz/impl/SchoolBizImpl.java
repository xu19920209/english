package com.tuyue.webModules.schoolAndPerson.biz.impl;

import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.sun.xml.internal.ws.encoding.MIMEPartStreamingDataHandler;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.ObjectCopyUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.schoolAndPerson.bean.InsertSchoolBean;
import com.tuyue.webModules.schoolAndPerson.bean.PersonZiYuanBean;
import com.tuyue.webModules.schoolAndPerson.bean.PersonnelBean;
import com.tuyue.webModules.schoolAndPerson.bean.RolerAndResouceBean;
import com.tuyue.webModules.schoolAndPerson.biz.IschoolBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/5.
 */
@Service
@Transactional
public class SchoolBizImpl implements IschoolBiz {
    private final static Logger logger = LoggerFactory.getLogger(SchoolBizImpl.class);
    @Autowired
    private IBaseDao<Ebranchschool> edao;
    @Autowired
    private IBaseDao<Fstaff> fdao;

    @Autowired
    private IBaseDao<Grole> gdao;

    @Autowired
    private IBaseDao<Hresource> hdao;

    @Autowired
    private IBaseDao<Ipeopleandrole> idao;

    @Autowired
    private IBaseDao<Jroleandresource> jdao;
    @Autowired
    private IBaseDao<Keduundergo> kdao;
    @Autowired
    private IBaseDao<Lworkresume> ldao;
    @Autowired
    private IBaseDao<Mfamilystatus> mdao;
    @Autowired
    private IBaseDao<QschoolCourse> qdao;

    /**
     * @Author: 徐慷慨
     * @Description: 添加校区
     * @Date: 9:44 2017/9/5
     */
    @Override
    public Result insertSchool(Ebranchschool ebranchschool, String username) throws Exception {

        int eid = edao.save(ebranchschool);
        if (eid>0) {
            Fstaff fstaff = new Fstaff();
            fstaff.setUsername(username);
            fstaff.setEid(eid);
            fstaff.setPassword("123456");
            fstaff.setName("admin");
            fstaff.setIsFlag(1);
            int fid = fdao.save(fstaff);
            if (fid>0){
                Ebranchschool ebranchschool1 = edao.getOne(Ebranchschool.class, eid);
                ebranchschool1.setFid(fid);
                boolean b = edao.update(ebranchschool1);
                if (b) {
                    Grole grole = new Grole();
                    grole.setEid(eid);
                    grole.setIsFlag(2);
                    grole.setRole("管理员");
                    grole.setComment("管理员");
                    int gid = gdao.save(grole);//添加角色
                    //添加角色资源
                    if (gid>0) {
                        Ipeopleandrole ipeopleandrole = new Ipeopleandrole();
                        ipeopleandrole.setFid(fid);
                        ipeopleandrole.setGid(gid);
                        int iid = idao.save(ipeopleandrole);
                        if (iid>0) {
                            List<Hresource> list = hdao.findList(" from Hresource where isFlag=2");
                            List<Jroleandresource> jroleandresourceList = new ArrayList<Jroleandresource>();
                            for (Hresource hresource : list) {
                                Jroleandresource jroleandresource =new Jroleandresource();
                                jroleandresource.setHid(hresource.getHid());
                                jroleandresource.setGid(gid);
                                jroleandresource.setJid(UUID.randomUUID().toString());
                                jroleandresourceList.add(jroleandresource);
                            }
                            int i = jdao.batchSave(jroleandresourceList);
                            if(i==jroleandresourceList.size()){
                                return ResultUtil.success("增加成功");
                            }
                            return ResultUtil.error(2,"增加失败");
                        }
                        return ResultUtil.error(2,"增加失败");
                    }
                    return ResultUtil.error(2,"增加失败");
                }
                return ResultUtil.error(2,"增加失败");
            }
            return ResultUtil.error(2,"增加失败");
        }

        return ResultUtil.error(2,"增加失败");

//        int save = edao.save(ebranchschool);//添加学校
//        if (save > 0) {
//            Fstaff fstaff = new Fstaff();
//            fstaff.setUsername(username);
//            fstaff.setIsFlag(2);
//            fstaff.setPassword("123");
//            fstaff.setEid(save);
//            int save1 = fdao.save(fstaff);//添加管理员
//            if (save1 > 0) {
//                Ebranchschool ebranchschool1 = edao.getOne(Ebranchschool.class, save);
//                ebranchschool.setFid(save1);
//                edao.update(ebranchschool1);//更新学校
//            }
//            Grole grole = new Grole();
//            grole.setEid(save);
//            grole.setIsFlag(2);
//            grole.setRole("管理员");
//            grole.setComment("管理员");
//            int save2 = gdao.save(grole);//添加角色
//
//            Ipeopleandrole ipeopleandrole = new Ipeopleandrole();
//            ipeopleandrole.setFid(save1);
//            ipeopleandrole.setGid(save2);
//            int save3 = idao.save(ipeopleandrole);
//            if (save > 0 && save1 > 0 && save2 > 0 && save3 > 0) {
//                return ResultUtil.success("添加校区成功", ebranchschool);
//            } else {
//                return ResultUtil.success("添加校区失败", ebranchschool);
//            }
//        }

    }
    /**
     * @Author: 徐慷慨
     * @Description: 修改校区
     * @Date: 10:16 2017/9/5
     */
    @Override
    public Result updateSchool(Ebranchschool ebranchschool) throws Exception {
        Ebranchschool one = edao.getOne(Ebranchschool.class, ebranchschool.getEid());
        if (one != null) {
            edao.getSessions().evict(one);
            boolean update = edao.update(ebranchschool);
            if (update) {
                return ResultUtil.success("修改校区成功");
            } else {
                return ResultUtil.error(2, "修改校区失败！");
            }
        } else {
            return ResultUtil.error(2, "参数错误！");
        }

    }

    /**
     * @Author: 徐慷慨
     * @Description:删除校区
     * @Date: 10:17 2017/9/5
     */
    @Override
    public Result deleteSchool(String eid) throws Exception {
        if (eid.endsWith(",")) {
            eid = eid.substring(0, eid.length() - 1);
        }
        Ebranchschool ebranchschool = edao.getOne(Ebranchschool.class, Integer.parseInt(eid));
        if (ebranchschool!=null) {
            boolean b = edao.delete(ebranchschool);
            if (b) {
                return ResultUtil.success("删除成功");
            }
            logger.error("系统删除失败");
            return ResultUtil.error(2, "删除失败！");
        }
        logger.error("ID查询不到删除失败");
        return ResultUtil.error(2, "删除失败！");
//        int i = edao.deleteWithHql(" delete from Ebranchschool where eid in (" + eid + ")");
//        if (i > 0) {
//            return ResultUtil.success("删除成功");
//        } else {
//            return ResultUtil.error(2, "删除失败！");
//        }

    }

    /**
     * @Author: 徐慷慨
     * @Description: 根据id查找学校信息
     * @Date: 10:31 2017/9/5
     */
    @Override
    public Result selectSchoolById(Integer eid) throws Exception {
        Ebranchschool one = edao.getOne(Ebranchschool.class, eid);
        InsertSchoolBean insertSchoolBean1 = ObjectCopyUtil.copy(one, Ebranchschool.class, InsertSchoolBean.class);
        Fstaff one1 = fdao.getOne(Fstaff.class, one.getFid());
        insertSchoolBean1.setUsername(one1.getUsername());
        if (one != null) {
            return ResultUtil.success("根据id查找学校信息", insertSchoolBean1);
        } else {
            return ResultUtil.error(2, "根据id查找学校信息失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:分学校列表
     * @Date: 18:30 2017/9/5
     */
    @Override
    public Result schoolList(String name, Integer currenPage, Integer pageSize) throws Exception {
        String hql = "";
        String count = "";
        if (name != null) {
            hql = "from Ebranchschool where schoolType=2 and name like '%" + name+"%'";
            count = "select count (*) from Ebranchschool where schoolType=2 and name like '%" + name+"%'";
        } else {
            hql = "from Ebranchschool where schoolType=2";
            count = "select count (*) from Ebranchschool where schoolType=2";
        }
        Page<Ebranchschool> page = edao.findPage(currenPage, pageSize, hql, count);
        List<InsertSchoolBean> list = new ArrayList<InsertSchoolBean>();
        for (Ebranchschool ebranchschool : page.getList()) {
            QschoolCourse one1 = qdao.findOne(" from QschoolCourse where eid="+ebranchschool.getEid());
            InsertSchoolBean insertSchoolBean1 = ObjectCopyUtil.copy(ebranchschool, Ebranchschool.class, InsertSchoolBean.class);
            if(one1!=null){
                insertSchoolBean1.setPath(one1.getPath());
            }else{
                insertSchoolBean1.setPath(" ");
            }

            Fstaff one = fdao.getOne(Fstaff.class, ebranchschool.getFid());
            if (one != null) {
                insertSchoolBean1.setUsername(one.getUsername());
            }
            list.add(insertSchoolBean1);
        }
        Map map = new HashMap();
        map.put("currentPage", page.getCurrentPage());
        map.put("allPage", page.getAllpage());
        map.put("total", page.getTotal());
        map.put("list", list);
        return ResultUtil.success("分学校列表", map);
    }


    /**
     * @Author: 徐慷慨
     * @Description:判断账号是否已被使用
     * @Date: 8:46 2017/9/7
     */

    @Override
    public boolean mateUsername(String username) throws Exception {
        Fstaff one = fdao.findOne("from Fstaff where username='" + username + "'");
        if (one != null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description: 给分校分配课程
     * @param path
     * @Date: 14:48 2017/9/9
     */
    @Override
    @Transactional
    public Result allotCourse(String path, Integer eid) throws Exception {
        List<QschoolCourse> list1 = qdao.findList("from QschoolCourse where eid=" + eid);
        if(list1.size()>0){
            int i = qdao.deleteWithHql("delete from QschoolCourse where eid=" + eid);
            if(i<=0){
                return ResultUtil.error(2,"给分校分配课程失败！");
            }
        }
        String[] split = path.split(",");
        List<String> listALL = new ArrayList<String>();//存所有的split
        List<String> list = new ArrayList<String>();//存所有的一级
        Set<String> set = new HashSet();
        List <QschoolCourse> courseList = new ArrayList<QschoolCourse>();
        for (String s : split) {
            String[] split1 = s.split(";");
            if(split1[1].equals("0")){
                list.add(s);
            }else{
                set.add(split1[1]);
            }
            listALL.add(s);
        }
        listALL.removeAll(list);//所有课时

        for(String aid : set){
            QschoolCourse qschoolCourse = new QschoolCourse();
            qschoolCourse.setQid(UUID.randomUUID().toString().replaceAll("-", ""));
            qschoolCourse.setAid(Integer.parseInt(aid));
            qschoolCourse.setEid(eid);
            qschoolCourse.setPath(path);
            String bid="";
            for(String str : listALL){
                String[] split1 = str.split(";");
                if(split1[1].equals(aid)){
                   bid+=split1[0]+",";
               }
            }
            qschoolCourse.setLevelId(bid);
            courseList.add(qschoolCourse);
        }
        int i = qdao.batchSave(courseList);
        if(i==courseList.size()){
            return ResultUtil.success("给分校分配课程成功！");
        }
        return ResultUtil.error(2,"给分校分配课程失败！");

    }

    //：：：：：：：：：：：：：：：：：：：员工模块：：：：：：：：：：：：：：：：：：：：：：：：：：：：：

    /**
     * @Author: 徐慷慨
     * @Description:添加员工信息
     * @Date: 11:44 2017/9/5
     */
    @Override
    public Result insertPerson(PersonnelBean personnelBean) throws Exception {
        Fstaff fstaff = ObjectCopyUtil.copy(personnelBean, PersonnelBean.class, Fstaff.class);
        fstaff.setPassword("123456");
        int i = fdao.save(fstaff);
        int a = 0;
        int b = 0;
        int c = 0;
        Ipeopleandrole ipeopleandrole = new Ipeopleandrole();
        ipeopleandrole.setGid(personnelBean.getGid());
        ipeopleandrole.setFid(i);
        idao.save(ipeopleandrole);
        if (personnelBean.getEduList().size() > 0) {
            for (Keduundergo keduundergo : personnelBean.getEduList()) {
                keduundergo.setFid(i);
                kdao.save(keduundergo);
                a++;
            }
        }
        if (personnelBean.getWorkList().size() > 0) {
            for (Lworkresume lworkundergo : personnelBean.getWorkList()) {
                lworkundergo.setFid(i);
                ldao.save(lworkundergo);
                b++;
            }
        }

        if (personnelBean.getFamilList().size() > 0) {
            for (Mfamilystatus mfamilystatus : personnelBean.getFamilList()) {
                mfamilystatus.setFid(i);
                mdao.save(mfamilystatus);
                c++;
            }
        }
        if (i > 0 && a == personnelBean.getEduList().size() && b == personnelBean.getWorkList().size() && c == personnelBean.getFamilList().size()) {
            return ResultUtil.success("添加员工信息成功！");
        } else {
            return ResultUtil.error(2, "添加员工信息失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:根据id查员工信息
     * @Date: 11:47 2017/9/5
     */
    @Override
    public Result selectPersionById(Integer fid) throws Exception {
        Fstaff one = fdao.getOne(Fstaff.class, fid);
        List<Keduundergo> list = kdao.findList(" FROM Keduundergo WHERE fid=" + fid);
        List<Lworkresume> list1 = ldao.findList("from Lworkresume where fid=" + fid);
        List<Mfamilystatus> list2 = mdao.findList("from Mfamilystatus where fid=" + fid);
        PersonnelBean personnelBean = ObjectCopyUtil.copy(one, Fstaff.class, PersonnelBean.class);
        personnelBean.setEduList(list);
        personnelBean.setWorkList(list1);
        personnelBean.setFamilList(list2);
        Ipeopleandrole one2 = idao.findOne(" from Ipeopleandrole where fid=" + fid);
        Grole gdaoOne = gdao.getOne(Grole.class, one2.getGid());
        if(gdaoOne!=null){
            personnelBean.setGid(gdaoOne.getGid());
        }
        if (gdaoOne != null) {
            personnelBean.setRole(gdaoOne.getRole());
        }
        return ResultUtil.success("查找成功", personnelBean);

    }

    /**
     * @Author: 徐慷慨
     * @Description:删除员工
     * @Date: 10:17 2017/9/5
     */
    public Result deletePersion(String fid) {
        if (fid.endsWith(",")) {
            fid = fid.substring(0, fid.length() - 1);
        }
        int i = fdao.deleteWithHql(" delete from Fstaff where fid in (" + fid + ")");
        if (i > 0) {
            return ResultUtil.success("删除成功");
        } else {
            return ResultUtil.error(2, "删除失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改员工信息
     * @Date: 13:36 2017/9/5
     */
    public Result updatePersion(PersonnelBean personnelBean) throws Exception {
        Fstaff one = fdao.getOne(Fstaff.class, personnelBean.getFid());
        if (one != null) {
            Fstaff copy = ObjectCopyUtil.copy(personnelBean, PersonnelBean.class, Fstaff.class);
            fdao.getSessions().evict(one);
            boolean update = fdao.update(copy);
            //删除四张附表的内容
            kdao.deleteWithHql("delete from Keduundergo where fid=" + personnelBean.getFid());
            ldao.deleteWithHql("delete from Lworkresume where fid=" + personnelBean.getFid());
            mdao.deleteWithHql("delete from Mfamilystatus where fid=" + personnelBean.getFid());
            idao.deleteWithHql("delete from Ipeopleandrole where fid=" + personnelBean.getFid());
            Ipeopleandrole ipeopleandrole = new Ipeopleandrole();
            ipeopleandrole.setGid(personnelBean.getGid());
            ipeopleandrole.setFid(personnelBean.getFid());
            idao.save(ipeopleandrole);
            int a = 0;
            int b = 0;
            int c = 0;
            if (personnelBean.getEduList().size() > 0) {
                for (Keduundergo keduundergo : personnelBean.getEduList()) {
                    keduundergo.setFid(personnelBean.getFid());
                    kdao.save(keduundergo);
                    a++;
                }
            }
            if (personnelBean.getWorkList().size() > 0) {
                for (Lworkresume lworkundergo : personnelBean.getWorkList()) {
                    lworkundergo.setFid(personnelBean.getFid());
                    ldao.save(lworkundergo);
                    b++;
                }
            }
            if (personnelBean.getFamilList().size() > 0) {
                for (Mfamilystatus mfamilystatus : personnelBean.getFamilList()) {
                    mfamilystatus.setFid(personnelBean.getFid());
                    mdao.save(mfamilystatus);
                    c++;
                }
            }
            if (update && a == personnelBean.getEduList().size() && b == personnelBean.getWorkList().size() && c == personnelBean.getFamilList().size()) {
                return ResultUtil.success("修改员工信息成功");
            } else {
                return ResultUtil.error(2, "修改员工信息失败！");
            }
        } else {
            return ResultUtil.error(2, "参数错误！");
        }
    }


    /**
     * @Author: 徐慷慨
     * @Description:给员工添加角色
     * @Date: 14:30 2017/9/5
     */
    @Override
    public Result insertPerRole(Integer fid, String gid) throws Exception {
        int i = 0;
        for (String id : gid.split(",")) {
            Ipeopleandrole ipeopleandrole = new Ipeopleandrole();
            ipeopleandrole.setGid(Integer.parseInt(id));
            ipeopleandrole.setFid(fid);
            int save = idao.save(ipeopleandrole);
        }

        if (i == gid.split(",").length) {
            return ResultUtil.success("给员工添加角色成功！");
        } else {
            return ResultUtil.error(2, "给员工添加角色失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description: 查询员工下的资源
     * @Date: 15:18 2017/9/5
     */
    @Override
    public List<PersonZiYuanBean> perResourceList(String gid) throws Exception {
        List<PersonZiYuanBean> list = new ArrayList<PersonZiYuanBean>();
        List<Jroleandresource> list1 = jdao.findList(" from Jroleandresource where gid=(" + gid + ")");
        if (list1!=null&&list1.size() <= 0) {
            return  list;
        } else {
            Set<String> set = new HashSet();
            String hid = "";
            for (Jroleandresource jroleandresource : list1) {
                set.add(jroleandresource.getHid().toString());
            }
            for (String id : set) {
                hid += id + ",";
            }
            hid = hid.substring(0, hid.length() - 1);
            List<Hresource> list2 = hdao.findList("from Hresource where hid in (" + hid + ") ORDER BY parentId,sort");
            for (Hresource hresource : list2) {
                PersonZiYuanBean personZiYuanBean = new PersonZiYuanBean();
                if (hresource.getParentId() == 0) {
                    personZiYuanBean.setId(hresource.getHid());
                    personZiYuanBean.setName(hresource.getResource());
                    personZiYuanBean.setPath(hresource.getAddress() != null ? hresource.getAddress() : "");
                    personZiYuanBean.setIcon(hresource.getIcon()!=null ? hresource.getIcon() :"");
                    List<PersonZiYuanBean.Person> list3 = new ArrayList<PersonZiYuanBean.Person>();
                    for (Hresource hresource1 : list2) {
                        if (hresource1.getParentId() == hresource.getHid()) {
                            PersonZiYuanBean.Person person = new PersonZiYuanBean.Person();
                            person.setId(hresource1.getHid());
                            person.setName(hresource1.getResource());
                            person.setPath(hresource1.getAddress() != null ? hresource1.getAddress() : "");
                            list3.add(person);
                        }
                    }
                    personZiYuanBean.setChildren(list3);
                    list.add(personZiYuanBean);
                }

            }
        }
        return list;
    }


    /**
     * @param eid
     * @Author: 徐慷慨
     * @Description:查询某个学校下所有员工
     * @Date: 18:34 2017/9/5
     */
    @Override
    public Result perBySchoolList(Integer eid, Integer gid, String name, Integer currentPage, Integer pageSize) throws Exception {
        Ebranchschool one1 = edao.getOne(Ebranchschool.class, eid);
        StringBuffer hql = new StringBuffer("from Fstaff where eid="+eid+" and fid !="+one1.getFid());
        StringBuffer count = new StringBuffer("select count (*) from Fstaff where eid=" + eid+" and fid !="+one1.getFid());
        if (name != null) {
            hql.append(" and name like '%" + name + "%'");
            count.append(" and name like '%" + name + "%'");
        }
        if (gid != null) {
            List<Ipeopleandrole> list = idao.findList(" from Ipeopleandrole where gid=" + gid);
            String fid = "";
            if (list.size() > 0) {
                for (Ipeopleandrole ipeopleandrole : list) {
                    fid += ipeopleandrole.getFid()+",";
                }
                fid=fid.substring(0,fid.length()-1);
            } else {
                fid = "-1,-2";
            }
            hql.append(" and fid in(" + fid + ")");
            count.append(" and fid in(" + fid + ")");
        }
        Page<Fstaff> page = fdao.findPage(currentPage, pageSize, hql.toString(), count.toString());
        List<PersonnelBean> list = new ArrayList<PersonnelBean>();
        for (Fstaff fstaff : page.getList()) {
            Ipeopleandrole one = idao.findOne(" from Ipeopleandrole where fid=" + fstaff.getFid());
            Grole gdaoOne = gdao.getOne(Grole.class, one.getGid());
            PersonnelBean copy = ObjectCopyUtil.copy(fstaff, Fstaff.class, PersonnelBean.class);
            copy.setGid(one.getGid());
            if (gdaoOne != null) {
                copy.setRole(gdaoOne.getRole());
            }

            list.add(copy);
        }
        Map map = new HashMap();
        map.put("list", list);
        map.put("total", page.getTotal());
        map.put("allPage", page.getAllpage());
        map.put("currentPage", page.getCurrentPage());
        return ResultUtil.success("查询某个学校下所有员工", map);
    }
    //*********************************************************************************************************************

    /**
     * @Author: 徐慷慨
     * @Description:添加角色
     * @Date: 14:01 2017/9/5
     */
    @Override
    public Result insertRole(Grole grole) throws Exception {
        if(grole.getEid()==null){
            return ResultUtil.error(2,"EID不能为空！");
        }
        Grole one = gdao.findOne("from Grole where role='" + grole.getRole() + "' and eid=" + grole.getEid());
        if (one != null) {
            return ResultUtil.error(2, "该角色已被创建！");
        } else {
            int i = gdao.save(grole);
            if (i > 0) {
                return ResultUtil.success("添加角色成功！", grole);
            } else {
                return ResultUtil.error(2, "添加角色失败！");
            }
        }
    }


    /**
     * @Author: 徐慷慨
     * @Description:修改角色
     * @Date: 14:04 2017/9/5
     */
    @Override
    public Result updateRole(Grole grole) throws Exception {
        Grole one = gdao.getOne(Grole.class, grole.getGid());
        if (one != null) {
            gdao.getSessions().evict(one);
            boolean update = gdao.update(grole);
            if (update) {
                return ResultUtil.success("修改角色成功");
            } else {
                return ResultUtil.error(2, "修改角色失败！");
            }
        } else {
            return ResultUtil.error(2, "修改角色失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:删除角色
     * @Date: 14:05 2017/9/5
     */
    @Override
    public Result deleteRole(String gid) throws Exception {
        if (gid.endsWith(",")) {
            gid = gid.substring(0, gid.length() - 1);
        }
        List<Ipeopleandrole> list = idao.findList(" from Ipeopleandrole where gid in (" + gid + ")");
        if(list.size()>0){
            return ResultUtil.error(2,"该角色下有资源，不能删除！");
        }else{
            int i = gdao.deleteWithHql(" delete from Grole where gid in (" + gid + ")");
            if (i > 0) {
                return ResultUtil.success("删除成功");
            } else {
                return ResultUtil.error(2, "删除失败！");
            }
        }

    }

    /**
     * @Author: 徐慷慨
     * @Description:角色列表
     * @Date: 14:06 2017/9/5
     */
    public Result rolerList(Integer eid, Integer currentPage, Integer pageSize, String role) throws Exception {
        List<Hresource> list2 = hdao.findList(" from Hresource where rank=2");
        StringBuffer hql = new StringBuffer("from Grole where eid=" + eid);
        StringBuffer count = new StringBuffer("select count(*) from Grole where eid=" + eid);
        if (role != null) {
            hql.append(" and role like '%" + role + "%'");
            count.append(" and role like '%" + role + "%'");
        }
        Page<Grole> page = gdao.findPage(currentPage, pageSize, hql.toString(), count.toString());
        List<RolerAndResouceBean> list = new ArrayList<RolerAndResouceBean>();
        for(Grole grole : page.getList()) {
            Set<String> set = new HashSet();
            List<Jroleandresource> list1 = jdao.findList(" from Jroleandresource where gid=(" + grole.getGid() + ")");
            String hid = "";
            if (list1.size() > 0) {
                for (Jroleandresource jroleandresource : list1) {
                    for (Hresource hresource : list2) {
                        if(jroleandresource.getHid()==hresource.getHid()){
                            set.add(jroleandresource.getHid().toString());
                        }
                    }
                }
                for (String id : set) {
                    hid += id + ",";
                }
                if(hid.length()>0){
                    hid = hid.substring(0, hid.length() - 1);
                }
            }
            RolerAndResouceBean copy = ObjectCopyUtil.copy(grole, Grole.class, RolerAndResouceBean.class);
            copy.setHid(hid);
            list.add(copy);
        }
        Map map = new HashMap();
        map.put("currentPage",page.getCurrentPage());
        map.put("total",page.getTotal());
        map.put("allPage",page.getAllpage());
        map.put("pageSize",page.getPageSize());
        map.put("list",list);
        return ResultUtil.success("角色列表", map);
    }

    /**
     * @Author: 徐慷慨
     * @Description:给角色添加资源
     * @Date: 14:42 2017/9/5
     */
    public Result inRoleResour(Integer gid, String hid) throws Exception {
       // jdao.deleteWithHql(" delte from  Jroleandresource where gid in("+gid+")");
        List<Jroleandresource> list = jdao.findList("from  Jroleandresource where gid =" + gid);
        if(list.size()>0){
            jdao.deleteAll(list);
        }
        Set<String> set = new HashSet();
        String[] split = hid.split(",");
        for(String id : split){
            if(!id.equals("0")){
                set.add(id);
            }
        }
        int i = 0;
        for (String id : set) {
            Jroleandresource jroleandresource = new Jroleandresource();
            jroleandresource.setGid(gid);
            jroleandresource.setHid(Integer.parseInt(id));
            jroleandresource.setJid(UUID.randomUUID().toString().replaceAll("-", ""));
            int save = jdao.saveUUid(jroleandresource);
            i++;
        }
        if (i ==set.size()) {
            return ResultUtil.success("给角色添加资源成功");
        } else {
            return ResultUtil.error(2, "给角色添加资源失败！");
        }

    }

    /**
     * @Author: 徐慷慨
     * @Description:修改角色资源
     * @Date: 14:43 2017/9/5
     */
    @Override
    public Result upRoleResour(Jroleandresource jroleandresource) throws Exception {
        Jroleandresource one = jdao.getOne(Jroleandresource.class, jroleandresource.getGid());
        if (one != null) {
            jdao.getSessions().evict(one);
            boolean update = jdao.update(jroleandresource);
            if (update) {
                return ResultUtil.success("修改角色资源成功");
            } else {
                return ResultUtil.error(2, "修改角色资源失败！");
            }
        } else {
            return ResultUtil.error(2, "修改角色资源失败");
        }

    }


    //::::::::::::::::::::::::::::::::::::::::::::::::资源管理::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


    /**
     * @Author: 徐慷慨
     * @Description:添加资源管理
     * @Date: 14:01 2017/9/5
     */
    public Result insertResource(Hresource hresource) {
        int i = hdao.save(hresource);
        if (i > 0) {
            return ResultUtil.success("添加资源管理成功！", hresource);
        } else {
            return ResultUtil.error(2, "添加资源管理失败！");
        }
    }


    /**
     * @Author: 徐慷慨
     * @Description:修改资源管理
     * @Date: 14:04 2017/9/5
     */
    @Override
    public Result updateResource(Hresource hresource) throws Exception {
        Hresource one = hdao.getOne(Hresource.class, hresource.getHid());
        if (one != null) {
            hdao.getSessions().evict(one);
            boolean update = hdao.update(hresource);
            if (update) {
                return ResultUtil.success("修改资源管理成功");
            } else {
                return ResultUtil.error(2, "修改资源管理失败！");
            }
        } else {
            return ResultUtil.error(2, "修改资源管理失败(参数错误)！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:删除资源管理
     * @Date: 14:05 2017/9/5
     */
    @Override
    public Result deleteResource(String hid) {
        if (hid.endsWith(",")) {
            hid = hid.substring(0, hid.length() - 1);
        } else {
            hid = hid;
        }
        int i = hdao.deleteWithHql(" delete from Hresource where hid in (" + hid + ")");
        if (i > 0) {
            return ResultUtil.success("删除成功");
        } else {
            return ResultUtil.error(2, "删除失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:资源管理列表
     * @Date: 14:06 2017/9/5
     */
    @Override
    public Result resourceList(Integer eid) throws Exception {
        Ebranchschool one = edao.getOne(Ebranchschool.class, eid);
        List<PersonZiYuanBean> list = new ArrayList<PersonZiYuanBean>();
            List<Hresource> list2 = hdao.findList("from Hresource WHERE isFlag="+one.getSchoolType()+"ORDER BY parentId,sort");
            for (Hresource hresource : list2) {
                PersonZiYuanBean personZiYuanBean = new PersonZiYuanBean();
                if (hresource.getParentId() == 0) {
                    personZiYuanBean.setId(hresource.getHid());
                    personZiYuanBean.setName(hresource.getResource());
                    personZiYuanBean.setPath(hresource.getAddress() != null ? hresource.getAddress() : "");
                    personZiYuanBean.setParentId(hresource.getParentId());
                    List<PersonZiYuanBean.Person> list3 = new ArrayList<PersonZiYuanBean.Person>();
                    for (Hresource hresource1 : list2) {
                        if (hresource1.getParentId() == hresource.getHid()) {
                            PersonZiYuanBean.Person person = new PersonZiYuanBean.Person();
                            person.setId(hresource1.getHid());
                            person.setName(hresource1.getResource());
                            person.setPath(hresource1.getAddress() != null ? hresource1.getAddress() : "");
                            person.setParentId(hresource1.getParentId());
                            list3.add(person);
                        }
                    }
                    personZiYuanBean.setChildren(list3);
                    list.add(personZiYuanBean);
                }
            }
        Map map =new HashMap();
        map.put("list",list);
        return ResultUtil.success("资源管理列表", map);
    }
}
