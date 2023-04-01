package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.pages.UsersPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class US04_AN_StepDefs {

    LoginPage loginPage= new LoginPage();
    DashBoardPage dashBoardPage= new DashBoardPage();
    BookPage bookPage = new BookPage();
    UsersPage usersPage = new UsersPage();
    String bookName;


    @Given("the {string} on the home page AN")
    public void the_on_the_home_page_an(String userType) {
        loginPage.login(userType);
        BrowserUtil.waitFor(3);
    }
    @Given("the user navigates to {string} page AN")
    public void the_user_navigates_to_page_an(String moduleName) {
        dashBoardPage.navigateModule(moduleName);

    }
    @When("the user searches for {string} book AN")
    public void the_user_searches_for_book_an(String bookName) {
        BrowserUtil.waitForVisibility(bookPage.search, 5).sendKeys(bookName);
        System.out.println("bookName = " + bookName);
        this.bookName = bookName;


    }
    @When("the user clicks edit book button AN")
    public void the_user_clicks_edit_book_button_an() {

        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();
        BrowserUtil.waitFor(3);


    }
    @Then("book information must match the Database AN")
    public void book_information_must_match_the_database_an() {
        List<String> actualBookInfo = new ArrayList<>();

        String actualName = bookPage.bookName.getAttribute("value");
        String actualAuthor = bookPage.author.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDescription = bookPage.description.getAttribute("value");

        actualBookInfo.addAll(Arrays.asList(actualName,actualAuthor,actualISBN,actualYear,actualDescription));

        System.out.println("actualBookInfo = " + actualBookInfo);


        String query = "select b.name,b.author, b.isbn, b.year, b.description from books b inner join\n" +
                "book_categories bc on b.book_category_id = bc.id\n" +
                "where b.name = '"+bookName+"'";

        DB_Util.runQuery(query);

        List<String> expectedBookInfo = DB_Util.getRowDataAsList(1);
        System.out.println("rowDataAsList = " + expectedBookInfo);

        Assert.assertEquals(actualBookInfo, expectedBookInfo);
        
    }

}
