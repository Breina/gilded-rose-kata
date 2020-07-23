package com.gildedrose.item;

import com.gildedrose.item.behavior.ItemBehaviorFactory;

/**
 * A set of distinct item behaviors which can be applied to {@link DynamicItem}s.
 */
public final class CommonItemBehaviors {

    /**
     * Standard quality behavior will reduce its quality by 1 every update and by 2 after items have expired.
     *
     * @return The standard item behavior.
     */
    public static ItemBehavior standard() {
        return ItemBehaviorFactory.doubleQualityOffsetWhenExpired(-1);
    }

    /**
     * Conjured quality behavior is like {@link #standard()} behavior, but will decrease in quality twice as fast.
     *
     * @return The conjured item behavior.
     */
    public static ItemBehavior conjured() {
        return ItemBehaviorFactory.doubleQualityOffsetWhenExpired(-2);
    }

    /**
     * The cheese quality behavior will increase its quality by 1 every update and by 2 after items have expired.
     *
     * @return The cheese item behavior.
     */
    public static ItemBehavior cheese() {
        return ItemBehaviorFactory.doubleQualityOffsetWhenExpired(+1);
    }

    /**
     * The legendary item behavior will ignore all updates. It won't even change its Sell In value.
     *
     * @return The legendary item behavior which does nothing.
     */
    public static ItemBehavior legendary() {
        return item -> {
        };
    }

    /**
     * The backstage pass quality behavior increases in Quality as its SellIn value approaches;
     * Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
     * Quality drops to 0 after the concert.
     *
     * @return The backstage pass item quality behavior.
     */
    public static ItemBehavior backStagePass() {
        return ItemBehaviorFactory.createCustomBehavior(item -> item.offsetQuality(1))
                .when(item -> item.getSellIn() <= 10).then(item -> item.offsetQuality(2))
                .when(item -> item.getSellIn() <= 5).then(item -> item.offsetQuality(3))
                .when(DynamicItem::isExpired).then(item -> item.setQuality(0))
                .build();
    }
}
