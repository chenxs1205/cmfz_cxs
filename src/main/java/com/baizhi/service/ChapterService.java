package com.baizhi.service;

import com.baizhi.entity.Chapter;

public interface ChapterService {
    void add(Chapter chapter);

   /* void update(Chapter chapter);

    void delete(String id);

    List<Album> findAll();*/

    Chapter queryOne(String id);

}
