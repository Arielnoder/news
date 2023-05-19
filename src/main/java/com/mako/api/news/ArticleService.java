package com.mako.api.news;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.mako.api.news.Article;
import com.mako.api.news.ArticleRepository;
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    public List<Article> allArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    public Article createArticle(Article b) {
return articleRepository.save(b);

    }

    public Article findArticle(String id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isPresent()) {
            return optionalArticle.get();
        } else {
            return null;
        }
    }

    public Article updateArticle(String id, String title, String category, String image, String description, String url) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setTitle(title);
            article.setCategory(category);
            article.setImage(image);
            article.setDescription(description);
            article.setUrl(url);
            return articleRepository.save(article);
        } else {
            return null;
        }
    }

    public void deleteArticle(String id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isPresent()) {
            articleRepository.deleteById(id);
        } else {
            return;
        }
    }



}
