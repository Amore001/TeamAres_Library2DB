package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

public class US03_PM_StepDefs {
    BookPage bookPage;
    LoginPage loginPage = new LoginPage();

    @Given("the {string} on the home page PM")
    public void the_on_the_home_page_PM(String user) {
        loginPage.login(user);
        BrowserUtil.waitFor(2);
    }

    @When("the user navigates to {string} page PM")
    public void the_user_navigates_to_page_PM(String moduleName) {
        bookPage=new BookPage();
        bookPage.navigateModule(moduleName);
        BrowserUtil.waitFor(1);
    }

    List<String> actualCategoryList ;
    @When("the user clicks book categories PM")
    public void the_user_clicks_book_categories_PM() {
        actualCategoryList = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        System.out.println("actualCategoryList = " + actualCategoryList);
        actualCategoryList.remove(0);
        System.out.println("------- AFTER REMOVE FIRST ONE --------");
        System.out.println("actualCategoryList = " + actualCategoryList);
    }

    @Then("verify book categories must match book_categories table from db PM")
    public void verify_book_categories_must_match_book_categories_table_from_db_PM() {

        DB_Util.runQuery("SELECT name from book_categories");
        // 3 - store this info inside the list >> EXPECTED
        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);

        //compare UI vs DB
        Assert.assertEquals(expectedCategoryList, actualCategoryList);
    }





}
