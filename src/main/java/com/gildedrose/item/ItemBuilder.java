package com.gildedrose.item;

import java.util.Objects;

import static com.gildedrose.item.DynamicItem.MIN_QUALITY;

/**
 * Builder class for {@link DynamicItem}s.
 */
public class ItemBuilder {

    private final String name;
    private int sellIn, quality;
    private ItemBehavior itemBehavior;

    /**
     * Creates a new instance of this builder.
     *
     * @param name The name of the item which is being built.
     */
    public ItemBuilder(String name) {
        this.name = name;
    }

    /**
     * Static helper method to create a new instance of this builder.
     *
     * @param name The name of the item which is being built.
     * @return The newly created instance of the item builder.
     */
    public static ItemBuilder item(String name) {
        return new ItemBuilder(name);
    }

    /**
     * Sets time in days until the item needs to be sold.
     *
     * @param sellIn The new Sell In value.
     * @return This builder instance.
     */
    public ItemBuilder sellingIn(int sellIn) {
        this.sellIn = sellIn;
        return this;
    }

    /**
     * Sets how valuable the item is.
     *
     * @param quality The new quality value of the item.
     * @return This builder instance.
     */
    public ItemBuilder ofQuality(int quality) {
        if (quality < MIN_QUALITY) {
            throw new IllegalArgumentException("The Quality of an item is never negative.");
        }

        this.quality = quality;
        return this;
    }

    /**
     * Sets the item's behavior indicating how it reacts to item updates which occur at the end of each day.
     *
     * @param itemBehavior The desired {@link ItemBehavior}.
     * @return This builder instance.
     * @implNote It may be helpful to return one provided by {@link CommonItemBehaviors}.
     */
    public ItemBuilder behavingLike(ItemBehavior itemBehavior) {
        Objects.requireNonNull(itemBehavior);

        this.itemBehavior = itemBehavior;
        return this;
    }

    /**
     * Creates the built instance.
     *
     * @return The newly created instance of {@link DynamicItem}.
     */
    public DynamicItem build() {
        if (itemBehavior == null) {
            itemBehavior = CommonItemBehaviors.standard();
        }
        return new DynamicItem(name, sellIn, quality, itemBehavior);
    }
}
