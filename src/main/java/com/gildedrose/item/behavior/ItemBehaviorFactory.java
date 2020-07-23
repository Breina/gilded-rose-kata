package com.gildedrose.item.behavior;

import com.gildedrose.item.DynamicItem;
import com.gildedrose.item.ItemBehavior;

import java.util.function.Consumer;

public final class ItemQualityBehaviorFactory {

    public static ItemBehavior doubleOffsetWhenExpired(int offset) {
        return new CustomQualityBehaviorBuilder(item -> item.offsetQuality(offset))
                .when(DynamicItem::isExpired)
                .then(item -> item.offsetQuality(offset * 2))
                .build();
    }

    public static CustomQualityBehaviorBuilder createCustomBehavior(Consumer<DynamicItem> initialQualityUpdate) {
        return new CustomQualityBehaviorBuilder(initialQualityUpdate);
    }
}
