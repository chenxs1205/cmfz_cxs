package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDAO albumDAO;
    @Override
    public void add(Album album) {
        album.setId(UUID.randomUUID().toString());
        albumDAO.save(album);
    }
    @Override
    public void update(Album album) {
        albumDAO.update(album);
}

    @Override
    public void delete(String id) {
        albumDAO.delete(id);
    }

    @Override
    public List<Album> findAll() {
        return albumDAO.findAll();
    }


    @Override
    public Album queryOne(String id) {
        return albumDAO.queryOne(id);
    }

}
