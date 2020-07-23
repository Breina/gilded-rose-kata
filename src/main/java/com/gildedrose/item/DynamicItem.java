package com.gildedrose.item;

import com.gildedrose.Item;

public class ItemWrapper extends Item {

    private static final int NO_SELL_IN = -1;

    private final ItemBehavior itemBehavior;

    private ItemWrapper(String name, int sellIn, int quality, ItemBehavior itemBehavior) {
        super(name, sellIn, quality);
        this.itemBehavior = itemBehavior;
    }

    public String getName() {
        return name;
    }

    public boolean hasSellIn() {
        return sellIn != NO_SELL_IN;
    }

    public int getSellIn() {
        assert hasSellIn() : "This item never has to be sold.";
        return sellIn;
    }

    public void setSellIn(Integer sellIn) {
        this.sellIn = sellIn == null
                ? NO_SELL_IN
                : sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void changeQuality(int qualityOffset) {
        this.quality += qualityOffset;
    }

    public void updateQuality() {
        itemBehavior.update(this);
    }
}
