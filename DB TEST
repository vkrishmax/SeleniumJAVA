Selenium Database Test

In every web application's back end, there is a database like SQL Server, My SQL, Oracle or other types of database support functions. When using Selenium to automate UI testing, we need to make sure any user data entered from the UI should be captured in the database. We can use database retrieve statement to retrieve data and then use the data to verify what is entered by the user. 

Considering the example of a user registration. User registers himself/herself with a username and password. By establishing a DB connection and retrieving data from the DB, we can validate the username entered in the UI should match the username in the database. 

Test Case: verify that user login information should be saved in the database when a user successfully registered in a site. 

In this screenshot below, first two users were in the database. A new test user is entered from the application UI, so we will verify the username. 



Step 1: create a java project, add a package and add a JUnit test class "DatabaseValidation.java".  Make sure that download sqljdbc4.jar file from the internet and add to the project build path as external jar file. You need to add Selenium library jar files to the build path as well. 



Step 2:  write the following code "DatabaseValidation.java" class

package com.seleniummaster.DatabaseUtility;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DatabaseValidation {

  private WebDriver driver = null;
  private Connection con = null;
  private Statement stmt = null;
  String baseUrl;

  @Before
  public void setUp() throws Exception {
    // use firefox browser
    driver = new FirefoxDriver();
    baseUrl = "http://www.testexample.com";
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
  }

  @Test
  public void test() throws SQLException, ClassNotFoundException {
    // Load Microsoft SQL Server JDBC driver.
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    // Prepare connection url.
    String url = "jdbc:sqlserver://192.168.1.5:1433;DatabaseName=UserInfo";
    // Get connection to DB.
    con = DriverManager.getConnection(url, "sa", "12345678");
    // Create statement object which would be used in writing DDL and DML
    // SQL statement.
    stmt = con.createStatement();
    // Send SQL SELECT statements to the database via the
    // Statement.executeQuery
    // method which returns the requested information as rows of data in a
    // ResultSet object.
    // define query to read data
    try {
      String query = "select * from userlogin";
      ResultSet result = stmt.executeQuery(query);
      if (result.next()) {
        while (result.next()) {
          // Fetch value of "username" and "password" from "result"
          // object; this will return 2 existing users in the DB.

         
          String username = result.getString("username");
          String password = result.getString("userpassword");
          // print them on the console
          System.out.println("username :" + username);
          System.out.println("password: " + password);
        }
        result.close();
      }
    }

    catch (SQLException ex) {
      System.out.println(ex);
    }
    // Add a new user on the UI
    String newtestusername = "newuser";
    String newtestuserpassword = "newuser";
    // navigate to the site
    driver.get(baseUrl + "/register.php");
    // set new user name "NewTestUser"
    driver.findElement(By.id("userID")).sendKeys(newtestusername);
    // set new user password for the new user "NewTestUser"
    driver.findElement(By.id("password")).sendKeys(newtestuserpassword);
    // click on Add User button
    driver.findElement(By.id("AddUser")).click();
    // verify the welcome message displayed
    System.out
        .println("Is welcome message displayed: "
            + isElementPresent(By
                .xpath("//*[contains(.,'Welcome back ')]")));

    // verify the new user in the database
    // create a query
    String newuserquery = "SELECT * From userlogin where username=?";
    // create a statement
    PreparedStatement stat = con.prepareStatement(newuserquery);
    stat.setString(1, newtestusername);
    try {
      boolean hasResultSet = stat.execute();
      if (hasResultSet) {
        ResultSet result = stat.getResultSet();
        // get new user name from the table

        String newusername = result.getString("username");
        // assert that new user name should be
        assertEquals(newtestusername, newusername);
      }
    } catch (SQLException ex)

    {
      System.out.println(ex);
    } finally {
      con.close();
    }

  }

  @After
  public void tearDown() throws Exception {
    // close the driver
    driver.close();
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

}
