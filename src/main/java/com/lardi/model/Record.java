package com.lardi.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Record {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="id",referencedColumnName="id", insertable=false , updatable=false)
    private User user;

    @Pattern(regexp = "^[A-Za-z]+$")
    @Size(min=4, max = 20)
    private String name;

    @Pattern(regexp = "^[A-Za-z]+$")
    @Size(min=4, max = 20)
    private String lastname;

    @Pattern(regexp = "^[A-Za-z]+$")
    @Size(min=4, max = 20)
    private String surname;

    @NotEmpty
    @Pattern(regexp = "^\\+380\\([0-9]{2}\\)[0-9]{7}$")
    private String phone;

    private String phoneHome;

    private String adress;

    @Email
    private String email;


    public Long getId() {
        return id;
    }

    public Record setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Record setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Record setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Record setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public Record setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
        return this;
    }

    public String getAdress() {
        return adress;
    }

    public Record setAdress(String adress) {
        this.adress = adress;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Record setEmail(String email) {
        this.email = email;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Record setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "Record{" +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", phoneHome='" + phoneHome + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
