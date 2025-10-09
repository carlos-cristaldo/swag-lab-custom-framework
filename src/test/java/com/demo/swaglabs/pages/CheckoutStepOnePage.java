package com.demo.swaglabs.pages;

import com.demo.swaglabs.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CheckoutStepOnePage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement inputFirstname;

    @FindBy(id = "last-name")
    private WebElement inputLastname;

    @FindBy(id = "postal-code")
    private WebElement inputZipcode;

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(factory, this);
    }

    public boolean isOpen() {
        return mainLabel.getText().equals(Constants.CHECKOUT_STEP_ONE_PAGE_MAIN_LABEL);
    }

    public void fillForm(String firstname, String lastname, String zipcode){
        enterText(inputFirstname, firstname);
        enterText(inputLastname, lastname);
        enterText(inputZipcode, zipcode);

    }

    public CheckoutStepTwoPage clickContinueToStepTwo(){
        clickElement(continueButton);
        return new CheckoutStepTwoPage(driver);
    }




}
