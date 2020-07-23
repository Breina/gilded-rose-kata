package com.gildedrose.item;

import java.util.Objects;

import static com.gildedrose.item.DynamicItem.MAX_QUALITY;
import static com.gildedrose.item.DynamicItem.MIN_QUALITY;

public class DynamicItemBuilder {

    private final String name;
    private int sellIn, quality;
    private ItemBehavior itemBehavior;

    public DynamicItemBuilder(String name) {
        this.name = name;
    }

    public DynamicItemBuilder atSellIn(int sellIn) {
        this.sellIn = sellIn;
        return this;
    }

    public DynamicItemBuilder withQuality(int quality) {
        if (quality < MIN_QUALITY) {
            throw new IllegalArgumentException("The Quality of an item is never negative.");
        }
        if (quality > MAX_QUALITY) {
            throw new IllegalArgumentException("The Quality of an item is never more than " + MAX_QUALITY + '.');
        }

        this.quality = quality;
        return this;
    }

    public DynamicItemBuilder behavingLike(ItemBehavior itemBehavior) {
        Objects.requireNonNull(itemBehavior);

        this.itemBehavior = itemBehavior;
        return this;
    }

    public DynamicItem build() {
        return new DynamicItem(name, sellIn, quality, itemBehavior);
    }
}
