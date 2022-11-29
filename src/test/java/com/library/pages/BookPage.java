package com.library.pages;

import com.library.utility.BrowserUtils;
import com.library.utility.Config;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookPage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    @FindBy(name = "name")
    public WebElement bookName;


    @FindBy(xpath = "(//input[@type='text'])[4]")
    public WebElement author;


    @FindBy(name = "year")
    public WebElement year;

    @FindBy(name = "isbn")
    public WebElement isbn;

    @FindBy(id = "description")
    public WebElement description;

    @FindBy(xpath = "//select[@id='book_group_id']")
    public WebElement bookCategory;

    @FindBy(css = ".add_book_btn")
    public WebElement addBookBtn;

    @FindBy(css = "button[type='submit']")
    public WebElement submitBtn;

    @FindAll(@FindBy(css = "tbody tr td"))
    public List<WebElement> bookListDetails;

    @FindAll(@FindBy(css = "tbody tr"))
    public List<WebElement> bookList;

    @FindBy(css = "select[name='tbl_books_length']")
    public WebElement numberOfRecordsDD;

    @FindBy(xpath = "//th[contains(text(),'Borrowed By')]")
    public WebElement borrowByBtn;

    @FindAll(@FindBy(css = "tbody tr td a"))
    private List<WebElement> borrowBookBtns;

    @FindBy(css = "div[class='toast-message']")
    private WebElement bookBorrowedMsg;

    @FindAll(@FindBy(css = "thead th"))
    private List<WebElement> tableHeader;



    public WebElement editBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }
    public WebElement getNumberOfRecordsDD() {
        return numberOfRecordsDD;
    }
    public void addNewBookBtn() {
        addBookBtn.click();
        BrowserUtils.waitUntilClickable(submitBtn,10);
    }

    public void enterBookName(String book){
        bookName.sendKeys(book);

    }
    public void enterISBN(String inputIsbn){
        isbn.sendKeys(inputIsbn);
    }

    public void enterYear(String inputYear){

        year.sendKeys(inputYear);

    }

    public void enterAuthor(String inputAuthor){
        author.sendKeys(inputAuthor);
    }
    public void selectBookCategory(String category) {
        Select select = new Select(bookCategory);
        select.selectByVisibleText(category);
    }
    public void saveNewBookEntry() {
        submitBtn.click();
       BrowserUtils.waitFor(2);
    }




    public void borrowBook() {
        for (WebElement eachBtn : borrowBookBtns) {
            if (!eachBtn.getAttribute("class").endsWith("disabled")) {
                eachBtn.click();
                BrowserUtils.sleep(1);
                break;
            }
        }
        Assert.assertTrue(bookBorrowedMsg.isDisplayed());
    }

    public List<Map<String, String>> getVisibleBooks() {
        List<Map<String, String>> allBooks = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++) {
            Map<String, String> eachBookInfo = new LinkedHashMap<>();
            for (int k = 1; k < bookListDetails.size(); k++) {
                eachBookInfo.put(tableHeader.get(k).getText(), bookListDetails.get(k).getText());
            }
            allBooks.add(eachBookInfo);
        }
        return allBooks;
    }



}
