package com.demo.swaglabs.pages;

import com.demo.swaglabs.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.Arrays;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[@class=\"cart_item_label\"]")
    private WebElement itemDescription;

    public CartPage(WebDriver driver) {
        super(driver);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(factory, this);
    }

    public boolean isOpen() {
        return mainLabel.getText().equals(Constants.CART_PAGE_MAIN_LABEL);
    }

    public CheckoutStepOnePage clickCheckoutButton(){
        clickElement(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }

    public List<String> getDescriptionList(){
        return  Arrays.asList(itemDescription.getText().split("\n"));
    }

}
