package com.mycompany.registrationclub;

import java.util.ArrayList;

public class Member extends Person {

    private double fundsAvailable;
    private String membershipType;
    private ArrayList<String> authorizedPeople = new ArrayList();

    public Member() {
    }

    public void increaseFunds(double amount) {   
        if ("VIP".equals(membershipType) && fundsAvailable + amount < 5000000) {
            fundsAvailable += amount;
            System.out.println("Ahora el monto disponible para consumo es: " + fundsAvailable);
        } else if ("Regular".equals(membershipType) && fundsAvailable + amount < 1000000) {
            fundsAvailable += amount;
            System.out.println("Ahora el monto disponible para consumo es: " + fundsAvailable);
        } else {
            System.out.println("Ha superado el valor máximo de aumento de fondos");
        }
    }

    public void initializeFunds() {
        if ("VIP".equals(membershipType)) {
            fundsAvailable = 100000;
            System.out.println("Usted cuenta con un fondo inicial de " + fundsAvailable);
        } else if ("Regular".equals(membershipType)) {
            fundsAvailable = 50000;
            System.out.println("Usted cuenta con un fondo inicial de " + fundsAvailable);
        }
    }

    public boolean addAuthorizedPerson(String authorizedPerson) {
        //Check limit of authorized people
        if (authorizedPeople.size() < 10 && fundsAvailable > 0) {
            authorizedPeople.add(authorizedPerson);
            System.out.println("La persona autorizada ha sido ingresado con éxito");
            return true;
        } else {
            System.out.println("Ha alcanzado el número máximo de personas autorizadas");
            return false;
        }
    }

    public boolean removeAuthorizedPerson(String authorizedPerson) {
        if (authorizedPeople.contains(authorizedPerson)) {
            authorizedPeople.remove(authorizedPerson);
            return true;
        } else {
            return false;
        }
    }

    public double getFundsAvailable() {
        return fundsAvailable;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public ArrayList<String> getAuthorizedPeople() {
        return authorizedPeople;
    }

    public void setFundsAvailable(double fundsAvailable) {
        this.fundsAvailable = fundsAvailable;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public void setAuthorizedPeople(ArrayList<String> authorizedPeople) {
        this.authorizedPeople = authorizedPeople;
    }

}
