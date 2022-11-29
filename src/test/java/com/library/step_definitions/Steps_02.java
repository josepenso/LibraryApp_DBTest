package com.library.step_definitions;

import com.library.pages.DashBoardPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Steps_02 {
    String actualBorrowedBookNumbers;
    DashBoardPage dashBoardPage=new DashBoardPage();

    @When("user take borrowed books number")
    public void user_take_borrowed_books_number() {
        actualBorrowedBookNumbers= dashBoardPage.borrowedBooksNumber.getText();


    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        String query= "select count(*) from book_borrow where is_returned=0";
        DB_Util.runQuery(query);
        String expectedBorrowBooksNumbers= DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedBorrowBooksNumbers,actualBorrowedBookNumbers);

    }

}
