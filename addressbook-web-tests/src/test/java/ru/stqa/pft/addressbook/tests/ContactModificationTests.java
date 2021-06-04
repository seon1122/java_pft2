package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (app.contact().all().size() == 0) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test1"));
            } else {
                app.goTo().goToHomePage();
            }
            app.contact().createContact(new ContactData().withName("Test1").withLast("Test2").withAddress("Test Address")
                    .withMobile("+0000000000").withEmail("test@email.com").withGroup("test1"), true);
        }
    }

    @Test
    public void contactModificationTest() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withName("Test1Edit").withLast("Test2Edit").withAddress("TestEdit Address")
                .withEmail("testEDIT@email.com").withId(modifiedContact.getId());
        app.contact().modify(contact, false);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
