package com.gildedrose.update;

/**
 * Indicates that an object ought to be updated at the end of each day.
 */
@FunctionalInterface
public interface Updatable {

    /**
     * The update which is called at the end of each day.
     */
    void update();
}
