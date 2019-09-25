package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            //return null;
            String ccode=null;
            for(Map.Entry<String,String> entry : countries.entrySet()) {
                if(customer.getCountryName().equals(entry.getValue())) {
                    ccode = entry.getKey();
                    break;
                }
            }
            return ccode;
        }

        @Override
        public String getCompany() {
            //return null;
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            //return null;
            String[] fio = contact.getName().split(", ");
            return fio[1];
        }

        @Override
        public String getContactLastName() {
            //return null;
            String[] fio = contact.getName().split(", ");
            return fio[0];

        }

        @Override
        public String getDialString() {
            //return null;
            String phone = contact.getPhoneNumber().replaceAll("[/\\D+/g]","");
            return "callto://+"+phone;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //For example: UA
        String getCompany();            //For example: JavaRush Ltd.
        String getContactFirstName();   //For example: Ivan
        String getContactLastName();    //For example: Ivanov
        String getDialString();         //For example: callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.
        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan
        String getPhoneNumber();        //For example: +38(050)123-45-67 or +3(805)0123-4567 or +380(50)123-4567 or ...
    }
}