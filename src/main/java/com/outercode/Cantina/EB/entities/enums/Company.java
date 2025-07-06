package com.outercode.Cantina.EB.entities.enums;

public enum Company {

    CCAP("CCAP"),
    ONECIA("1_CIA"),
    TWOCIA("2_CIA"),
    THREECIA("3_CIA");

    private String company;

    Company(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
