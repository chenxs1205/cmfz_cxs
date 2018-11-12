package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;
    @Override
    public void add(Article article) {
        article.setId(UUID.randomUUID().toString());
        articleDAO.save(article);
    }

    @Override
    public void update(Article article) {
        articleDAO.update(article);
}

    @Override
    public void delete(String id) {
        articleDAO.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> findByPage(Integer page, Integer rows) {
        int start=(page-1)*rows;

        return articleDAO.findByPage(start,rows);
    }

    @Override
    public Article queryOne(String id) {

        return articleDAO.queryOne(id);
    }

    @Override
    public Long findTotals() {
        return articleDAO.findTotals();
    }
}
