package org.example.lab.loans;

public record LoanPayment(int year, int month, double sumToPay, double leftToPay) {
    public double getLeftToPay() {
        return Math.round(leftToPay * 100.0) / 100.0;
    }

    public double getSumToPay() {
        return Math.round(sumToPay * 100.0) / 100.0;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
