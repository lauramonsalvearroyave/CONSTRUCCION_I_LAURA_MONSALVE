package com.mycompany.registrationclub;

import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationClub {

    private static Club club = new Club();

    public static void main(String[] args) {

        menu();
    }

    private static void menu() {
        Scanner scann = new Scanner(System.in);

        System.out.println("Seleccione una de las siguientes opciones:");
        System.out.println("1. Registrar socio");
        System.out.println("2. Registrar autorizado");
        System.out.println("3. Registrar consumo");
        System.out.println("4. Pagar una factura");
        System.out.println("5. Aumentar fondo de la cuenta de un socio");
        System.out.println("6. Eliminar autorizado");
        System.out.println("7. Eliminar un socio");
        System.out.println("8. Salir del programa");
        int option = scann.nextInt();
        scann.nextLine();
        boolean exitProgram = false;
        switch (option) {
            case 1:
                Member member = new Member();
                System.out.println("Ingrese nombre completo");
                member.setName(scann.nextLine());
                System.out.println("Ingrese el documento de identidad");
                member.setIdentificationNumber(scann.nextLine());
                System.out.println("Ingrese el tipo de Suscripción: VIP o Regular");
                member.setMembershipType(scann.nextLine());
                club.affiliateMember(member);
                break;

            case 2:
                System.out.println("Ingrese el documento del socio principal");
                String identificationNumber = scann.nextLine();
                System.out.println("Ingrese el nombre de la persona autorizada");
                String authorizedPerson = scann.nextLine();
                club.registerAuthorizedPerson(identificationNumber, authorizedPerson);
                break;

            case 3:
                System.out.println("Ingrese el concepto del consumo");
                String invoiceConcept = scann.nextLine();
                System.out.println("Ingrese el valor del consumo");
                int price = scann.nextInt();
                scann.nextLine();
                System.out.println("Ingrese el documento del socio");
                String memberIdentificationNumber = scann.nextLine();
                System.out.println("Si el consumo es realizado por un autorizado, ingrese el nombre del autorizado");
                String authorizedName = scann.nextLine();
                club.makeConsumption(memberIdentificationNumber, invoiceConcept, price, authorizedName);
                break;

            case 4:
                System.out.println("Ingrese el documento del socio");
                memberIdentificationNumber = scann.nextLine();
                System.out.println("Ingrese el nombre del socio o autorizado de la factura que desea pagar");
                String invoiceName = scann.nextLine();
                ArrayList<Invoice> invoices = club.getMemberInvoices(invoiceName);
                for (int i = 0; i < invoices.size(); i++) {
                    Invoice invoice = invoices.get(i);
                    System.out.println(i + " - Concepto: " + invoice.getInvoiceConcept() + " - Valor: " + invoice.getInvoicePrice());
                }
                System.out.println("Ingrese el número de la factura que desea pagar");
                int invoiceNumber = Integer.parseInt(scann.nextLine());
                Invoice invoiceToPay = invoices.get(invoiceNumber);
                club.payInvoice(memberIdentificationNumber, invoiceToPay);
                break;


            case 5:
                System.out.println("Ingrese el documento de identidad del socio");
                memberIdentificationNumber = scann.nextLine();
                System.out.println("Ingrese el valor del monto que desea aumentar");
                int additionalAmount = scann.nextInt();
                club.increaseFunds(memberIdentificationNumber, additionalAmount);
                break;


            case 6:
                System.out.println("Ingrese el documento de identidad del socio principal");
                memberIdentificationNumber = scann.nextLine();
                System.out.println("Ingrese el nombre de la persona autorizada que desea eliminar");
                String authorizedNameToDelete = scann.nextLine();
                club.removeAuthorizedPerson(memberIdentificationNumber, authorizedNameToDelete);
                break;


            case 7:
                System.out.println("Ingrese el documento de identidad del socio");
                memberIdentificationNumber = scann.nextLine();
                club.removeMember(memberIdentificationNumber);
                break;

            case 8:
                System.out.println("¿Desea salir del programa?");
                exitProgram = scann.nextBoolean();
                break;

            default:
                System.out.println("La opción ingresada no es válida");

        }
        if (!exitProgram) {
            menu();
        }
    }

}
