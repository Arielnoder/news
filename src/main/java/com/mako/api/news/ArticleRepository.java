package com.mako.api.news;

import org.springframework.data.repository.CrudRepository;

import com.mako.api.news.Article;

public interface ArticleRepository extends CrudRepository<Article, String> {

}