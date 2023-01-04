package com.library.step_definitions;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtils;
import com.library.utility.Config;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class Steps_07 {

    BookPage studentBook= new BookPage();
    String bookNameInput;
    String studentId= Config.getProperty("student_id");

    @Given("the user search book {string}")
    public void the_user_search_book(String bookName) {
        bookNameInput=bookName;
        studentBook.search.sendKeys(bookName);
        BrowserUtils.waitFor(2);




    }

    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {

        studentBook.borrowByBtn.click();
        BrowserUtils.waitFor(2);
        studentBook.borrowBook();


    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_borrowing_books_page(String moduleName) {

        studentBook.navigateModule(moduleName);






    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

        String query ="select full_name,b.name,bb.borrowed_date from users u inner join book_borrow bb on u.id = bb.user_id inner join books b on bb.book_id= b.id where full_name='"+studentId+"' and name = '"+bookNameInput+"' order by 3 desc";
        DB_Util.runQuery(query);

        List<String> expectedData = DB_Util.getRowDataAsList(1);

        System.out.println(expectedData+" EXPECTED");
        System.out.println(studentId+" ACTUAL");

        Assert.assertEquals(expectedData.get(0),studentId);


    }


}
