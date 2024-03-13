package com.raulgarcan.wikitarkov.pojo;

import java.io.Serializable;

public class HideoutModule implements Serializable {
    private String name;
    private Item[] neededItems;
    private int level;

    public HideoutModule(String name, Item[] neededItems, int level) {
        this.name = name;
        this.neededItems = neededItems;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item[] getNeededItems() {
        return neededItems;
    }

    public void setNeededItems(Item[] neededItems) {
        this.neededItems = neededItems;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
