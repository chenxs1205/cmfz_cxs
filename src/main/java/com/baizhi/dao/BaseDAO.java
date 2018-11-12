package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDAO<T>{
    //添加
    void save(T t);
    //修改
    void update(T t);
    //删除
    void delete(String id);

    //批量删除
    void deleteAll(List String);
    //查一果
    T queryOne(String id);
    //查全部
    List<T> findAll();

    //参数1:起始条数 //参数2:每页显示的记录数
    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    //总记录数
    Long findTotals();


}
