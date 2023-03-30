package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.List;

public class US07_AS_StepDefs {


    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    List<String> borrowedBooks = new ArrayList<>();



    String book_Name = "";


    @Given("the {string} on the home page AS")
    public void the_on_the_home_page_as(String string) {

        loginPage.login(string);

    }
    @Given("the user navigates to {string} page AS")
    public void the_user_navigates_to_page_as(String string) {

        bookPage.navigateModule(string);
        BrowserUtil.waitFor(2);
    }
    @Given("the user searches for {string} book AS")
    public void the_user_searches_for_book_as(String name) {
        book_Name = name;

        // student searches the book name
        bookPage.search.sendKeys(name);
        BrowserUtil.waitFor(2);
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

        BrowserUtil.waitFor(2);

        List<String> UIlistOFBookNames = BrowserUtil.getElementsText(borrowedBooksPage.allBorrowedBooksName);

        /*
        for (WebElement webElement : borrowedBooksPage.allBorrowedBooksName) {
            String eachBookName = webElement.getText();
            borrowedBooks.add(eachBookName);
        }


        System.out.println(borrowedBooks);

         */

        Assert.assertTrue(UIlistOFBookNames.contains(book_Name));




    }
    @Then("verify logged student has same book in database AS")
    public void verify_logged_student_has_same_book_in_database_as() {

        DB_Util.runQuery("select full_name,b.name,bb.borrowed_date from users u\n" +
                "inner join book_borrow bb on u.id = bb.user_id\n" +
                "inner join books b on bb.book_id = b.id\n" +
                "where full_name='Test Student 5'\n" +
                "order by 3 desc");


       // String lastAddedBookNameInDB = DB_Util.getCellValue(1, 2);

        List<String> BookNamesInDB = DB_Util.getColumnDataAsList(2);
        System.out.println(BookNamesInDB);

        Assert.assertTrue(BookNamesInDB.contains(book_Name));


        //use below code to return the book so you can retest it. Not stable test!!!!
        //you need to retrun the book back so above steps can work and click button, borrowbook.

        Driver.getDriver().navigate().refresh();

        String locatorToReturnBook = "(//td[2][.='Self Confident for AS'])[8]/../td/a";
        WebElement ReturnBook = Driver.getDriver().findElement(By.xpath(locatorToReturnBook));

        ReturnBook.click();







    }

}
