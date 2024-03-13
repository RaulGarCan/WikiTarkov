package com.raulgarcan.wikitarkov.pojo.enums;

import java.io.Serializable;

public enum WishReason implements Serializable {
    HIDEOUT,
    MONEY,
    QUEST,
    TRADE,
    CRAFTING,
    OTHER;
    private double price;
    WishReason(){
        price = 0d;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
