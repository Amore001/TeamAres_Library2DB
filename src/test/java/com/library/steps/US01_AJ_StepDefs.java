package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class US01_AJ_StepDefs{

    String actualUsersNumber;

    @Given("Establish the database connection AJ")
    public void establish_the_database_connection_aj() {

        System.out.println("Connection done with Hooks class");
    }
    @When("Execute query to get all IDs from users AJ")
    public void execute_query_to_get_all_i_ds_from_users_aj() {
        String query = "select count(id) from users";
        DB_Util.runQuery(query);
        actualUsersNumber = DB_Util.getFirstRowFirstColumn();


    }
    @Then("verify all users has unique ID AJ")
    public void verify_all_users_has_unique_id_aj() {

        String query = "select count(distinct id) from users";
        DB_Util.runQuery(query);
        String expectedUsersNumber = DB_Util.getFirstRowFirstColumn();


        Assert.assertEquals(expectedUsersNumber,actualUsersNumber);

    }

    List<String> actualColumnsNames;

    @When("Execute query to get all columns AJ")
    public void execute_query_to_get_all_columns_aj() {

        DB_Util.createConnection();


        String query = "SELECT COLUMN_NAME\n" +
                "FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_NAME = 'users'";

        DB_Util.runQuery(query);

        List<Map<String, String>> actualColumnsName = DB_Util.getAllRowAsListOfMap();

        actualColumnsNames = new ArrayList<>();

        for (Map<String, String> row : actualColumnsName) {
            String rowString = "";
            for (String key : row.keySet()) {
                rowString += row.get(key) + ",";

            }
            rowString = rowString.substring(0, rowString.length() - 1);
            actualColumnsNames.add(rowString);

            System.out.println("actualColumnsNames = " + actualColumnsNames);

        }


    }
    @Then("verify the below columns are listed in result AJ")
    public void verify_the_below_columns_are_listed_in_result_aj(List<String> expectedColumnNames) {

        System.out.println("expectedColumnNames = " + expectedColumnNames);
        Assert.assertEquals(expectedColumnNames,actualColumnsNames);




    }






}
