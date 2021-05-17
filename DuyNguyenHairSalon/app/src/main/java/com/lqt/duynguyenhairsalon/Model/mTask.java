package com.lqt.duynguyenhairsalon.Model;

public class mTask {
    private String NameCustomer;
    private String PhoneNumberCustomer;

    public mTask(String nameCustomer, String phoneNumberCustomer) {
        NameCustomer = nameCustomer;
        PhoneNumberCustomer = phoneNumberCustomer;
    }

    public String getNameCustomer() {
        return NameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        NameCustomer = nameCustomer;
    }

    public String getPhoneNumberCustomer() {
        return PhoneNumberCustomer;
    }

    public void setPhoneNumberCustomer(String phoneNumberCustomer) {
        PhoneNumberCustomer = phoneNumberCustomer;
    }
}
