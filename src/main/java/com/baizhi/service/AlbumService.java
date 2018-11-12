package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    void add(Album album);

    void update(Album album);

    void delete(String id);

    List<Album> findAll();

    Album queryOne(String id);

}
