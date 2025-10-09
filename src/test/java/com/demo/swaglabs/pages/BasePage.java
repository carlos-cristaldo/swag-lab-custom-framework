package com.demo.swaglabs.pages;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    @FindBy(xpath = "//span[@data-test=\"title\"]")
    protected WebElement mainLabel;

    @FindBy(id = "continue")
    protected WebElement continueButton;

    public BasePage(WebDriver driver){
        this.driver=driver;
    }

    protected void enterText(WebElement element, String text){
        fluentWait().until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    protected void clickElement(WebElement element){
        fluentWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected Wait<WebDriver> fluentWait(){
        return new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(2))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoring(ElementNotInteractableException.class);
    }


}
