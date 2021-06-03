package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        ContactData contact = new ContactData()
                .withName("Test1").withLast("Test2").withAddress("Test Address")
                .withMobile("0000000000").withEmail("test@email.com").withGroup("test1");
        if (app.contact().all().size() == 0) {
            app.contact().createContact(contact, true);
        }
    }

    @Test
    public void contactDeletionTest() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().goToHomePage();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
