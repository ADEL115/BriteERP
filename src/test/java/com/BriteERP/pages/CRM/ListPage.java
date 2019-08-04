package com.BriteERP.pages.CRM;

import com.BriteERP.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ListPage {

    public ListPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//th")
    public List<WebElement> tableHeaders;

    public List<WebElement> getCellsInColumn(String colName) {

        List<WebElement> columnsNames = Driver.get().findElements(By.xpath("//th"));

        int idx = -1;
        for (int i = 0; i < columnsNames.size(); i++) {
            if (columnsNames.get(i).getText().equalsIgnoreCase(colName)) {
                idx = i+1;
                break;
            }
        }
        List<WebElement> cellsInColumn = Driver.get().findElements(By.xpath("//tr/td["+idx+"]"));
        if (idx == -1) throw new RuntimeException("Invalid Column Name");
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
        return Driver.get().findElement(By.xpath("//tr["+opportunityNumber+"]/td["+(idx+1)+"]"));
    }


}
