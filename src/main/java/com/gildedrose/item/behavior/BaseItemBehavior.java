package com.gildedrose.item.behavior;

import com.gildedrose.item.DynamicItem;
import com.gildedrose.item.ItemBehavior;

/**
 * High level implementation of ItemBehavior for all behaviors that include reducing the Sell In.
 */
public abstract class BaseItemBehavior implements ItemBehavior {

    /**
     * Reduces the Sell In by one.
     *
     * @param item The item of which the value is reduced.
     */
    protected void reduceSellIn(DynamicItem item) {
        item.offsetSellIn(-1);
    }

    /**
     * Template method which can be used to update the quality of a particular item.
     *
     * @param item The item on which to update the quality.
     */
    protected abstract void updateQuality(DynamicItem item);

    @Override
    public final void update(DynamicItem item) {
        reduceSellIn(item);
        updateQuality(item);
    }
}
