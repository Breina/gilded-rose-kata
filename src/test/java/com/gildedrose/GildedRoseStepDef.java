package com.gildedrose;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class GildedRoseStepDef {

    private String itemName;
    private int itemQuality;
    private int itemSellIn;

    private GildedRose gildedRose;

    @Given("^an item \"([^\"]*)\"$")
    public void anItem(String name) {
        itemName = name;
    }

    @Given("^the quality is (-?\\d+)")
    public void theQualityIs(int quality) {
        itemQuality = quality;
    }

    @Given("^the sell in is (-?\\d+)")
    public void theSellInIs(int sellIn) {
        itemSellIn = sellIn;
    }

    @Given("^an item \"([^\"]*)\" with quality (-?\\d+) and sell in (-?\\d+)$")
    public void anItemWithQualityAndSellIn(String name, int quality, int sellIn) {
        itemName = name;
        itemQuality = quality;
        itemSellIn = sellIn;
    }

    @When("^I update quality$")
    public void iUpdateQuality() {
        Item item = new Item(itemName, itemSellIn, itemQuality);
        Item[] items = {item};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
    }

    @Then("^the quality is updated to (-?\\d+)$")
    public void theQualityIsUpdatedTo(int expected) {
        assertEquals(expected, gildedRose.items[0].quality);
    }

    @Then("^the sell in is updated to (-?\\d+)$")
    public void theSellInIsUpdatedTo(int expected) {
        assertEquals(expected, gildedRose.items[0].sellIn);
    }

    @Then("^the quality becomes (-?\\d+) and sell in becomes (-?\\d+)$")
    public void theQualityBecomesAndSellInBecomes(
            int expectedQuality, int expectedSellIn) {
        assertEquals(expectedQuality, gildedRose.items[0].quality);
        assertEquals(expectedSellIn, gildedRose.items[0].sellIn);
    }

    @Then("^the item's name is \"([^\"]*)\"$")
    public void theItemSNameIs(String name) {
        assertEquals(name, itemName);
        assertEquals(name, gildedRose.items[0].name);
    }


}

