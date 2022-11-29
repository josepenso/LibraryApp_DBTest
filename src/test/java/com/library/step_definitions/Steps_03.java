package com.library.step_definitions;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtils;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class Steps_03 {
    BookPage bookPage = new BookPage();
    List<String> actualCategoryList;

    @When("the user open book {string}")
    public void i_open_book(String bookName) {

        System.out.println("bookName = " + bookName);
        BrowserUtils.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtils.waitFor(3);
        BrowserUtils.waitForClickablility(bookPage.editBook(bookName), 5).click();

    }

    @When("the user takes all book categories in UI")
    public void i_take_all_book_categories_in_ui() {
        BrowserUtils.waitFor(3);
      actualCategoryList= BrowserUtils.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategoryList.remove(0);

    }
    @When("the user execute query to get book categories from db")
    public void i_execute_query_to_get_book_categories() {
        String query= "select count(*) from book_borrow where is_returned=0";

        DB_Util.runQuery(query);

    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db(List<String> expectedCategoryList) {

        Assert.assertEquals(actualCategoryList,expectedCategoryList);


    }

    @Then("book information must match the database for {string}")
    public void book_information_must_match_the_database_for(String bookName) {
        List<String> actualBookInfo = new ArrayList<>();
        BrowserUtils.waitFor(2);
        actualBookInfo.add(bookPage.bookName.getAttribute("value"));
        actualBookInfo.add(bookPage.isbn.getAttribute("value"));
        actualBookInfo.add(bookPage.author.getAttribute("value"));
        actualBookInfo.add(bookPage.description.getAttribute("value"));
        actualBookInfo.add(bookPage.year.getAttribute("value"));

        String query = "select name,isbn,author,description,year from books where name='" + bookName + "'";
        DB_Util.runQuery(query);
        //store nformation


        List<String> bookInfo = DB_Util.getRowDataAsList(1);

        System.out.println(actualBookInfo);
        System.out.println(bookInfo);

        for (int i = 0; i < bookInfo.size(); i++) {
            Assert.assertEquals(bookInfo.get(i), actualBookInfo.get(i));
        }


    }

    }
