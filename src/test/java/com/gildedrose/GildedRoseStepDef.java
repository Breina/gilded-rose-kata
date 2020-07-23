package com.gildedrose;

import com.gildedrose.item.ItemBehavior;
import com.gildedrose.item.ItemBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.gildedrose.item.CommonItemBehaviors.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GildedRoseStepDef {

    private Item item;
    private GildedRose gildedRose;
    private Exception exception;

    @Given("^an? ?(anything|item|cheese item|backstage pass|legendary item) \"([^\"]*)\" with quality (-?\\d+) to be sold in (-?\\d+)$")
    public void anItem(String type, String name, int quality, int sellIn) {
        exception = null;

        try {
            ItemBuilder itemBuilder = ItemBuilder.item(name)
                    .ofQuality(quality)
                    .sellingIn(sellIn);

            final ItemBehavior itemBehavior;

            switch (type) {
                case "cheese item":
                    itemBehavior = cheese();
                    break;

                case "backstage pass":
                    itemBehavior = backStagePass();
                    break;

                case "legendary item":
                    itemBehavior = legendary();
                    break;

                case "item":
                    itemBehavior = standard();
                    break;

                default:
                    itemBehavior = null;
            }

            if (itemBehavior != null) {
                itemBuilder.behavingLike(itemBehavior);
            }
            item = itemBuilder.build();
            System.out.println("Created item: " + item);

        } catch (RuntimeException e) {
            this.exception = e;
        }
    }

    @When("^the day ends$")
    public void qualityUpdated() {
        Item[] items = {item};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
    }

    @Then("^its quality becomes (-?\\d+)$")
    public void qualityChanged(int quality) {
        Item item = gildedRose.items[0];

        assertEquals("The Quality does not match", quality, item.quality);
    }

    @Then("^it's to be sold in (-?\\d+)$")
    public void soldInChanged(int soldIn) {
        Item item = gildedRose.items[0];

        assertEquals("The Sold In does not match", soldIn, item.sellIn);
    }

    @Then("^it fails$")
    public void itFails() {
        assertNotNull("It should have failed", exception);
    }
}
