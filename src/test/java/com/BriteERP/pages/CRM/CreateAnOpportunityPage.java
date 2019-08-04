package com.BriteERP.pages.CRM;

import com.BriteERP.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAnOpportunityPage {

    public CreateAnOpportunityPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy (css = "[id*=o_field_input][name=name]")
    public WebElement opportunityTitle;

    @FindBy (css = "input[name=planned_revenue]")
    public WebElement expectedRevenue;

    @FindBy (css = "button[name=close_dialog]")
    public WebElement createButton;


}
