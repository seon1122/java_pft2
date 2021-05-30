package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void contactModificationTest() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Test1", "Test2", "Test Address", "0000000000", "test@email.com", "test1"), true);
        }
        app.getContactHelper().initContactEdit();
        app.getContactHelper().fillContactForm(new ContactData("Test1Edit", "Test2Edit", "Addressedit", "00000001", "testedit@test.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
