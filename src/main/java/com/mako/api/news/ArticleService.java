package com.mako.api.news;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    public List<Article> allArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    public Iterable<Article> saveAllArticles(Iterable<Article> articles) {
        for (Article article : articles) {
            if (article.getDescription().length() > 250) {
                article.setDescription(article.getDescription().substring(0, 250));
            }
        }
        return articleRepository.saveAll(articles);


    }

    public Article saveArticleById(String id, Iterable<Article> articles) {
        for (Article article : articles) {
            if (article.getId().equals(id)) {
                return articleRepository.save(article);
            }
        }
        return null;

    }



    public Iterable<Article> deleteAllArticles(Iterable<Article> articles) {
        articleRepository.deleteAll(articles);
        return articles;
    }



    public Article findArticle(String id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.orElse(null);
    }



    // find all articles by category
    public Iterable<Article> findArticlesByCategory(String category) {
        Iterable<Article> articles = articleRepository.findAll();
        List<Article> articlesByCategory = new ArrayList<>();
        for (Article article : articles) {
            if (article.getCategory().equals(category)) {
                articlesByCategory.add(article);
            }
        }
        return articlesByCategory;
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

    public Article deleteArticleById(String id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            articleRepository.delete(article);
            return article;
        } else {
            return null;
        }


    }





}
