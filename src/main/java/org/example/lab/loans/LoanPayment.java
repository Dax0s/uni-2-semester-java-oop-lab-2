package org.example.lab.loans;

public record LoanPayment(int year, int month, double monthlyPayment, double interest, double credit, double loanBalance) {
    public double getLoanBalance() {
        return Math.round(loanBalance * 100.0) / 100.0;
    }

    public double getMonthlyPayment() {
        return Math.round(monthlyPayment * 100.0) / 100.0;
    }

    public double getInterest() {
        return Math.round(interest * 100.0) / 100.0;
    }

    public double getCredit() {
        return Math.round(credit * 100.0) / 100.0;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
