package com.mycompany.registrationclub;


class Invoice {
    private String invoiceConcept;
    private double invoicePrice;
    private String memberName;

    public Invoice(String invoiceConcept, double invoicePrice, String memberName) {
        this.invoiceConcept = invoiceConcept;
        this.invoicePrice = invoicePrice;
        this.memberName = memberName;
    }

    public double getInvoicePrice() {
        return invoicePrice;
    }

    public String getInvoiceConcept() {
        return invoiceConcept;
    }

    public String getMemberName() {
        return memberName;
    }
    
    
}
