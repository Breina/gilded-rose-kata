package com.gildedrose.item.behavior;

import com.gildedrose.item.DynamicItem;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CustomQualityBehavior extends BaseItemBehavior {

    private final Queue<QualityMutator> qualityMutators;
    private Consumer<DynamicItem> currentQualityMutator;

    CustomQualityBehavior(Consumer<DynamicItem> initialQualityUpdate,
                          Collection<QualityMutator> qualityMutators) {

        this.currentQualityMutator = initialQualityUpdate;
        this.qualityMutators = new LinkedList<>(qualityMutators);
    }

    @Override
    protected void updateQuality(DynamicItem item) {
        if (!qualityMutators.isEmpty() && qualityMutators.peek().test(item)) {
            currentQualityMutator = qualityMutators.poll().getQualityMutator();
        }

        currentQualityMutator.accept(item);
    }

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
