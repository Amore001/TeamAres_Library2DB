package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US01_AG_StepDefs {
    String actualUserCount;

    @Given("establish the database connection AG")
    public void establishTheDatabaseConnectionAG() {

        System.out.println("**********************************************");
        System.out.println("*** CONNECTION WILL BE DONE WITH HOOK CLASS***");
        System.out.println("**********************************************");

    }

    @When("Execute query to get all IDs from users AG")
    public void executeQueryToGetAllIDsFromUsersAG() {
        String query = "select count(id) from users"; // 1855
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualUserCount = " + actualUserCount);

    }

    @Then("verify all users has unique ID AG")
    public void verifyAllUsersHasUniqueIDAG() {

        String query = "select count(distinct id) from users";
        DB_Util.runQuery(query);
        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedUserCount = " + expectedUserCount);


        Assert.assertEquals(expectedUserCount, actualUserCount);


        System.out.println("**********************************************");
        System.out.println("*** DESTROY  WILL BE DONE WITH HOOK CLASS***");
        System.out.println("**********************************************");


    }

    List<String> actualList;



    @When("Execute query to get all columns AG")
    public void executeQueryToGetAllColumnsAG() {
        DB_Util.runQuery("select * from users");
        actualList = DB_Util.getAllColumnNamesAsList();
        System.out.println("actualList = " + actualList);


    }


    @Then("verify the below columns are listed in result AG")
    public void verifyTheBelowColumnsAreListedInResultAG(List<String> expectedList) {

        System.out.println("expectedList = " + expectedList);

        Assert.assertEquals(expectedList, actualList);


    }
}