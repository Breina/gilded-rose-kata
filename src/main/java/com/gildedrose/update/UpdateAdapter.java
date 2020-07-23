package com.gildedrose.update;

import com.gildedrose.Item;
import com.gildedrose.item.DynamicItem;

/**
 * Adapter which will use the refactored code instead of the legacy implementation for those items that support it.
 */
public final class UpdateAdapter {

    /**
     * Executes an update upon the given item.
     *
     * @param item The item which should be updated.
     */
    public static void update(Item item) {
        if (item instanceof DynamicItem) {
            ((DynamicItem) item).update();

        } else {
            LegacyItemUpdater.update(item);
        }
    }
}
