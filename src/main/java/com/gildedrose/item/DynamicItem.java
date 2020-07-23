package com.gildedrose.item;

import com.gildedrose.Item;
import com.gildedrose.update.Updatable;

/**
 * This class is basically a wrapper for {@link Item}, intended to produce additional functionality without
 * angering the goblin in the corner.
 */
public class DynamicItem extends Item implements Updatable {

    static final int MIN_QUALITY = 0;
    static final int MAX_QUALITY = 50;

    /**
     * The ItemBehavior describes how this item behaves when an update occurs.
     */
    private final ItemBehavior itemBehavior;

    /**
     * Creates a new DynamicItem with the given parameters.
     *
     * @param name         The name of the item.
     * @param sellIn       The number of days we have to sell the item.
     * @param quality      How valuable the item is.
     * @param itemBehavior Describes how the item reacts to updates.
     */
    DynamicItem(String name, int sellIn, int quality, ItemBehavior itemBehavior) {
        super(name, sellIn, quality);
        this.itemBehavior = itemBehavior;
    }

    /**
     * Returns the number of days we have to sell the item.
     *
     * @return The sellIn value.
     */
    public int getSellIn() {
        return sellIn;
    }

    /**
     * Offsets the current sellIn value by the given amount.
     *
     * @param sellInOffset The amount by which to offset the sellIn value. May be negative.
     */
    public void offsetSellIn(int sellInOffset) {
        sellIn += sellInOffset;
    }

    /**
     * Indicates whether or not the item has passes its sellIn value.
     *
     * @return True if the item is past its due date.
     */
    public boolean isExpired() {
        return sellIn < 0;
    }

    /**
     * Offsets the quality by the given amount.
     *
     * @param qualityOffset The quality to offset the item's quality by, may be negative.
     */
    public void offsetQuality(int qualityOffset) {
        quality += qualityOffset;

        if (quality < MIN_QUALITY) {
            quality = MIN_QUALITY;

        } else if (quality > MAX_QUALITY) {
            quality = MAX_QUALITY;
        }
    }

    /**
     * Sets the quality to a new value.
     *
     * @param quality The new value to set to the item's quality.
     */
    public void setQuality(int quality) {
        assert quality >= MIN_QUALITY : "The Quality of an item is never negative.";
        assert quality <= MAX_QUALITY : "The Quality of an item is never more than " + MAX_QUALITY + '.';

        this.quality = quality;
    }

    @Override
    public void update() {
        itemBehavior.update(this);
    }
}
