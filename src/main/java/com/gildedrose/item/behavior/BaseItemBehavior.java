package com.gildedrose.item.quality_behavior;

import com.gildedrose.item.DynamicItem;
import com.gildedrose.item.ItemBehavior;

public abstract class BaseItemBehavior implements ItemBehavior {

    protected void reduceSellIn(DynamicItem item) {
        item.offsetSellIn(-1);
    }

    protected abstract void updateQuality(DynamicItem item);

    @Override
    public final void update(DynamicItem item) {
        reduceSellIn(item);
        updateQuality(item);
    }
}
