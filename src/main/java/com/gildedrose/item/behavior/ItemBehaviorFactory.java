package com.gildedrose.item.behavior;

import com.gildedrose.item.DynamicItem;
import com.gildedrose.item.ItemBehavior;

import java.util.function.Consumer;

/**
 * Creates item behaviors according to common patterns.
 */
public final class ItemBehaviorFactory {

    /**
     * Creates an item behavior which will change according to a static offset. After the item has expired, the offset
     * will be doubled.
     *
     * @param offset The offset which is applied every update.
     * @return The created {@link ItemBehavior}.
     */
    public static ItemBehavior doubleQualityOffsetWhenExpired(int offset) {
        return new SequentialQualityBehaviorBuilder(item -> item.offsetQuality(offset))
                .when(DynamicItem::isExpired)
                .then(item -> item.offsetQuality(offset * 2))
                .build();
    }

    /**
     * Creates a builder object which can be used to create custom behavior.
     *
     * @param initialQualityUpdate The initial quality change behavior.
     * @return The created {@link SequentialQualityBehaviorBuilder}.
     */
    public static SequentialQualityBehaviorBuilder createCustomBehavior(Consumer<DynamicItem> initialQualityUpdate) {
        return new SequentialQualityBehaviorBuilder(initialQualityUpdate);
    }
}
