package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02_AH_StepDefs {
    LoginPage loginPage;
    DashBoardPage dashBoardPage;
    String actualBorrowedBookNumber;


    @Given("the {string} on the home page AHU")
    public void the_on_the_home_page_ahu(String user) {
        loginPage = new LoginPage();
        loginPage.login(user);
        BrowserUtil.waitFor(1);


    }
    @When("the librarian gets borrowed books number AHU")
    public void the_librarian_gets_borrowed_books_number_ahu() {
        dashBoardPage = new DashBoardPage();
        actualBorrowedBookNumber = dashBoardPage.borrowedBooksNumber.getText();



    }
    @Then("borrowed books number information must match with DB AHU")
    public void borrowed_books_number_information_must_match_with_db_ahu() {
        DB_Util.runQuery("select count(*) from book_borrow where is_returned = 0");
        String expectedBorrowedBookNumber = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBorrowedBookNumber, actualBorrowedBookNumber);

    }




}
