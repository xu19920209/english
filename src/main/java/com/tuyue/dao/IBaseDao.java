package com.tuyue.dao;

import com.tuyue.util.Page;
import com.tuyue.util.Parameter;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface IBaseDao<T> {
    //单个CRUD
    public int save(final T entity);

    public int saveUUid(final T entity);

    public boolean delete(final T entity) throws Exception;

    public int deleteWithHql(final String hql);

    public boolean update(final T entity) throws Exception;

    public T getOne(Class<T> clazz, final int id) throws Exception;

    public int updateandsave(String hql);

    //批处理
    public int batchSave(final List<T> list) throws Exception;

    public boolean deleteAll(final Collection entities) throws Exception;

    //createQuery（Query）
    public T findOne(final String hql) throws Exception;

    public T findOne(final String hql, final Parameter parameter) throws Exception;

    //list查询
    public List<T> findList(final String hql) throws Exception;

    public List<T> findList(final String hql, final Parameter parameter) throws Exception;

    //分页查询
    public Page<T> findPage(final int currentPage, final int pageSize, final String queryHql, final String countHql, final Object[] values)
            throws HibernateException, SQLException;

    public Page<T> findPage(final int currentPage, final int pageSize, final String queryHql, final String countHql)
            throws HibernateException, SQLException;

    //查询满足条件的记录数
    public long findCount(final String hql);

    public long findCount(final String hql, final Object[] values);

    //查询几条数据
    public List<T> findListNum(final String hql, final Integer num);

    public Session getSessions();
}
