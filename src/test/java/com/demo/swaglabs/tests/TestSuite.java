package com.demo.swaglabs.tests;

import com.demo.swaglabs.pages.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static com.demo.swaglabs.utilities.Utils.hardWait;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestSuite extends BaseTest{

    Faker faker = new Faker();

    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutStepOnePage checkoutStepOnePage;
    CheckoutStepTwoPage checkoutStepTwoPage;
    OrderCompletePage orderCompletePage;


    @Test
    public void happyPathTest(){
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

        checkoutStepOnePage = cartPage.clickCheckoutButton();
        assertThat(checkoutStepOnePage.isOpen()).isTrue();

        checkoutStepOnePage.fillForm(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().zipCode()
        );

        checkoutStepTwoPage = checkoutStepOnePage.clickContinueToStepTwo();
        assertThat(checkoutStepTwoPage.isOpen()).isTrue();

        orderCompletePage = checkoutStepTwoPage.clickFinishButton();

        softAssertions.assertThat(orderCompletePage.isOpen()).isTrue();
        softAssertions.assertThat(orderCompletePage.thankYouMessageIsDisplayed()).isTrue();
        softAssertions.assertThat(orderCompletePage.ponyDispatchedMessageIsDisplayed()).isTrue();
        softAssertions.assertThat(orderCompletePage.backButtonIsDisplayed()).isTrue();
        softAssertions.assertAll();

        hardWait(2000);
    }
}
