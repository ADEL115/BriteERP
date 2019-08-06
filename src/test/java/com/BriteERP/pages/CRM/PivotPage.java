package com.BriteERP.pages.CRM;

import com.BriteERP.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PivotPage {

    public PivotPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy (xpath = "//tr/td[1]")
    public WebElement total;

    public void selectOption(String option) {

        String tabXpath = "//a[contains(text(), '" + option + "')]";
        Driver.get().findElement(By.xpath(tabXpath)).click();

    }

    public List<WebElement> getCellsInColumn(String colName) {

        List<WebElement> columnsNames = Driver.get().findElements(By.xpath("//th"));

        int idx = -1;
        for (int i = 0; i < columnsNames.size(); i++) {
            if (columnsNames.get(i).getText().equalsIgnoreCase(colName)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) throw new RuntimeException("Invalid Column Name");
        List<WebElement> cellsInColumn = Driver.get().findElements(By.xpath("//tr/td["+idx+"]"));
        return cellsInColumn;
    }

    public WebElement getSingleCell(String colName, int opportunityNumber) {

        List<WebElement> columnsNames = Driver.get().findElements(By.xpath("//th"));

        int idx = -1;
        for (int i = 0; i < columnsNames.size(); i++) {
            if (columnsNames.get(i).getText().equalsIgnoreCase(colName)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) throw new RuntimeException("Invalid Column Name");
        return Driver.get().findElement(By.xpath("//tr["+(opportunityNumber+1)+"]/td["+idx+"]"));

    }

}
