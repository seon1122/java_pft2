package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactEditByIndex(int i) {
        wd.findElements(By.xpath("//img[@title='Edit']")).get(i).click();
    }

    public void selectContact(int i) {
        wd.findElements(By.xpath("//input[@type='checkbox' and @name='selected[]']")).get(i).click();
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

    public void clickAddNewContact() {
        click(By.xpath(("//li[@class='all'][1]")));
    }

    public void createContact(ContactData data, boolean creation) {
        clickAddNewContact();
        fillContactForm(data, creation);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//input[@type='checkbox' and @name='selected[]']"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            String firstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath("//td/input")).getAttribute("value"));
            ContactData contact = new ContactData(firstName, lastName, null, null, null, null, id);
            contacts.add(contact);
        }
        return contacts;
    }
}
