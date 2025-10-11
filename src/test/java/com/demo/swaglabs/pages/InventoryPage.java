package com.demo.swaglabs.pages;

import com.demo.swaglabs.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(xpath = "//span[@data-test=\"shopping-cart-badge\"]")
    private WebElement shoppingCartBadge;

    @FindBy(xpath = "//a[@data-test=\"shopping-cart-link\"]")
    private WebElement shoppingCartLink;

    @FindBy(xpath = "//button[text()=\"Add to cart\"]")
    private List<WebElement> addToCartButtonList;

    @FindBy(xpath = "//button[text()=\"Remove\"]")
    private List<WebElement> removeButtonList;

    @FindBy(xpath="//div[@data-test=\"inventory-item\"]")
    private List<WebElement> inventoryItemsList;


    public InventoryPage(WebDriver driver) {
        super(driver);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(factory, this);
    }

    public boolean isOpen() {
        return mainLabel.getText().equals(Constants.PRODUCTS_PAGE_MAIN_LABEL);
    }

    public void selectFirstProduct(){
        clickElement(addToCartButtonList.getFirst());
    }

    public void selectProductProduct(int index){
        clickElement(addToCartButtonList.get(index));
    }

    public boolean isAtLeastOneProductSelected(){
        return !removeButtonList.isEmpty();
    }

    public boolean isShoppingCartWithItems(){
        return shoppingCartBadge.isDisplayed() && !shoppingCartBadge.getText().equals("0");
    }

    public CartPage clickShoppingCart(){
        clickElement(shoppingCartLink);
        return new CartPage(driver);
    }

    public List<WebElement> getInventoryItems(){
        return inventoryItemsList;
    }

}
