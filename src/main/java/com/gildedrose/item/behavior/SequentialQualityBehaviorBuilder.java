package com.gildedrose.item.behavior;

import com.gildedrose.item.DynamicItem;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.gildedrose.item.behavior.CustomQualityBehavior.*;

public class CustomQualityBehaviorBuilder {

    private final Consumer<DynamicItem> initialQualityUpdate;
    private List<QualityMutator> qualityMutators;

    CustomQualityBehaviorBuilder(Consumer<DynamicItem> initialQualityUpdate) {
        this.initialQualityUpdate = initialQualityUpdate;
    }

    public Condition when(Predicate<DynamicItem> condition) {
        return new Condition(condition);
    }

    public class Condition {
        private final Predicate<DynamicItem> condition;

        public Condition(Predicate<DynamicItem> condition) {
            this.condition = condition;
        }

        public CustomQualityBehaviorBuilder then(Consumer<DynamicItem> qualityMutator) {
            qualityMutators.add(new QualityMutator(condition, qualityMutator));
            return CustomQualityBehaviorBuilder.this;
        }
    }

    public CustomQualityBehavior build() {
        return new CustomQualityBehavior(initialQualityUpdate, qualityMutators);
    }
}
