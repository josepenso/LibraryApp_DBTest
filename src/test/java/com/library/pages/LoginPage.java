package com.library.pages;

import com.library.utility.Config;
import com.library.utility.Driver;
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

        if (userType.equalsIgnoreCase("librarian")) {
            String username = Config.getProperty(userType + "_username");
            String password = Config.getProperty("password");

            emailBox.sendKeys(username);
            passwordBox.sendKeys(password);
            loginButton.click();
        }if (userType.equalsIgnoreCase("student")){
            String studentUsername = Config.getProperty(userType + "_username");
            String studentPassword = Config.getProperty(userType+"_password");

            emailBox.sendKeys(studentUsername);
            passwordBox.sendKeys(studentPassword);
            loginButton.click();

        }

    }




}
