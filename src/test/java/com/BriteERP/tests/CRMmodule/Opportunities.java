package com.BriteERP.tests.CRMmodule;

import com.BriteERP.pages.CRM.CRMPage;
import com.BriteERP.pages.CRM.CreateAnOpportunityPage;
import com.BriteERP.pages.CRM.ListPage;
import com.BriteERP.pages.CRM.PivotPage;
import com.BriteERP.pages.LoginPage;
import com.BriteERP.tests.TestBase;
import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import com.github.javafaker.Faker;
import static org.testng.Assert.*;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Opportunities extends TestBase {

    @Test (description = "creating opportunities", invocationCount = 5, enabled = true)
    public void test1() {
        // Creating opportunities in CRM Module
        // logging in with Sales Manager credentials
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("sales_manager"), ConfigurationReader.get("sales_manager_password"));

        // navigating to CRM module
        CRMPage crmPage = new CRMPage();
        crmPage.selectModule("CRM");

        // creating opportunity
        crmPage.createOpportunityButton.click();

        Faker faker = new Faker();
        Random random = new Random();
        CreateAnOpportunityPage createAnOpportunityPage = new CreateAnOpportunityPage();
        createAnOpportunityPage.opportunityTitle.sendKeys(faker.book().title());
        createAnOpportunityPage.expectedRevenue.clear();
        createAnOpportunityPage.expectedRevenue.sendKeys("" + ((random.nextInt(25)+1)*100));
        createAnOpportunityPage.createButton.click();
        BrowserUtils.waitFor(3);

    }

    @Test(description = "testing that second opportunity 'Expected Revenue' value on the Pivot board is the same as the 'Expected revenue' value on the list board.")
    public void test2() {

        // login with sales managers credentials
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("sales_manager"), ConfigurationReader.get("sales_manager_password"));

        // go to CRM module
        CRMPage crmPage = new CRMPage();
        crmPage.selectModule("CRM");

        //change to pivot view
        crmPage.pivotView.click();
        BrowserUtils.waitFor(1);

        // getting all the opportunities
        PivotPage pivotPage = new PivotPage();
        pivotPage.total.click();
        pivotPage.total.click();
        BrowserUtils.waitFor(1);
        pivotPage.selectOption("Opportunity");

        int opportunityNumber = 2;
        // saving Expected Revenue from 2nd opportunity
        String expectedValue = "";
        try {
            expectedValue = pivotPage.getSingleCell("Expected Revenue", opportunityNumber).getText();
        } catch (Exception e) {
            System.out.println("Invalid opportunity number");
            return;
        }

        // going to list view and saving Expected Revenue of 2nd opportunity
        crmPage.listView.click();
        ListPage listPage = new ListPage();
        BrowserUtils.waitFor(1);

        // sorting opportunities in alphabetical order like in pivot view
        List<WebElement> headers = listPage.tableHeaders;
        for (WebElement header: headers) {
            if (header.getText().equals("Opportunity")) {
                header.click();
                break;
            }
        }

        BrowserUtils.waitFor(1);
        String actualValue = listPage.getSingleCell("Expected Revenue", opportunityNumber).getText();

        assertEquals(actualValue, expectedValue, "<<< VALUES ARE NOT THE SAME >>>");
    }

    @Test(description = "testing the total expected revenue on the pivot table")
    public void test3() {

        // login with sales managers credentials
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("sales_manager"), ConfigurationReader.get("sales_manager_password"));

        // go to CRM module
        CRMPage crmPage = new CRMPage();
        crmPage.selectModule("CRM");

        //change to pivot view
        crmPage.pivotView.click();

        // getting all the opportunities
        PivotPage pivotPage = new PivotPage();
        pivotPage.total.click();
        pivotPage.total.click();
        BrowserUtils.waitFor(1);
        pivotPage.selectOption("Opportunity");

        // reading the total cell value
        BrowserUtils.waitFor(1);
        String expectedTotalString = pivotPage.getSingleCell("Expected Revenue", 0).getText();
        double expectedTotal = Double.parseDouble(expectedTotalString.replace(",",""));

        // calculating actual total
        List<WebElement> expectedRevenue = pivotPage.getCellsInColumn("Expected Revenue");
        double actualTotal = 0;
        for (int i = 1; i < expectedRevenue.size(); i++) {
            String val = expectedRevenue.get(i).getText().replace(",","");
            actualTotal += Double.parseDouble(val);
        }
        assertEquals(actualTotal, expectedTotal,"<<< TOTAL DISPLAYED IS NOT ACCURATE >>>");

    }
}
