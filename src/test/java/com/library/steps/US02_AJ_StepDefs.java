package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class US02_AJ_StepDefs {

    LoginPage loginPage;
    DashBoardPage dashBoardPage;

    String actualBorrowedBookNumbers;
    @Given("the {string} on the home page AH")
    public void the_on_the_home_page_ah(String user) {
        loginPage = new LoginPage();
        loginPage.login(user);
    }
    @When("the librarian gets borrowed books number AH")
    public void the_librarian_gets_borrowed_books_number_ah() {
        dashBoardPage = new DashBoardPage();
        BrowserUtil.waitForVisibility(dashBoardPage.usersNumber,3);
        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);
        //System.out.println("dashBoardPage.getModuleCount(\" Borrowed Books\") = " + dashBoardPag
    }
    @Then("borrowed books number information must match with DB AH")
    public void borrowed_books_number_information_must_match_with_db_ah() {
        String query = "select count(*) from book_borrow where is_returned = 0";
        DB_Util.runQuery(query);
        String expectedBorrowedBookNumbers= DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowedBookNumbers = " + expectedBorrowedBookNumbers);
        Assert.assertEquals(expectedBorrowedBookNumbers,actualBorrowedBookNumbers);
    }

}
