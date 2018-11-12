package com.baizhi.service;

import com.baizhi.entity.Guru;

import java.util.List;

public interface GuruService {
    void add(Guru guru);

    void update(Guru guru);

    void delete(String id);

    Long findTotals();

    List<Guru> findByPage(Integer page, Integer rows);
    Guru FindById(String id);
    List<Guru> findAllName();

}
