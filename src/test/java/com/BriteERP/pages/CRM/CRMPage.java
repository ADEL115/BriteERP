package com.BriteERP.pages.CRM;

import com.BriteERP.pages.NavigationBar;
import com.BriteERP.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMPage extends NavigationBar {

    public CRMPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy (xpath = "//button[contains(text(), 'Create')]")
    public WebElement createOpportunityButton;

    @FindBy (xpath = "//button[@aria-label='kanban']")
    public WebElement kanbanView;

    @FindBy (xpath = "//button[@aria-label='list']")
    public WebElement listView;

    @FindBy (xpath = "//button[@aria-label='graph']")
    public WebElement graphView;

    @FindBy (xpath = "//button[@aria-label='pivot']")
    public WebElement pivotView;

    @FindBy (xpath = "//button[@aria-label='calendar']")
    public WebElement calendarView;

}
