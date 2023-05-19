package com.mako.api.news;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    public ArticleService articleService;

    public MainController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/add")
    public ResponseEntity<Article> addNewArticle(@RequestBody CreateArticleInput article) {
        Article articleCreated = articleService.createArticle(article.toArticle());

        return new ResponseEntity<>(articleCreated, HttpStatus.CREATED);
    }

}
