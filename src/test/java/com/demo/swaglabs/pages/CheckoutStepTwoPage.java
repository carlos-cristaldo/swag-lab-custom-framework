package com.demo.swaglabs.pages;

import com.demo.swaglabs.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CheckoutStepTwoPage extends BasePage {

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(factory, this);
    }

    public boolean isOpen() {
        return mainLabel.getText().equals(Constants.CHECKOUT_STEP_TWO_PAGE_MAIN_LABEL);
    }

    public OrderCompletePage clickFinishButton(){
        clickElement(finishButton);
        return new OrderCompletePage(driver);
    }




}
