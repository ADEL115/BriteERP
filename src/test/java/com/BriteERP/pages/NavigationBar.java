package com.BriteERP.pages;

import com.BriteERP.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class NavigationBar {


    public void selectModule(String module) {

        String tabXpath = "//span[@class='oe_menu_text' and contains(text(), '" + module + "')]";
        Driver.get().findElement(By.xpath(tabXpath)).click();

        WebDriverWait wait = new WebDriverWait(Driver.get(), 5);

        if(module.equalsIgnoreCase("CRM")) module = "Pipeline";
        wait.until(ExpectedConditions.titleContains(module));

    }
}
