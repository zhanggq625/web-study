package com.buaa.study2.model;

/**
 * Created by zgq on 2016/12/3.
 */
public class Customer {
    private long id;
    private String name;
    private String contact;
    private String telephone;
    private String email;
    private String remack;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemack() {
        return remack;
    }

    public void setRemack(String remack) {
        this.remack = remack;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
