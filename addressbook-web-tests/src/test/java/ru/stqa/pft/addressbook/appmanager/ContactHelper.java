package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper (WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.xpath("//a[contains(text(), 'home')]"));
    }

    public void submitContactCreation() {
        click(By.name(("submit")));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
    }

    public void initContactEdit() {
        click(By.xpath("//img[@title='Edit']"));
    }

    public void selectContact() {
        click(By.xpath("//input[@type='checkbox' and @name='selected[]']"));
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptContactDeletionAlert() {
        wd.switchTo().alert().accept();
    }

    public void submitContactModification() {
        click(By.xpath("//input[@value='Update'][2]"));
    }
}
