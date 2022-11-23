package com.cydeo.pages;

import com.cydeo.utility.Config;
import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "inputEmail")
    public WebElement emailBox;

    @FindBy(id = "inputPassword")
    public WebElement passwordBox;

    @FindBy(tagName = "button")
    public WebElement loginButton;



    public void login(String userType){

        String username= Config.getProperty(userType+"_username");
        String password= Config.getProperty("password");


        emailBox.sendKeys(username);
        passwordBox.sendKeys(password);
        loginButton.click();

    }




}
