package de.ft.res;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Reservation r = new Reservation(new Customer(), 0, 0, new Date());
        // call getTotalFee to make sure the compiler doesn't throw it away
        r.getTotalFee();
    }
}
