package com.demo.swaglabs.pages;

import com.demo.swaglabs.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrderCompletePage extends BasePage {

    @FindBy(xpath = "//h2[@data-test=\"complete-header\"]")
    private WebElement thankYouHeader;

    @FindBy(xpath = "//div[@data-test=\"complete-text\"]")
    private WebElement dispatchHeader;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public OrderCompletePage(WebDriver driver) {
        super(driver);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(factory, this);
    }

    public boolean isOpen() {
        return mainLabel.getText().equals(Constants.ORDER_COMPLETE_PAGE_MAIN_LABEL);
    }

    public boolean thankYouMessageIsDisplayed() { return thankYouHeader.getText().equals(Constants.THANK_YOU_MESSAGE); }

    public boolean ponyDispatchedMessageIsDisplayed() { return dispatchHeader.getText().equals(Constants.DISPATCH_MESSAGE); }

    public boolean backButtonIsDisplayed() { return backHomeButton.isDisplayed(); }





}
