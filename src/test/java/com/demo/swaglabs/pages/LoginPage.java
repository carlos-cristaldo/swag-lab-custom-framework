package com.demo.swaglabs.pages;

import com.demo.swaglabs.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.Arrays;

public class LoginPage extends BasePage{

    @FindBy(className = "login_logo")
    private WebElement mainTitle;

    @FindBy(id = "login_credentials")
    private WebElement loginUsername;

    @FindBy(id = "user-name")
    private WebElement inputUsername;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@data-test=\"login-password\"]")
    private WebElement loginPassword;

    public LoginPage(WebDriver driver) {
        super(driver);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(factory, this);
    }

    public boolean isOpen(){
        return mainTitle.getText().equals(Constants.LOGIN_PAGE_MAIN_TITLE);
    }

    public String getUsername(){
        String[] usernames = loginUsername.getText().split("\n");
        return Arrays.asList(usernames).get(1);
    }

    public String getPassword(){
        String[] passwords = loginPassword.getText().split("\n");
        return Arrays.asList(passwords).get(1);
    }

    public InventoryPage doLogin(String user, String pass){
        enterText(inputUsername, user);
        enterText(inputPassword, pass);
        clickElement(loginButton);
        return new InventoryPage(driver);
    }


}
