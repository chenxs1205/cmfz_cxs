package com.baizhi.service;

import com.baizhi.dao.GuruDAO;
import com.baizhi.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {

   @Autowired
   private GuruDAO guruDAO;

    @Override
    public void add(Guru guru) {
        guru.setId(UUID.randomUUID().toString());
        guruDAO.save(guru);
    }

    @Override
    public void update(Guru guru) {

    }

    @Override
    public void delete(String id) {
        guruDAO.delete(id);
    }

    @Override
    public Long findTotals() {
        return guruDAO.findTotals();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Guru> findByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;

        return guruDAO.findByPage(start,rows);
    }

    @Override
    public Guru FindById(String id) {
        return guruDAO.queryOne(id);
    }

    @Override
    public List<Guru> findAllName() {
        return guruDAO.queryAllName();
    }


}
