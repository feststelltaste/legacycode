package de.ft.res;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

class Reservation {
    private int duration;
    private int dailyRate;
    private Date date;
    private Customer customer;
    private List fees = new ArrayList();

    public Reservation(Customer customer, int duration, int dailyRate, Date date) {
        this.customer = customer;
        this.duration = duration;
        this.dailyRate = dailyRate;
        this.date = date;
    }

    public void extend(int additionalDays) {
        duration += additionalDays;
    }

    public void extendForWeek() {
        int weekRemainder = RentalCalendar.weekRemainderFor(date);
        final int DAYS_PER_WEEK = 7;
        extend(weekRemainder);
        dailyRate = RateCalculator.computeWeekly(
                customer.getRateCode()) / DAYS_PER_WEEK;
    }

    public void addFee(FeeRider rider) {
        fees.add(rider);
    }

    int getAdditionalFees() {
        int total = 0;
        for (Iterator it = fees.iterator(); it.hasNext(); ) {
            total += ((FeeRider) (it.next())).getAmount();

        }
        return total;
    }

    int getPrincipalFee() {
        return dailyRate * RateCalculator.rateBase(customer) * duration;
    }

    public int getTotalFee() {
        return getPrincipalFee() + getAdditionalFees();
    }
}

