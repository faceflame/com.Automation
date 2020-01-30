package com.automation.tests.AkbarTests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class Spartan {

    private String id;
    private String name;
    private String gender;
    private long phone;



    public Spartan(){

    }
    public Spartan(String name, String gender, long phone) {

        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }
    public Spartan(String id, String name, String gender, long phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Long getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Spartan{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                //", max_salary=" + max_salary +
                '}';
    }

}