package com.mako.api.news;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;

public class getArticleList {
    Iterable<Article> articles = new ArrayList<>();

    public Iterable<Article> getArticles() {
        org.jsoup.nodes.Document doc;
        try {
            doc = Jsoup.connect("https://www.mako.co.il/news-dailynews?partner=NewsNavBar").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        org.jsoup.select.Elements articleElements = doc.select("li.regularitemsmall:nth-of-type(n+2) a");
        Article article;
        for (org.jsoup.nodes.Element articleElement : articleElements) {
            String articleId = articleElement.attr("href").split("/")[articleElement.attr("href").split("/").length - 1];
            org.jsoup.nodes.Document articleDoc;
            try {
                articleDoc = Jsoup.connect("https://www.mako.co.il/" + articleElement.attr("href")).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String title = articleDoc.select("h1").text();
            String category = articleDoc.select(".here span").text();
            String image = articleDoc.select(".article-header figure img").attr("src");
            if (image.equals("")) {
                image = articleDoc.select("body > figure img").attr("data-src");

            }
            if (image.equals("")) {
                image = articleDoc.select("p:nth-of-type(3) img").attr("src");

            }
              if (image.equals("")) {
                    // image = no image found
                    // search for next image it can find
                    image = articleDoc.select("img").attr("src");
                }


            String description = articleDoc.select("h2").text();
            String url = ("https://www.mako.co.il/" + articleElement.attr("href"));
            CreateArticleInput createArticleInput = new CreateArticleInput(articleId, title, category, image, description, url);
            article = createArticleInput.toArticle();
            ((ArrayList<Article>) articles).add(article);

        }
        return articles;
    }
}

