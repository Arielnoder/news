package com.mako.api.news;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class MainController {
    public ArticleService articleService;

    public MainController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @PostMapping("/saveAll")
    public ResponseEntity<Article> saveAllArticles() {
        Iterable<Article> articles = new getArticleList().getArticles();
        return new ResponseEntity(articleService.saveAllArticles(articles), HttpStatus.OK);
    }

    @PostMapping("/saveArticleById/{id}")
    public ResponseEntity<Article> saveArticleById(@PathVariable String id) {
        Iterable<Article> articles = new getArticleList().getArticles();
        return new ResponseEntity(articleService.saveArticleById(id, articles), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Article> deleteAllArticles() {
        Iterable<Article> articles = new getArticleList().getArticles();
        return new ResponseEntity(articleService.deleteAllArticles(articles), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticles() {
        return new ResponseEntity(articleService.allArticles(), HttpStatus.OK);
    }
    @GetMapping("/findArticlesByCategory/{category}")
    public ResponseEntity<List<Article>> findArticlesByCategory(@PathVariable String category) {
        return new ResponseEntity(articleService.findArticlesByCategory(category), HttpStatus.OK);
    }



    @GetMapping("/findArticle/{id}")
    public ResponseEntity<Article> findArticle(@PathVariable String id) {
        return new ResponseEntity<>(articleService.findArticle(id), HttpStatus.OK);
    }

    @PutMapping("/updateArticle/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable String id, @RequestBody CreateArticleInput createArticleInput) {
        return new ResponseEntity(articleService.updateArticle(id, createArticleInput.title(), createArticleInput.category(), createArticleInput.image(), createArticleInput.description(), createArticleInput.url()), HttpStatus.OK);
    }

    @DeleteMapping("/deleteArticle/{id}")
    public ResponseEntity<Article> deleteArticleById(@PathVariable String id) {


        return new ResponseEntity(articleService.deleteArticleById(id), HttpStatus.OK);
    }








}





