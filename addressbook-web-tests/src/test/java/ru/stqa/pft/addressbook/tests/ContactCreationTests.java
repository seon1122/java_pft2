package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().clickAddNewContact();
        app.getContactHelper().fillContactForm((new ContactData("Test1", "Test2", "Test Address", "0000000000", "test@email.com")));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }

}