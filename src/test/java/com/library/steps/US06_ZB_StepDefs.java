package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US06_ZB_StepDefs {

    LoginPage loginPage;
    BookPage bookPage;
    String expectedBookName;
    String year;
    String author;


    @Given("the {string} on the home page BZ")
    public void the_on_the_home_page_bz(String user) {
        loginPage = new LoginPage();
        loginPage.login(user);

    }

    @Given("the user navigates to {string} page BZ")
    public void the_user_navigates_to_page_bz(String pageName) {
        bookPage = new BookPage();
        bookPage.navigateModule(pageName);


    }

    @When("the librarian click to add book BZ")
    public void the_librarian_click_to_add_book_bz() {
        bookPage.addBook.click();


    }

    @When("the librarian enter book name {string} BZ")
    public void the_librarian_enter_book_name_bz(String bookName) {
        bookPage.bookName.sendKeys(bookName);
        expectedBookName = bookName;

    }

    @When("the librarian enter ISBN {string} BZ")
    public void the_librarian_enter_isbn_bz(String ISBN) {
        bookPage.isbn.sendKeys(ISBN);

    }

    @When("the librarian enter year {string} BZ")
    public void the_librarian_enter_year_bz(String year) {
        bookPage.year.sendKeys(year);
        this.year = year;

    }

    @When("the librarian enter author {string} BZ")
    public void the_librarian_enter_author_bz(String author) {
        bookPage.author.sendKeys(author);
        this.author = author;


    }

    @When("the librarian choose the book category {string} BZ")
    public void the_librarian_choose_the_book_category_bz(String bookCategory) {
       // BrowserUtil.waitFor(1);
        BrowserUtil.selectOptionDropdown(bookPage.categoryDropdown, bookCategory);
        BrowserUtil.waitFor(1);

    }

    @When("the librarian click to save changes BZ")
    public void the_librarian_click_to_save_changes_bz() {
        bookPage.saveChanges.click();

    }

    @Then("verify {string} message is displayed BZ")
    public void verify_message_is_displayed_bz(String expectedMessage) {
        String actualMessage = bookPage.toastMessage.getText();
        Assert.assertTrue(bookPage.toastMessage.isDisplayed());

    }

    @Then("verify {string} information must match with DB BZ")
    public void verify_information_must_match_with_db_bz(String expectedBookName) {
        DB_Util.runQuery("select name, author, isbn year from books\n" +
                "where name = '"+ expectedBookName+"'");
        Assert.assertEquals(expectedBookName, DB_Util.getFirstRowFirstColumn());

    }
}
