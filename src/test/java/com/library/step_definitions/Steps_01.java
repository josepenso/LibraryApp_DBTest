package com.library.step_definitions;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtils;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Steps_01 {

    LoginPage loginPage =new LoginPage();
    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginPage.login(user);
        BrowserUtils.waitFor(3);

    }
    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);


    }

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DB_Util.createConnection();
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        String query= "select id from users";

        DB_Util.runQuery(query);
    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {

        List<String> users = DB_Util.getColumnDataAsList(1);
        Set<String> uniqueUsers= new HashSet<>(users);
        Assert.assertEquals(uniqueUsers.size(),users.size());


    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        String query="select * from users";
        DB_Util.runQuery(query);

    }
    @Then("verify the below columns are listed in the result")
    public void verify_the_below_columns_are_listed_in_the_result(List<String> expectedColumnNames) {

        List<String> actualColumnNames= DB_Util.getAllColumnNamesAsList();

        for (int i=0;i<expectedColumnNames.size();i++){

            Assert.assertEquals(expectedColumnNames.get(i),actualColumnNames.get(i));
        }


        System.out.println("Actual column names = "+actualColumnNames);
        System.out.println("Expected column names="+expectedColumnNames);


    }




}
