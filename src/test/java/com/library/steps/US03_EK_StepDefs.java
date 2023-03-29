package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US03_EK_StepDefs {
    LoginPage loginPage=new LoginPage();
    BookPage bookPage=new BookPage();
    List<String> actualResultAsString;
    @Given("the {string} on the home page EK")
    public void the_on_the_home_page_ek(String userName) {
        loginPage.login(userName);
    }
    @When("the user navigates to {string} page EK")
    public void the_user_navigates_to_page_ek(String string) {
        BrowserUtil.waitFor(3);
        bookPage.bookButton.click();
    }
    @When("the user clicks book categories EK")
    public void the_user_clicks_book_categories_ek() {
      Select bookCategory=new Select(bookPage.mainCategoryElement);
        List<WebElement> actualResult=bookCategory.getOptions();
        actualResultAsString=new ArrayList<>();
        for (WebElement each : actualResult) {
            actualResultAsString.add(each.getText());
        }

        actualResultAsString.remove(0);
        System.out.println(actualResultAsString);


    }

    @Then("verify book categories must match book_categories table from db EK")
    public void verify_book_categories_must_match_book_categories_table_from_db_ek() {
        String query="select name from book_categories";
        DB_Util.runQuery(query);
        List<String> expectedResult=DB_Util.getColumnDataAsList(1);
        System.out.println(expectedResult);
        Assert.assertEquals(expectedResult,actualResultAsString);
    }
}
