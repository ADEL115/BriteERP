package com.BriteERP.tests;

import com.BriteERP.pages.LoginPage;
import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @Test (description = "Sales Manager positive login")
    public void test1() {

        // testing github fetch
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigurationReader.get("sales_manager"), ConfigurationReader.get("sales_manager_password"));


    }
}
