package com.baizhi.service;

import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterDAO chapterDAO;
    @Override
    public void add(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapterDAO.save(chapter);
    }
   /* @Override
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
    }*/


    @Override
    public Chapter queryOne(String id) {
        return chapterDAO.queryOne(id);
    }

}
