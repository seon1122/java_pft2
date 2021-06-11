package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Transient
    private String address;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile = "";
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String home;
    @Column(name = "work")
    @Type(type = "text")
    private String work;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email = "";
    @Transient
    private String email2;
    @Transient
    private String email3;
    @Transient
    private String allEmails;
    @Transient
    private File photo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();
    @XStreamOmitField
    @Expose
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;


    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, mobile, email, id);
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getHome() {
        return home;
    }

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public String getWork() {
        return work;
    }

    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

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

    public int getId() { return id; }
}
