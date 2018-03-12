package com.tuyue.webModules.schoolAndPerson.biz;

import com.tuyue.pojo.Ebranchschool;
import com.tuyue.pojo.Grole;
import com.tuyue.pojo.Hresource;
import com.tuyue.pojo.Jroleandresource;
import com.tuyue.result.Result;
import com.tuyue.webModules.schoolAndPerson.bean.PersonZiYuanBean;
import com.tuyue.webModules.schoolAndPerson.bean.PersonnelBean;

import java.util.List;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/5.
 */
public interface IschoolBiz {
    /**
     * @Author: 徐慷慨
     * @Description: 添加校区
     * @Date: 9:44 2017/9/5
     */
    public Result insertSchool(Ebranchschool ebranchschool,String username) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description: 修改校区
     * @Date: 10:16 2017/9/5
     */
    public Result updateSchool(Ebranchschool ebranchschool) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:删除校区
     * @Date: 10:17 2017/9/5
     */
    public Result deleteSchool(String eid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description: 根据id查找学校信息
     * @Date: 10:31 2017/9/5
     */
    public Result selectSchoolById(Integer eid) throws Exception;
   /**
    * @Author: 徐慷慨
    * @Description:分学校列表(带搜索)
    * @Date: 18:30 2017/9/5
    */
   public Result schoolList(String name,Integer currenPage,Integer pageSize) throws Exception;

   /**
    * @Author: 徐慷慨
    * @Description:判断账号是否已被使用
    * @Date: 8:46 2017/9/7
    */

    public boolean mateUsername(String username) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description: 给分校分配课程
       * @param path
     * @Date: 14:48 2017/9/9
     */
     public Result allotCourse(String path,Integer eid) throws Exception;
    //::::::::::::::::::::::::::::::::::::::::::员工:::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * @Author: 徐慷慨
     * @Description:添加员工信息
     * @Date: 11:44 2017/9/5
     */
    public Result insertPerson(PersonnelBean personnelBean) throws  Exception;

    /**
     * @Author: 徐慷慨
     * @Description:根据id查员工信息
     * @Date: 11:47 2017/9/5
     */
    public Result selectPersionById(Integer fid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:删除员工
     * @Date: 10:17 2017/9/5
     */
    public Result deletePersion(String eid);

    /**
     * @Author: 徐慷慨
     * @Description:修改员工信息
     * @Date: 13:36 2017/9/5
     */
    public Result updatePersion(PersonnelBean personnelBean) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:给员工添加角色
     * @Date: 14:30 2017/9/5
     */
    public Result insertPerRole(Integer fid, String gid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description: 查询员工下的资源
     * @Date: 15:18 2017/9/5
     */
    public List<PersonZiYuanBean> perResourceList(String gid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:查询某个学校下所有员工
       * @param eid
     * @Date: 18:34 2017/9/5
     */
    public Result perBySchoolList(Integer eid,Integer gid,String name,Integer currentPage,Integer pageSize) throws Exception;
    //::::::::::::::::::::::::::::::::::::::::::::角色::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * @Author: 徐慷慨
     * @Description:添加角色
     * @Date: 14:01 2017/9/5
     */
    public Result insertRole(Grole grole) throws Exception;


    /**
     * @Author: 徐慷慨
     * @Description:修改角色
     * @Date: 14:04 2017/9/5
     */
    public Result updateRole(Grole grole) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:删除角色
     * @Date: 14:05 2017/9/5
     */
    public Result deleteRole(String gid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:角色列表
     * @Date: 14:06 2017/9/5
     */
    public Result rolerList(Integer eid,Integer currentPage,Integer pageSize,String role) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:给角色添加资源
     * @Date: 14:42 2017/9/5
     */
    public Result inRoleResour(Integer gid ,String hid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:修改角色资源
     * @Date: 14:43 2017/9/5
     */
    public Result upRoleResour(Jroleandresource jroleandresource) throws Exception;


    //::::::::::::::::::::::::::::::::::::::::::::::::资源管理::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


    /**
     * @Author: 徐慷慨
     * @Description:添加资源管理
     * @Date: 14:01 2017/9/5
     */
    public Result insertResource(Hresource hresource);


    /**
     * @Author: 徐慷慨
     * @Description:修改资源管理
     * @Date: 14:04 2017/9/5
     */
    public Result updateResource(Hresource hresource) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:删除资源管理
     * @Date: 14:05 2017/9/5
     */
    public Result deleteResource(String hid);

    /**
     * @Author: 徐慷慨
     * @Description:资源管理列表
     * @Date: 14:06 2017/9/5
     */
    public Result resourceList(Integer eid) throws Exception;
}
