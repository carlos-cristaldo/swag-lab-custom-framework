package com.demo.swaglabs.tests;

import com.demo.swaglabs.pages.CartPage;
import com.demo.swaglabs.pages.InventoryPage;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.demo.swaglabs.utilities.Utils.hardWait;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestSuite extends BaseTest{

    InventoryPage inventoryPage;
    CartPage cartPage;

    @Test
    public void loginTest(){
        assertThat(loginPage.isOpen()).isTrue();
        String user = loginPage.getUsername();
        String pass = loginPage.getPassword();
        inventoryPage = loginPage.doLogin(
                Objects.requireNonNull(user, "User can not be null"),
                Objects.requireNonNull(pass, "User can not be null"));
        assertThat(inventoryPage.isOpen()).isTrue();
        inventoryPage.selectFirstProduct();

        softAssertions.assertThat(inventoryPage.isAtLeastOneProductSelected()).isTrue();
        softAssertions.assertThat(inventoryPage.isShoppingCartWithItems()).isTrue();
        softAssertions.assertAll();

        cartPage = inventoryPage.clickShoppingCart();
        assertThat(cartPage.isOpen()).isTrue();
        cartPage.clickCheckoutButton();
        hardWait(2000);
    }
}
