package com.gildedrose;

import com.gildedrose.update.UpdateAdapter;

import java.util.Arrays;

/**
 * The main implementation of our application. This class allows for setting the items and update all of their
 * qualities.
 */
public class GildedRose {

    Item[] items;

    /**
     * Creates a new instance of our application with the given items.
     *
     * @param items The items which are present in this application.
     */
    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     * Updates the quality in parallel of all items.
     */
    public void updateQuality() {
        Arrays.stream(items)
                .parallel()
                .forEach(UpdateAdapter::update);
    }
}