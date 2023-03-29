package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class US07_AS_StepDefs {


    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    List<String> borrowedBooks = new ArrayList<>();

    String book_Name = "Self Confident";
    String moduleName = "Borrowing Books";

    @Given("the {string} on the home page AS")
    public void the_on_the_home_page_as(String string) {

        loginPage.login(string);
        BrowserUtil.waitFor(3);
    }
    @Given("the user navigates to {string} page AS")
    public void the_user_navigates_to_page_as(String string) {

        bookPage.navigateModule(string);
        BrowserUtil.waitFor(3);
    }
    @Given("the user searches for {string} book AS")
    public void the_user_searches_for_book_as(String string) {
        // student searches the book name
        bookPage.search.sendKeys(string);
        BrowserUtil.waitFor(3);
    }
    @When("the user clicks Borrow Book AS")
    public void the_user_clicks_borrow_book_as() {


        WebElement borrowBook = bookPage.borrowBook(book_Name);

        borrowBook.click();

// student can see the message " book borrowed "
        Assert.assertTrue(bookPage.toastMessage.isDisplayed());

    }
    @Then("verify that book is shown in {string} page AS")
    public void verify_that_book_is_shown_in_page_as(String string) {

        bookPage.navigateModule(string);

        BrowserUtil.waitFor(3);

        for (WebElement webElement : borrowedBooksPage.allBorrowedBooksName) {
            String eachBookName = webElement.getText();
            borrowedBooks.add(eachBookName);
        }

        System.out.println(borrowedBooks);

        Assert.assertTrue(borrowedBooks.contains(book_Name));


        BrowserUtil.waitFor(3);

    }
    @Then("verify logged student has same book in database AS")
    public void verify_logged_student_has_same_book_in_database_as() {

        DB_Util.runQuery("select full_name,b.name,bb.borrowed_date from users u\n" +
                "inner join book_borrow bb on u.id = bb.user_id\n" +
                "inner join books b on bb.book_id = b.id\n" +
                "where full_name='Test Student 5'\n" +
                "order by 3 desc");


        System.out.println(DB_Util.getCellValue(1, 2));

        List<String> BookNamesInDB = DB_Util.getColumnDataAsList(2);
        System.out.println(BookNamesInDB);
    }

}
