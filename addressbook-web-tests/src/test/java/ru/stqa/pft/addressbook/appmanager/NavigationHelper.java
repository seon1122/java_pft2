package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void clickAddNewContact() {
        click(By.xpath(("//li[@class='all'][1]")));
    }

    public void goToHomePage() {
        click(By.xpath(("//li/a[contains(text(), 'home')]")));
    }
}
