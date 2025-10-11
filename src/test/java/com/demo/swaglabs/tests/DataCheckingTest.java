package com.demo.swaglabs.tests;

import com.demo.swaglabs.pages.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DataCheckingTest extends BaseTest{

    InventoryPage inventoryPage;
    CartPage cartPage;

    @Test
    @Tag("verifyItemDescriptionAndPriceTest")
    @Order(0)
    @DisplayName("Verify Item Description And Price Test")
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

}
