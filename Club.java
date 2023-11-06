package com.mycompany.registrationclub;

import java.util.ArrayList;

class Club {

    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Invoice> invoices = new ArrayList<>();

    public boolean affiliateMember(Member member) {
        ArrayList<Member> membersVIP = new ArrayList();
        for (Member memberVIP : members) {
            if ("VIP".equals(memberVIP.getMembershipType())) {
                membersVIP.add(memberVIP);
            }
        }
        if (members.size() < 35 && membersVIP.size() <= 3 && !validateExistenceMember(member.getIdentificationNumber())) {
            member.initializeFunds();
            members.add(member);
            System.out.println("El socio ha sido registrado con éxito");
            System.out.println("");
            return true;
        } else {
            System.out.println("Ya existe un socio con el mismo documento de identidad o se ha alcanzado el límite máximo de socios permitidos");
            System.out.println("");
            return false;
        }
    }

    public boolean registerAuthorizedPerson(String memberIdentificationNumber, String authorizedPerson) {
        Member storeMember = findMember(memberIdentificationNumber);
        if (storeMember == null) {
            System.out.println("No se encuentra un socio con el documento ingresado.");
            return false;
        }
        return storeMember.addAuthorizedPerson(authorizedPerson);
    }

    public boolean removeAuthorizedPerson(String memberIdentificationNumber, String authorizedPerson) {
        Member member = findMember(memberIdentificationNumber);
        ArrayList<Invoice> invoicesAuthorizedPeople = getMemberInvoices(authorizedPerson);
        if (invoicesAuthorizedPeople.isEmpty()) {
            System.out.println("La persona autorizada ha sido eliminada con éxito");
            return member.removeAuthorizedPerson(authorizedPerson);
            
        }
        System.out.println("No ha sido posible eliminar la persona solicitada. Verifique que se cumplan todas las condiciones para eliminar la persona autorizada.");
        return false;
    }

    public void increaseFunds(String memberIdentificationNumber, double amount) {
        Member member = findMember(memberIdentificationNumber);
        member.increaseFunds(amount);
    }

    public double getTotalInvoice(Member member) {
        double total = 0;
        for (Invoice invoice : invoices) {
            if (invoice.getMemberName().equals(member.getName())) {
                total += invoice.getInvoicePrice();
            }
        }
        return total;
    }

    public void registerInvoice(Invoice invoice) {
        ArrayList<Invoice> facturasSocio = getMemberInvoices(invoice.getMemberName());
        if (facturasSocio.size() >= 20) {
            System.out.println("No puede tener más de 20 facturas pendientes de pago");
        } else {
            invoices.add(invoice);
        }

    }

    public void deleteInvoice(Invoice invoice) {
        invoices.removeIf(invoiceToDelete -> invoiceToDelete.getMemberName().equals(invoice.getMemberName()));
    }

    public ArrayList<Invoice> getMemberInvoices(String memberName) {
        ArrayList<Invoice> memberInvoices = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.getMemberName().equals(memberName)) {
                memberInvoices.add(invoice);
            }

        }
        return memberInvoices;
    }

    public boolean removeMember(String identificationNumber) {
        Member member = findMember(identificationNumber);

        if (member != null) {
            if ("VIP".equals(member.getMembershipType())) {
                System.out.println("No es posible eliminar socios VIP");
                return false;
            }
            ArrayList<Invoice> memberInvoices = getMemberInvoices(member.getName());
            if (!memberInvoices.isEmpty()) {
                System.out.println("No es posible eliminar socios con facturas pendientes de pago");
                return false;
            }

            if (member.getAuthorizedPeople().size() > 1) {
                System.out.println("No es posible eliminar un socio con una o más personas autorizadas");
                return false;
            }

            members.remove(member);
            System.out.println("Socio eliminado con éxito");
            return true;
        } else {
            System.out.println("No existe un socio con el documento de identidad ingresado");
            return false;
        }
    }

    private boolean validateExistenceMember(String identificationNumber) {
        for (Member member : members) {
            if (member.getIdentificationNumber().equals(identificationNumber)) {
                return true;
            }
        }
        return false;
    }

    private Member findMember(String memberIdentificationNumber) {
        for (Member member : members) {
            if (member.getIdentificationNumber().equals(memberIdentificationNumber)) {
                return member;
            }
        }
        return null;
    }

    public boolean makeConsumption(String memberIdentificationNumber, String invoiceConcept, double invoicePrice, String authorizedName) {
        Member storeMember = findMember(memberIdentificationNumber);
        if (storeMember == null) {
            System.out.println("El documento ingresado no corresponde a ningún socio");
            return false;
        }
        if (storeMember.getFundsAvailable() >= invoicePrice) {
            //Create Invoice
            Invoice invoice;
            if (!authorizedName.isBlank()) {
                invoice = new Invoice(invoiceConcept, invoicePrice, authorizedName);

            } else {
                invoice = new Invoice(invoiceConcept, invoicePrice, storeMember.getName());

            }
            //Register invoice in the system
            registerInvoice(invoice);
            System.out.println("El consumo se ha registrado con éxito");
            return true;
        } else {
            System.out.println("No es posible agregar el consumo");
            return false;
        }
    }

    public boolean payInvoice(String memberIdentificationNumber, Invoice invoice) {
        Member storeMember = findMember(memberIdentificationNumber);

        if (storeMember.getFundsAvailable() >= invoice.getInvoicePrice()) {

            storeMember.setFundsAvailable(storeMember.getFundsAvailable() - invoice.getInvoicePrice());
            deleteInvoice(invoice);
            return true;
        } else {
            return false;
        }
    }
}
