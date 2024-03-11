package org.example.lab.loans;

import java.util.List;

public abstract class Loan {
    protected double sum;
    protected int years;
    protected int months;
    protected double yearlyPercentage;

    public Loan(double sum, int years, int months, double yearlyPercentage) {
        this.sum = sum;
        this.years = years;
        this.months = months;
        this.yearlyPercentage = yearlyPercentage;
    }

    public abstract List<LoanPayment> GetMonthlyPayments();
}
