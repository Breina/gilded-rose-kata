package com.gildedrose.item;

/**
 * Describes how the item reacts to updates, which occur on the end of each day.
 */
@FunctionalInterface
public interface ItemBehavior {

    /**
     * The update event which occurs at the end of each day.
     *
     * @param item The item on which the update ought to occur.
     */
    void update(DynamicItem item);
}
