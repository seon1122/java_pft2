package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectGroupById(group.getId());
        click(By.cssSelector("input[name='add']"));
        returnToHomePage();
    }

    private void selectGroupById(int id) {
        Select select = new Select(wd.findElement(By.cssSelector("select[name='to_group']")));
        select.selectByValue("" + id);
    }

    public void initContactEditById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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
        contactCache = null;
        returnToHomePage();
    }


    public void delete(ContactData data) {
        selectContactById(data.getId());
        deleteSelectedContacts();
        contactCache = null;
        acceptContactDeletionAlert();
    }

    public int count() {
        return wd.findElements(By.cssSelector("tr[name=entry]")).size();
    }

    public void modify(ContactData contact, boolean creation) {
        initContactEditById(contact.getId());
        fillContactForm(contact, creation);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String allphones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withName(firstName).withLast(lastName).withId(id).withAllPhones(allphones).withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactEditById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                .withName(firstname).withLast(lastname).withMobile(mobile).withWork(work).withHome(home).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}
