package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        } else {
            app.goTo().goToHomePage();
        }
    }

    @Test
    public void testContactCreation() {
        ContactData contact = new ContactData().withName("Test1").withLast("Test2").withAddress("Test Address")
                .withMobile("+0000000000").withEmail("test@email.com").withGroup("test1");
        Contacts before = app.contact().all();
        app.contact().createContact(contact, true);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}