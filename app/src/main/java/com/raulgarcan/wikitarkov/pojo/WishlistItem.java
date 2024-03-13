package com.raulgarcan.wikitarkov.pojo;

import com.raulgarcan.wikitarkov.pojo.enums.WishReason;

import java.io.Serializable;

public class WishlistItem implements Serializable {
    private Item item;
    private WishReason wishReason;
    private String wishDescription;

    public WishlistItem(Item item, WishReason wishReason, String wishDescription) {
        this.item = item;
        this.wishReason = wishReason;
        this.wishDescription = wishDescription;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public WishReason getWishReason() {
        return wishReason;
    }

    public void setWishReason(WishReason wishReason) {
        this.wishReason = wishReason;
    }

    public String getWishDescription() {
        return wishDescription;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }
}
