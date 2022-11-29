package com.library.step_definitions;

import com.library.pages.UsersPage;
import com.library.utility.BrowserUtils;
import com.library.utility.Config;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Steps_08 {

    UsersPage usersPage= new UsersPage();
    String email;
    String inactiveStatus;
    List<String> expectedData;

    @When("the user clicks Edit User button")
    public void the_user_clicks_edit_user_button() {

        BrowserUtils.waitFor(2);
        usersPage.editUser.click();


    }
    @When("the user changes user status {string} to {string}")
    public void the_user_changes_user_status_to(String active, String inactive) {

        BrowserUtils.waitFor(2);
        inactiveStatus=inactive;


        BrowserUtils.selectByVisibleText(usersPage.statusDropdown,inactive);

        email = usersPage.email.getAttribute("value");
        System.out.println("email = " + email);

        BrowserUtils.waitFor(2);
    }
    @When("the user clicks save changes button")
    public void the_user_clicks_save_changes_button() {
        usersPage.saveChanges.click();
        System.out.println("Student "+email+" is deactivated");


    }
    @Then("{string} message should appear")
    public void message_should_appear(String expectedMessage) {

        BrowserUtils.waitUntilVisible(usersPage.toastMessage,10);
        String actualMessage= usersPage.toastMessage.getText();

        Assert.assertEquals(expectedMessage,actualMessage);


    }
    @Then("the users should see same status for related user in database")
    public void the_users_should_see_same_status_for_related_user_in_database() {

        String query = "select full_name,email,status from users where email='"+email +"'";
        DB_Util.runQuery(query);
        expectedData = DB_Util.getRowDataAsList(1);

        System.out.println(expectedData+" EXPECTED");

        Assert.assertEquals(expectedData.get(2),inactiveStatus);

    }
    @Then("the user changes current user status {string} to {string}")
    public void the_user_changes_current_user_status_to(String inactive, String active) {

        BrowserUtils.waitFor(2);
        BrowserUtils.selectByVisibleText(usersPage.userStatusDropdown,inactive);

       // BrowserUtils.waitFor(1);
        BrowserUtils.selectByVisibleText(usersPage.numberOfUserDropdown,"500");
        BrowserUtils.waitFor(2);
        usersPage.editUser(email).click();
        BrowserUtils.waitFor(2);
        BrowserUtils.selectByVisibleText(usersPage.statusDropdown,active);
        usersPage.saveChanges.click();

        System.out.println("user "+email+ " is activated");



    }

}
