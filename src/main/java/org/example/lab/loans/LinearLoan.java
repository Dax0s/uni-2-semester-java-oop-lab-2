package org.example.lab.loans;

import java.util.ArrayList;
import java.util.List;

public class LinearLoan extends Loan {

    public LinearLoan(double sum, int years, int months, double yearlyPercentage) {
        super(sum, years, months, yearlyPercentage);
    }

    @Override
    public List<LoanPayment> GetMonthlyPayments() {
        double monthlyInterestRate = yearlyPercentage / 100 / 12;
        double loanBalance = sum;
        double monthlyPayment = sum / (years * 12 + months);

        List<LoanPayment> payments = new ArrayList<>();

        int year = 1;
        int month = 1;
        for (int i = 0; i < years * 12 + months; i++) {
            double monthlyInterest = loanBalance * monthlyInterestRate;
            payments.add(new LoanPayment(year, month++, monthlyPayment + monthlyInterest, monthlyInterest, monthlyPayment, loanBalance));

            if (month > 12) {
                year++;
                month = 1;
            }

            loanBalance -= monthlyPayment;
        }

        return payments;
    }
}
