package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private String firstname;
    private String lastname;
    private String address;
    private String mobile;
    private String email;
    private String group;
    private int id = Integer.MAX_VALUE;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLast(String lastname) {
        this.lastname = lastname;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, id);
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() { return  group; }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

}
