package com.tuyue.appModules.courseGoods.biz.impl;

import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tuyue.appModules.courseGoods.bean.CourseGoodsDetail;
import com.tuyue.appModules.courseGoods.biz.PartMoneyBiz;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import io.netty.channel.ChannelHandler;
import org.aspectj.weaver.ast.Var;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.multi.MultiLabelUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/2/26.
 */
@Service
public class PartMoneyBizImpl implements PartMoneyBiz {
    @Autowired
    private IBaseDao<CourseGoodsOrder> courseGoodsOrderIBaseDao;
    @Autowired
    private IBaseDao<Nstudent> nstudentIBaseDao;
    @Autowired
    private IBaseDao<UserWallet> userWalletIBaseDao;
    @Autowired
    private IBaseDao<Agent> agentIBaseDao;
    @Autowired
    private IBaseDao<WalletDetails> walletDetailsIBaseDao;
    @Autowired
    private IBaseDao<ACourseGood> aCourseGoodIBaseDao;
    @Autowired
    private IBaseDao<AgentAccount> agentAccountIBaseDao;

    @Override
    @Transactional
    public boolean partMoney(String orderNo) throws Exception {
        boolean flag = false;
        try {
            List<AgentAccount> list1 = new ArrayList<AgentAccount>();
            //根据订单号找到订单
            CourseGoodsOrder courseGoodsOrder = courseGoodsOrderIBaseDao.findOne(" FROM CourseGoodsOrder where orderNo='" + orderNo + "'");
            // 根据订单号找到购买者
            Nstudent one = nstudentIBaseDao.findOne("select a from Nstudent a,CourseGoodsOrder b where b.orderNo='" + orderNo + "' and a.nid=b.nid");
            //根据订单找到商品
            System.out.println(courseGoodsOrder.getCourseId());
            ACourseGood one1 = aCourseGoodIBaseDao.getOne(ACourseGood.class, courseGoodsOrder.getCourseId());
            int status = 1;
            if (one1.getCoursePrice() == 399) {
                status = 2;
            } else if (one1.getCoursePrice() == 10000) {
                status = 4;
            } else if (one1.getCoursePrice() == 800) {
                status = 5;
            }
            Double price = one1.getCoursePrice();
            //找到所有父级联系人
            List<Agent> list = agentIBaseDao.findList(" from Agent where nid in (" + one.getParentIds() + ") order by creatTime desc");
            /**
             * map中
             * 2：是校长
             * 22:是vip 会员
             * 3：是总监1
             * 31：是总监2
             * 4:执行董事1
             * 5:执行董事2
             * 6：执行董事3
             */
            Map<String, Integer> map = new HashMap();
            if (list != null && list.size() > 0) {
                if (list.get(0).getAgentStatus() == 2 || list.get(0).getAgentStatus() == 5) { //父级是校长或者是vip会员
                    if (list.get(0).getAgentStatus() == 2) {
                        map.put("2", list.get(0).getNid());
                    } else {
                        map.put("22", list.get(0).getNid());
                    }

                    for (int i = 1; i < list.size(); i++) {
                        if (list.get(i).getAgentStatus() == 2 || list.get(i).getAgentStatus() == 5) { // 校长 或者 vip 会员跳过
                            continue;
                        }
                        if (list.get(i).getAgentStatus() == 3) { //找总监
                            /**
                             * 1：判断是否有执行董事，如果有执行董事则直接跳过
                             * 2：如果没有执行董事，判断是否有3， 有3 则set 31 set 没有3 则 set 3
                             */
                            if (map.get("4") != null) {
                                continue;
                            } else {
                                if (map.get("3") == null) {
                                    map.put("3", list.get(i).getNid());
                                } else {
                                    map.put("31", list.get(i).getNid());
                                }
                            }
                        }
                        if (list.get(i).getAgentStatus() == 4) {
                            if (map.get("6") != null) {
                                break;
                            }
                            if (map.get("4") == null) {
                                map.put("4", list.get(i).getNid());
                                continue;
                            }
                            if (map.get("5") == null) {
                                map.put("5", list.get(i).getNid());
                                continue;
                            }
                            if (map.get("6") == null) {
                                map.put("6", list.get(i).getNid());
                                continue;
                            }
                        }
                    }
                } else if (list.get(0).getAgentStatus() == 3) {  //父级是总监
                    map.put("3", list.get(0).getNid());
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getAgentStatus() == 3) {
                            map.put("31", list.get(i).getNid());
                        }
                        if (list.get(i).getAgentStatus() == 4) {
                            if (map.get("6") != null) {
                                break;
                            }
                            if (map.get("4") == null) {
                                map.put("4", list.get(i).getNid());
                                continue;
                            }
                            if (map.get("5") == null) {
                                map.put("5", list.get(i).getNid());
                                continue;
                            }
                            if (map.get("6") == null) {
                                map.put("6", list.get(i).getNid());
                                continue;
                            }

                        }
                    }
                } else { //父级是执行董事
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getAgentStatus() == 4) {
                            if (map.get("6") != null) {
                                break;
                            }
                            if (map.get("4") == null) {
                                map.put("4", list.get(i).getNid());
                                continue;
                            }
                            if (map.get("5") == null) {
                                map.put("5", list.get(i).getNid());
                                continue;
                            }
                            if (map.get("6") == null) {
                                map.put("6", list.get(i).getNid());
                                continue;
                            }

                        }
                    }
                }
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                }

                /**
                 * 接下来是分红操作
                 * 1：先给购买该课程的人身份（学员、总监、董事，vip 会员）
                 * 2：再确定父级用户的身份 是否要给父级升级为总监
                 * 3给上级分红
                 */
                //修改买该课程的人的状态
                int i = agentIBaseDao.deleteWithHql("update Agent set agentStatus=" + status + "where nid=" + one.getNid());
//
  if(status==2){ //买的是399的课程需要判断父级是否要升为总监
      Agent one3 = agentIBaseDao.findOne("select a from Agent a,Nstudent b where a.nid=b.nid and b.username='" + one.getReferrerPhone() + "'");
      if (one3.getAgentStatus() == 2) { //需要判断是否可以成为总监
          Nstudent one4 = nstudentIBaseDao.getOne(Nstudent.class, one3.getNid());
          long count = nstudentIBaseDao.findCount("select count(*) from Nstudent where  referrerPhone='" + one4.getUsername() + "'");
          if (count >= 5) {
              long count1 = nstudentIBaseDao.findCount("select count(*) from Nstudent where parentIds LIKE '%," + one3.getNid() + ",%' OR parent_ids LIKE '%" + one3.getNid() + "%'");
              if (count1 >= 10) {
                  int j = agentIBaseDao.deleteWithHql("update Agent set agentStatus=3 where nid=" + one3.getNid());
              }
          }
      }
  }

                if (i <= 0) {
                    flag = false;
                    return flag;
                }
                //分红
                if (status == 2) { //买的399的课程
                    // 给校长分红 或者vip 分红
                    if (map.get("2") != null || map.get("22") != null) {
                        AgentAccount agentAccount = new AgentAccount();
                        agentAccount.setMoneyState(2);
                        if (map.get("2") != null) {
                            agentAccount.setOrderMoney(100.0);
                            agentAccount.setNid((Integer) map.get("2"));
                        } else {
                            agentAccount.setOrderMoney(price * 0.1);
                            agentAccount.setNid((Integer) map.get("22"));
                        }
                        agentAccount.setCourseOrderId(courseGoodsOrder.getCourseOrderId());

                        list1.add(agentAccount);
                    }
                    //给总监分红
                    if (map.get("3") != null) {
                        AgentAccount agentAccount = new AgentAccount();
                        agentAccount.setMoneyState(2);
                        if (map.get("2") != null || map.get("22") != null) {
                            agentAccount.setOrderMoney(50.0);
                        } else {
                            agentAccount.setOrderMoney(150.0);
                        }
                        agentAccount.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                        agentAccount.setNid((Integer) map.get("3"));
                        list1.add(agentAccount);
                    }
                    //给执行董事分红
                    if (map.get("4") != null) {
                        AgentAccount agentAccount = new AgentAccount();
                        agentAccount.setMoneyState(2);
                        if (map.get("2") == null && map.get("22") == null && map.get("3") == null) {
                            agentAccount.setOrderMoney(200.0);
                        } else {
                            if (map.get("3") != null) {
                                agentAccount.setOrderMoney(50.0);
                            } else {
                                agentAccount.setOrderMoney(100.0);
                            }
                        }
                        agentAccount.setMoneyState(2);
                        agentAccount.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                        agentAccount.setNid((Integer) map.get("4"));
                        list1.add(agentAccount);
                    }
                } else if (status == 4) { //买的10000元的课程
                    //给校长分红 或者 vip 会员
                    if (map.get("2") != null || map.get("22") != null) {
                        AgentAccount agentAccount = new AgentAccount();
                        agentAccount.setMoneyState(2);
                        if (map.get("2") != null) {
                            agentAccount.setOrderMoney(price * 0.18);
                            agentAccount.setNid((Integer) map.get("2"));
                        } else {
                            agentAccount.setOrderMoney(price * 0.1);
                            agentAccount.setNid((Integer) map.get("22"));
                        }
                        agentAccount.setCourseOrderId(courseGoodsOrder.getCourseOrderId());

                        list1.add(agentAccount);
                    }
                    //给总监分红
                    if (map.get("3") != null) {
                        AgentAccount agentAccount = new AgentAccount();
                        agentAccount.setMoneyState(2);
                        if (map.get("2") != null) {
                            agentAccount.setOrderMoney(price * 0.02);
                        } else {
                            agentAccount.setOrderMoney(price * 0.2);
                        }
                        agentAccount.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                        agentAccount.setNid((Integer) map.get("3"));
                        list1.add(agentAccount);
                        if (map.get("31") != null) {
                            AgentAccount agentAccount1 = new AgentAccount();
                            agentAccount1.setMoneyState(2);
                            agentAccount1.setOrderMoney(price * 0.02);
                            agentAccount1.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                            agentAccount1.setNid((Integer) map.get("31"));
                            list1.add(agentAccount1);
                        }
                    }
                    //给执行董事分红
                    if (map.get("4") != null) {
                        AgentAccount agentAccount = new AgentAccount();
                        if (map.get("2") != null && map.get("3") != null) { //(校长 1800  》》》总监200 >>>执行董事800)
                            agentAccount.setOrderMoney(price * 0.08);
                        } else if (map.get("2") != null || map.get("22") != null) {// 校长 1800  》》》》》执行董事1200
                            if (map.get("2") != null) {
                                agentAccount.setOrderMoney(price * 0.12);
                            } else {
                                agentAccount.setOrderMoney(price * 0.2);
                            }
                        } else if (map.get("3") != null) { // 总监2000  》》》 执行董事800
                            agentAccount.setOrderMoney(price * 0.08);
                        } else {
                            agentAccount.setOrderMoney(price * 0.3);
                        }
                        agentAccount.setMoneyState(2);
                        agentAccount.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                        agentAccount.setNid((Integer) map.get("4"));
                        list1.add(agentAccount);
                        if (map.get("5") != null) {
                            AgentAccount agentAccount1 = new AgentAccount();
                            agentAccount1.setMoneyState(2);
                            agentAccount1.setOrderMoney(price * 0.02);
                            agentAccount1.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                            agentAccount1.setNid((Integer) map.get("5"));
                            list1.add(agentAccount1);
                        }
                        if (map.get("6") != null) {
                            AgentAccount agentAccount2 = new AgentAccount();
                            agentAccount2.setMoneyState(2);
                            agentAccount2.setOrderMoney(price * 0.01);
                            agentAccount2.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                            agentAccount2.setNid((Integer) map.get("6"));
                            list1.add(agentAccount2);
                        }
                    }
                } else if (status == 5) { //买的是800的课程
                    //给 vip 分红
                    if (map.get("22") != null) {
                        AgentAccount agentAccount = new AgentAccount();
                        agentAccount.setMoneyState(2);
                        agentAccount.setOrderMoney(price * 0.1);
                        agentAccount.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                        agentAccount.setNid((Integer) map.get("22"));
                        list1.add(agentAccount);
                    }
                    /**
                     * 给执行董事分红
                     * 1首先判断是否有 vip 会员
                     */
                    if (map.get("4") != null) {
                        AgentAccount agentAccount = new AgentAccount();
                        agentAccount.setMoneyState(2);
                        if (map.get("22") != null) {
                            agentAccount.setOrderMoney(price * 0.05);
                        } else {
                            agentAccount.setOrderMoney(price * 0.15);
                        }
                        agentAccount.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                        agentAccount.setNid((Integer) map.get("4"));
                        list1.add(agentAccount);

                        if (map.get("5") != null) {
                            AgentAccount agentAccount1 = new AgentAccount();
                            agentAccount1.setMoneyState(2);
                            agentAccount1.setOrderMoney(price * 0.02);
                            agentAccount1.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                            agentAccount1.setNid((Integer) map.get("5"));
                            list1.add(agentAccount1);
                        }
                        if (map.get("6") != null) {
                            AgentAccount agentAccount2 = new AgentAccount();
                            agentAccount2.setMoneyState(2);
                            agentAccount2.setOrderMoney(price * 0.01);
                            agentAccount2.setCourseOrderId(courseGoodsOrder.getCourseOrderId());
                            agentAccount2.setNid((Integer) map.get("6"));
                            list1.add(agentAccount2);
                        }
                    }
                }
                for (AgentAccount agentAccount : list1) {
                    System.out.println(agentAccount.toString());
                }
                int i1 = agentAccountIBaseDao.batchSave(list1);
                if (i1 > 0) {
                    flag = true;
                    return flag;
                } else {
                    flag = false;
                    return true;
                }
            } else {
                flag = true;
                return flag;
            }
        } catch (Exception E) {
           // throw new RuntimeException("分红出现异常" + orderNo);
            System.out.println(E.getMessage());
            return false;
        }
    }
}
