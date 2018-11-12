package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDAO bannerDAO;
    @Override
    public void add(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        bannerDAO.save(banner);
    }

    @Override
    public void update(Banner banner) {
        bannerDAO.update(banner);
}

    @Override
    public void delete(String id) {
        bannerDAO.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> findByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;

        return bannerDAO.findByPage(start,rows);
    }

    @Override
    public Banner queryOne(String id) {

        return bannerDAO.queryOne(id);
    }

    @Override
    public Long findTotals() {
        return bannerDAO.findTotals();
    }
}
