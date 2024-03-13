package com.raulgarcan.wikitarkov.pojo;

import com.raulgarcan.wikitarkov.pojo.enums.Category;

import java.io.Serializable;

public class Item implements Serializable {
    private String longName;
    private String shortName;
    private Category category;

    public Item(String longName, String shortName, Category category) {
        this.longName = longName;
        this.shortName = shortName;
        this.category = category;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
