package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05_StepDefinitions {

    @Given("Establish the database connection DS")
    public void establish_the_database_connection_ds() {
        DB_Util.createConnection();
    }
    @When("I execute query to find most popular book genre DS")
    public void i_execute_query_to_find_most_popular_book_genre_ds() {

        String query = "select bc.name, count(*) from book_borrow\n" +
                "inner join books b on book_borrow.book_id = b.id\n" +
                "inner join book_categories bc on b.book_category_id = bc.id\n" +
                "group by name\n" +
                "order by 2 desc;";

        DB_Util.runQuery(query);

        DB_Util.displayAllData();

        System.out.println("DB_Util.getFirstRowFirstColumn() = " + DB_Util.getFirstRowFirstColumn());

    }
    @Then("verify {string} is the most popular book genre DS")
    public void verify_is_the_most_popular_book_genre_ds(String expected) {

        String actual = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(actual,expected);

    }

}
