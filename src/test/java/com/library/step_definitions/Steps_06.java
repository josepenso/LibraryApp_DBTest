package com.library.step_definitions;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtils;
import com.library.utility.Config;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Steps_06 {
    BookPage bookPage= new BookPage();
    List<String> actualBookInfoAdded = new ArrayList<>();
    List<String> expectedBookInfo= new ArrayList<>();
    String isbnCode;

    String numOfRecordsToSee = Config.getProperty("numOfRecordsToSee");

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        BrowserUtils.waitFor(3);

        bookPage.addNewBookBtn();

    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookName) {
        bookPage.enterBookName(bookName);
        expectedBookInfo.add(bookName);
    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {
        bookPage.enterISBN(isbn);
        expectedBookInfo.add(isbn);
        isbnCode=isbn;

    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        bookPage.enterYear(year);
        expectedBookInfo.add(year);

    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {

        bookPage.enterAuthor(author);
        expectedBookInfo.add(author);

    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {
        bookPage.selectBookCategory(category);

    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {


        bookPage.saveNewBookEntry();
      BrowserUtils.waitFor(4);
    }
    @Then("the librarian verify new book by {string}")
    public void the_librarian_verify_new_book_by(String bookName) {

        System.out.println("bookName = " + bookName);
        BrowserUtils.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtils.waitFor(3);
        bookPage.numberOfRecordsDD.sendKeys(numOfRecordsToSee);
        BrowserUtils.waitFor(3);
        BrowserUtils.waitForClickablility(bookPage.getBookInfo(isbnCode), 5).click();

        BrowserUtils.waitFor(2);
        actualBookInfoAdded.add(bookPage.bookName.getAttribute("value"));
        actualBookInfoAdded.add(bookPage.isbn.getAttribute("value"));
        actualBookInfoAdded.add(bookPage.year.getAttribute("value"));
        actualBookInfoAdded.add(bookPage.author.getAttribute("value"));


        System.out.println(expectedBookInfo+" EXPECTED");
        System.out.println(actualBookInfoAdded+" ACTUAL");
        Assert.assertEquals(expectedBookInfo,actualBookInfoAdded);
    }
    @Then("the librarian verify new book from database by {string}")
    public void the_librarian_verify_new_book_from_database_by(String bookName)  {

        DB_Util.runQuery("select name,isbn,year,author from books where name like '"+bookName+"' and isbn="+isbnCode+" order by isbn desc");
        List<String> expectedBookInfoStored= DB_Util.getRowDataAsList(1);

        System.out.println(expectedBookInfoStored+" EXPECTED");
        System.out.println(actualBookInfoAdded+" ACTUAL");

        Assert.assertEquals(expectedBookInfoStored,actualBookInfoAdded);


    }




}
