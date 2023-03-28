package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

public class US03_PM_StepDefs {

    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    List<String> actualCategoryList;


    @Given("the {string} on the home page PM")
    public void the_on_the_home_page_PM(String user) {
        loginPage.login(user);
        BrowserUtil.waitFor(2);
    }
    @When("the user navigates to {string} page PM")
    public void the_user_navigates_to_page_PM(String moduleName) {
        new BookPage().navigateModule(moduleName);
    }

    @When("the user clicks book categories PM")
    public void the_user_clicks_book_categories_PM() {
        bookPage.mainCategoryElement.click();
    }

    @Then("verify book categories must match book_categories table from db PM")
    public void verify_book_categories_must_match_book_categories_table_from_db_PM() {
        //first step: create a query String to store our sql query
        String query = "SELECT name from book_categories";
        // 2 - run query to get data from db
        DB_Util.runQuery(query);
        // 3 - store this info inside the list >> EXPECTED
        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);

        //4 - Get my ActualCategoryList
        actualCategoryList = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        //                                  method              WE element
        actualCategoryList.remove(0);
        //delete first option >>> ALL <<< so, the assert will match

        //compare UI vs DB
        Assert.assertEquals(expectedCategoryList, actualCategoryList);
    }





}
