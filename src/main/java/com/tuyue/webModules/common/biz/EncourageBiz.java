package com.tuyue.webModules.common.biz;


import com.tuyue.result.Result;
import com.tuyue.pojo.*;
import java.sql.SQLException;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/21.
 */
public interface EncourageBiz {
    /**
     * @Author: 徐慷慨
     * @Description:添加鼓励
     * @Date: 9:56 2017/11/21
     */
    public Result inEncourage(Encourage encourage);

    /**
     * @Author: 徐慷慨
     * @Description:修改鼓励语
     * @Date: 9:58 2017/11/21
     */
    public Result upEncourage(Encourage encourage) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:删除鼓励语
     * @Date: 9:59 2017/11/21
     */
    public Result delEncourage(String ids);
    /**
     * @Author: 徐慷慨
     * @Description:鼓励语列表
     * @Date: 9:59 2017/11/21
     */
    public Result encourageList(Integer currentPage ,Integer pageSize,Integer type) throws Exception;
}
