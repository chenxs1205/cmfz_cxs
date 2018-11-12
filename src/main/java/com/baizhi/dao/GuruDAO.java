package com.baizhi.dao;

import com.baizhi.entity.Guru;

import java.util.List;

public interface GuruDAO extends BaseDAO<Guru> {
    List<Guru> queryAllName();
}
