package com.gildedrose.item.behavior;

import com.gildedrose.item.DynamicItem;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.gildedrose.item.behavior.SequentialQualityBehavior.QualityMutator;

/**
 * A builder class for {@link SequentialQualityBehavior}s.
 */
public class SequentialQualityBehaviorBuilder {

    private final Consumer<DynamicItem> initialQualityUpdate;
    private final List<QualityMutator> qualityMutators = new LinkedList<>();

    /**
     * Creates a new instance of this builder.
     *
     * @param initialQualityUpdate The initial quality behavior.
     */
    SequentialQualityBehaviorBuilder(Consumer<DynamicItem> initialQualityUpdate) {
        this.initialQualityUpdate = initialQualityUpdate;
    }

    /**
     * Creates a new condition for when to trigger the next behavior.
     *
     * @param condition The predicate of the condition.
     *
     * @return A {@link Condition} object on which its quality mutator can be applied.
     */
    public Condition when(Predicate<DynamicItem> condition) {
        return new Condition(condition);
    }

    /**
     * Creates the instance which has been built.
     *
     * @return The created {@link SequentialQualityBehavior} object.
     */
    public SequentialQualityBehavior build() {
        return new SequentialQualityBehavior(initialQualityUpdate, qualityMutators);
    }

    public class Condition {
        private final Predicate<DynamicItem> condition;

        private Condition(Predicate<DynamicItem> condition) {
            this.condition = condition;
        }

        /**
         * Sets the quality mutator on this condition. This will be applied when the condition succeeds.
         *
         * @param qualityMutator The quality mutator which is applied upon {@link DynamicItem}s.
         * @return The {@link SequentialQualityBehaviorBuilder} object which used for further building.
         */
        public SequentialQualityBehaviorBuilder then(Consumer<DynamicItem> qualityMutator) {
            qualityMutators.add(new QualityMutator(condition, qualityMutator));
            return SequentialQualityBehaviorBuilder.this;
        }
    }
}
