package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;

public interface BannerService {
    void add(Banner banner);

    void update(Banner banner);

    void delete(String id);

    Long findTotals();

    List<Banner> findByPage(Integer page,Integer rows);

    Banner queryOne(String id);

}
