package com.gildedrose.item.behavior;

import com.gildedrose.item.DynamicItem;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Creates a quality behavior according to predicates which are checked. Every time such a check proceeds, the next
 * quality behavior is used when applying updates. If there are no further predicates, the last quality behavior
 * remains active.
 */
public class SequentialQualityBehavior extends BaseItemBehavior {

    /**
     * The queue of {@link QualityMutator}s which are applied in-order.
     */
    private final Queue<QualityMutator> qualityMutators;

    /**
     * The current quality behavior. This can be replaced when the next predicate of the {@link QualityMutator}s
     * succeeds.
     */
    private Consumer<DynamicItem> currentQualityMutator;

    /**
     * Creates a new instance.
     *
     * @param initialQualityUpdate The initial quality behavior.
     * @param qualityMutators      An ordered collection of mutators which are applied in order when their predicates
     *                             succeed.
     */
    SequentialQualityBehavior(Consumer<DynamicItem> initialQualityUpdate,
                              Collection<QualityMutator> qualityMutators) {

        this.currentQualityMutator = initialQualityUpdate;
        this.qualityMutators = new LinkedList<>(qualityMutators);
    }

    /**
     * Traverses the {@link QualityMutator}s with passing predicates and executes the last succeeding one.
     *
     * @param item The item on which to update the quality.
     */
    @Override
    protected void updateQuality(DynamicItem item) {
        while (!qualityMutators.isEmpty() && qualityMutators.peek().test(item)) {
            currentQualityMutator = qualityMutators.poll().getQualityMutator();
        }

        currentQualityMutator.accept(item);
    }

    /**
     * The QualityMutator has two functions.<br>
     * 1. Check whether or not it can be applied.<br>
     * 2. Apply its quality mutation to {@link DynamicItem}s.
     */
    static class QualityMutator {
        private final Predicate<DynamicItem> condition;
        private final Consumer<DynamicItem> qualityMutator;

        QualityMutator(Predicate<DynamicItem> condition, Consumer<DynamicItem> qualityMutator) {
            this.condition = condition;
            this.qualityMutator = qualityMutator;
        }

        private boolean test(DynamicItem item) {
            return condition.test(item);
        }

        public Consumer<DynamicItem> getQualityMutator() {
            return qualityMutator;
        }
    }
}
