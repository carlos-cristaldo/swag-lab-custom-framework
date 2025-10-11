package com.demo.swaglabs.tests;

import com.demo.swaglabs.pages.*;
import com.demo.swaglabs.utilities.Constants;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest{

    Faker faker = new Faker();

    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutStepOnePage checkoutStepOnePage;
    CheckoutStepTwoPage checkoutStepTwoPage;
    OrderCompletePage orderCompletePage;


    @Test
    @Tag("HappyPath")
    @Order(0)
    @DisplayName("Happy Path")
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
    }

    @Test
    @Tag("wrongCredentialsTest")
    @Order(1)
    @DisplayName("Wrong Credentials Test")
    public void wrongCredentialsTest(){
        List<String> trace = new ArrayList<>(
                List.of(
                        Thread.currentThread().getStackTrace()[1].getMethodName(),
                        Objects.requireNonNullElseGet(getClass().getEnclosingClass(), this::getClass).getSimpleName())
        );

        assertThat(loginPage.isOpen()).isTrue();
        String user = Constants.INVALID_USER;
        String pass = Constants.INVALID_PASSWORD;

        loginPage.doLogin(user,pass);

        trace.add("MESSAGE: \"" + loginPage.getErrorMessage() + "\"");
        errorLogger(trace);

        softAssertions.assertThat(loginPage.getErrorMessage()).isEqualTo(Constants.INVALID_USER_AND_PASSWORD_MESSAGE);
        softAssertions.assertThat(loginPage.isErrorMessageDisplayed()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    @Tag("wrongPasswordTest")
    @Order(2)
    @DisplayName("Wrong Password Test")
    public void wrongPasswordTest(){
        List<String> trace = new ArrayList<>(
                List.of(
                        Thread.currentThread().getStackTrace()[1].getMethodName(),
                        Objects.requireNonNullElseGet(getClass().getEnclosingClass(), this::getClass).getSimpleName())
        );

        assertThat(loginPage.isOpen()).isTrue();
        String user = loginPage.getUsername();
        String pass = Constants.INVALID_PASSWORD;

        loginPage.doLogin(user,pass);

        trace.add("MESSAGE: \"" + loginPage.getErrorMessage() + "\"");
        errorLogger(trace);

        softAssertions.assertThat(loginPage.getErrorMessage()).isEqualTo(Constants.INVALID_USER_AND_PASSWORD_MESSAGE);
        softAssertions.assertThat(loginPage.isErrorMessageDisplayed()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    @Tag("wrongUserTest")
    @Order(3)
    @DisplayName("Wrong User Test")
    public void wrongUserTest(){
        List<String> trace = new ArrayList<>(
                List.of(
                        Thread.currentThread().getStackTrace()[1].getMethodName(),
                        Objects.requireNonNullElseGet(getClass().getEnclosingClass(), this::getClass).getSimpleName())
        );

        assertThat(loginPage.isOpen()).isTrue();
        String user = Constants.INVALID_USER;
        String pass = loginPage.getPassword();

        loginPage.doLogin(user,pass);

        trace.add("MESSAGE: \"" + loginPage.getErrorMessage() + "\"");
        errorLogger(trace);

        softAssertions.assertThat(loginPage.getErrorMessage()).isEqualTo(Constants.INVALID_USER_AND_PASSWORD_MESSAGE);
        softAssertions.assertThat(loginPage.isErrorMessageDisplayed()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    @Tag("emptyCredentialsTest")
    @Order(4)
    @DisplayName("Empty Credentials Test")
    public void emptyCredentialsTest(){
        List<String> trace = new ArrayList<>(
                List.of(
                        Thread.currentThread().getStackTrace()[1].getMethodName(),
                        Objects.requireNonNullElseGet(getClass().getEnclosingClass(), this::getClass).getSimpleName())
        );

        assertThat(loginPage.isOpen()).isTrue();
        String user = "";
        String pass = "";

        loginPage.doLogin(user,pass);

        trace.add("MESSAGE: \"" + loginPage.getErrorMessage() + "\"");
        errorLogger(trace);

        softAssertions.assertThat(loginPage.getErrorMessage()).isEqualTo(Constants.EMPTY_USER_MESSAGE);
        softAssertions.assertThat(loginPage.isErrorMessageDisplayed()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    @Tag("emptyUserTest")
    @Order(5)
    @DisplayName("Empty User Test")
    public void emptyUserTest(){
        List<String> trace = new ArrayList<>(
                List.of(
                        Thread.currentThread().getStackTrace()[1].getMethodName(),
                        Objects.requireNonNullElseGet(getClass().getEnclosingClass(), this::getClass).getSimpleName())
        );

        assertThat(loginPage.isOpen()).isTrue();
        String user = "";
        String pass = loginPage.getPassword();

        loginPage.doLogin(user,pass);

        trace.add("MESSAGE: \"" + loginPage.getErrorMessage() + "\"");
        errorLogger(trace);

        softAssertions.assertThat(loginPage.getErrorMessage()).isEqualTo(Constants.EMPTY_USER_MESSAGE);
        softAssertions.assertThat(loginPage.isErrorMessageDisplayed()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    @Tag("emptyPasswordTest")
    @Order(6)
    @DisplayName("Login with empty password")
    public void emptyPasswordTest(){
        List<String> trace = new ArrayList<>(
                List.of(
                        Thread.currentThread().getStackTrace()[1].getMethodName(),
                        Objects.requireNonNullElseGet(getClass().getEnclosingClass(), this::getClass).getSimpleName())
        );

        assertThat(loginPage.isOpen()).isTrue();
        String user = loginPage.getUsername();
        String pass = "";

        loginPage.doLogin(user,pass);

        trace.add("MESSAGE: \"" + loginPage.getErrorMessage() + "\"");
        errorLogger(trace);

        softAssertions.assertThat(loginPage.getErrorMessage()).isEqualTo(Constants.EMPTY_PASSWORD_MESSAGE);
        softAssertions.assertThat(loginPage.isErrorMessageDisplayed()).isTrue();
        softAssertions.assertAll();
    }

}
