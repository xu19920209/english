package com.tuyue.appModules.personalCenter.biz;

import com.tuyue.pojo.Nstudent;
import com.tuyue.pojo.PointLike;
import com.tuyue.result.Result;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
public interface IpersonalCenterBiz {
    /**
     * @Author: 徐慷慨
     * @Description:注册用户
     * @Date: 9:37 2017/9/14
     */
    public Result register(Nstudent nstudent) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:登录
     * @Date: 9:38 2017/9/14
     */
    public Result login(Nstudent nstudent) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:忘记密码
     * @Date: 9:39 2017/9/14
     */
    public Result forgetPwd(Nstudent nstudent) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description: 修改用户
     * @Date: 10:01 2017/9/14
     */
    public Result updataUser(Nstudent nstudent) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:根据id查用户
     * @Date: 10:06 2017/9/14
     */
    public Result findById(Integer nid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:签到
     * @Date: 20:10 2017/9/14
     */
    public Result sign(Integer nid) throws Exception;

  /**
   * @Author: 徐慷慨
   * @Description: 用户成绩列表
   * @Date: 13:55 2017/11/9
   */
  public Result gradeList(Integer nid) throws Exception;

  /**
   * @Author: 徐慷慨
   * @Description:点赞
   * @Date: 16:17 2017/11/9
   */
  public Result pointLike(PointLike pointLike) throws Exception;

  /**
   * @Author: 徐慷慨
   * @Description:根据微信的openId判断用户是否存在
   * @Date: 19:38 2017/12/18
   */
  public Nstudent selectByopenId(String openId) throws Exception;
}
