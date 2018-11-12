package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleService {
    void add(Article article);

    void update(Article article);

    void delete(String id);

    Long findTotals();

    List<Article> findByPage(Integer page, Integer rows);

    Article queryOne(String id);

}
