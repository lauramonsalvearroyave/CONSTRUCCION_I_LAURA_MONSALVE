package com.mycompany.registrationclub;

abstract class Person {

    private String identificationNumber;
    private String name;

    public Person() {
    }

    public Person(String identificationNumber, String name) {
        this.identificationNumber = identificationNumber;
        this.name = name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

}
