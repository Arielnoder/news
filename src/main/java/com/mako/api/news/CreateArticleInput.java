package com.mako.api.news;



public record CreateArticleInput(String Id,String title, String category, String image, String description, String url ) {

    public Article toArticle() {





        Article article = new Article();

        article.setId(Id.substring(Id.lastIndexOf("-") + 1, Id.lastIndexOf(".")));
        article.setTitle(title);
        article.setCategory(category);
        article.setImage(image);
        article.setDescription(description.substring(0, description.length() - 100));
        article.setUrl(url);

        return article;

    }

    }



