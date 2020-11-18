package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void contactModificationTest() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactEdit();
        app.getContactHelper().fillContactForm(new ContactData("Test1Edit", "Test2Edit", "Addressedit", "00000001", "testedit@test.com"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
