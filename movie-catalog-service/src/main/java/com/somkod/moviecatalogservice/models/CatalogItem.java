package com.somkod.moviecatalogservice.models;

public class CatalogItem {

    public String name;
    public String desc;
    public int rating;

    public CatalogItem(String name,String desc,int rating) {
        this.desc = desc;
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
