package com.mako.api.news;

import org.jsoup.Jsoup;

import java.io.IOException;

public record CreateArticleInput(String title, String category, String image, String description, String url ) {

    public static Article toArticle() {
        org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.connect("https://www.mako.co.il/news-dailynews?partner=NewsNavBar").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        org.jsoup.select.Elements articleElements = doc.select("li.regularitemsmall:nth-of-type(n+2) a");

            String articleId = articleElements.attr("href").substring(1);
            org.jsoup.nodes.Document articleDoc = null;
            try {
                articleDoc = Jsoup.connect("https://www.mako.co.il/" + articleElements.attr("href")).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String title = articleDoc.select("h1").text();
            String category = articleDoc.select(".here span").text();
            String image = articleDoc.select(".article-header figure img").attr("src");
            String description = articleDoc.select("h2").text();
            String url = ("https://www.mako.co.il/" + articleElements.attr("href"));

            Article article = new Article();

            article.setId(articleId);
            article.setTitle(title);
            article.setCategory(category);
            article.setImage(image);
            article.setDescription(description);
            article.setUrl(url);

            return article;

        }

    }



