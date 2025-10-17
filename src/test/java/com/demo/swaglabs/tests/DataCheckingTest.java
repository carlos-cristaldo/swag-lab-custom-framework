package com.demo.swaglabs.tests;

import com.demo.swaglabs.pages.*;
import com.demo.swaglabs.utilities.Constants;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataCheckingTest extends BaseTest{

    InventoryPage inventoryPage;
    CartPage cartPage;

    @Test
    @Tag("verifyItemDescriptionAndPriceTest")
    @Order(0)
    @DisplayName("Verify Item Description And Price")
    public void verifyItemDescriptionAndPriceTest(){

        record Item(String name, String description, String price) {}

        assertThat(loginPage.isOpen()).isTrue();
        String user = loginPage.getUsername();
        String pass = loginPage.getPassword();

        inventoryPage = loginPage.doLogin(
                Objects.requireNonNull(user, "User can not be null"),
                Objects.requireNonNull(pass, "User can not be null"));
        assertThat(inventoryPage.isOpen()).isTrue();

        int index = random.nextInt(inventoryPage.getInventoryItems().size()) ;

        //Get the item in inventory page and store data in Item object
        String key = inventoryPage.getInventoryItems().get(index).getText().split("\n", 2)[0];
        String value = inventoryPage.getInventoryItems().get(index).getText().split("\n", 2)[1];
        List<String> values = Arrays.asList(value.split("\n"));
        Item it2 = new Item(key, values.getFirst(), values.get(1));

        inventoryPage.selectProductProduct(index);

        cartPage = inventoryPage.clickShoppingCart();
        //Get the item in cart page and store data in Item object
        Item it3 = new Item(cartPage.getDescriptionList().getFirst(), cartPage.getDescriptionList().get(1), cartPage.getDescriptionList().get(2) );

       assertThat(it2.equals(it3)).isTrue();
    }

    @Test
    @Tag("verifyAlphabeticReverseSortingTest")
    @Order(1)
    @DisplayName("Verify sorting reverse by name correctly")
    public void verifyAlphabeticReverseSortingTest(){

        assertThat(loginPage.isOpen()).isTrue();
        String user = loginPage.getUsername();
        String pass = loginPage.getPassword();

        inventoryPage = loginPage.doLogin(
                Objects.requireNonNull(user, "User can not be null"),
                Objects.requireNonNull(pass, "User can not be null"));
        assertThat(inventoryPage.isOpen()).isTrue();

        List<String> generatedReversedItemNamesList = new LinkedList<>();
        List<String> reversedItemNamesListFromPage = new LinkedList<>();

        for (WebElement element : inventoryPage.getItemNameList()){
            generatedReversedItemNamesList.add(element.getText());
        }

        infoLogger("Original List from Page : " + generatedReversedItemNamesList);

        Collections.sort(generatedReversedItemNamesList);
        Collections.reverse(generatedReversedItemNamesList);

        infoLogger("Generated reversed List : " + generatedReversedItemNamesList);

        inventoryPage.setSortSelector(Constants.ORDER_BY_NAME_DESCENDING);

        for (WebElement element : inventoryPage.getItemNameList()){
            reversedItemNamesListFromPage.add(element.getText());
        }

        infoLogger("Reversed List from Page : " + reversedItemNamesListFromPage);

        assertThat(generatedReversedItemNamesList).isEqualTo(reversedItemNamesListFromPage);

    }

    @Test
    @Tag("verifyLowHighPriceSortingTest")
    @Order(2)
    @DisplayName("Verify sorting Low High by price correctly")
    public void verifyLowHighPriceSortingTest(){

        assertThat(loginPage.isOpen()).isTrue();
        String user = loginPage.getUsername();
        String pass = loginPage.getPassword();

        inventoryPage = loginPage.doLogin(
                Objects.requireNonNull(user, "User can not be null"),
                Objects.requireNonNull(pass, "User can not be null"));
        assertThat(inventoryPage.isOpen()).isTrue();

        List<BigDecimal> generatedReversedItemPriceList = new LinkedList<>();
        List<BigDecimal> reversedItemPricesListFromPage = new LinkedList<>();

        for (WebElement element : inventoryPage.getItemPriceList()){
            generatedReversedItemPriceList.add(convertStringIntoBigDecimal(element.getText()));
        }

        infoLogger("Original List from Page : " + generatedReversedItemPriceList);

        generatedReversedItemPriceList.sort(Comparator.reverseOrder());

        infoLogger("Generated reversed List : " + generatedReversedItemPriceList);

        inventoryPage.setSortSelector(Constants.ORDER_BY_PRICE_DESCENDING);

        for (WebElement element : inventoryPage.getItemPriceList()){
            reversedItemPricesListFromPage.add(convertStringIntoBigDecimal(element.getText()));
        }

        infoLogger("Reversed List from Page : " + reversedItemPricesListFromPage);

        assertThat(generatedReversedItemPriceList).isEqualTo(reversedItemPricesListFromPage);

    }

    @Test
    @Tag("verifyHighLowPriceSortingTest")
    @Order(3)
    @DisplayName("Verify sorting High Low by price correctly")
    public void verifyHighLowPriceSortingTest(){

        assertThat(loginPage.isOpen()).isTrue();
        String user = loginPage.getUsername();
        String pass = loginPage.getPassword();

        inventoryPage = loginPage.doLogin(
                Objects.requireNonNull(user, "User can not be null"),
                Objects.requireNonNull(pass, "User can not be null"));
        assertThat(inventoryPage.isOpen()).isTrue();

        List<BigDecimal> generatedOrderedItemPriceList = new LinkedList<>();
        List<BigDecimal> orderedItemPricesListFromPage = new LinkedList<>();

        for (WebElement element : inventoryPage.getItemPriceList()){
            generatedOrderedItemPriceList.add(convertStringIntoBigDecimal(element.getText()));
        }

        infoLogger("Original List from Page : " + generatedOrderedItemPriceList);

        Collections.sort(generatedOrderedItemPriceList);

        infoLogger("Generated ordered List : " + generatedOrderedItemPriceList);

        inventoryPage.setSortSelector(Constants.ORDER_BY_PRICE_ASCENDING);

        for (WebElement element : inventoryPage.getItemPriceList()){
            orderedItemPricesListFromPage.add(convertStringIntoBigDecimal(element.getText()));
        }

        infoLogger("Ordered List from Page : " + orderedItemPricesListFromPage);

        assertThat(generatedOrderedItemPriceList).isEqualTo(orderedItemPricesListFromPage);

    }

}
