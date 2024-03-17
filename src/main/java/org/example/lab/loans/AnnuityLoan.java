package org.example.lab.loans;

import java.util.ArrayList;
import java.util.List;

public class AnnuityLoan extends Loan {
    public AnnuityLoan(double sum, int years, int months, double yearlyPercentage) {
        super(sum, years, months, yearlyPercentage);
    }

    @Override
    public List<LoanPayment> GetMonthlyPayments() {
        double monthlyInterestRate = yearlyPercentage / 100 / 12;
        double up = monthlyInterestRate * Math.pow((1 + monthlyInterestRate), years * 12 + months);
        double down = Math.pow(1 + monthlyInterestRate, years * 12 + months) - 1;
        double monthlyPayment = (float) (sum * (up / down));

        List<LoanPayment> payments = new ArrayList<>();

        double loanBalance = sum;
        int year = 1;
        int month = 1;
        for (int i = 0; i < years * 12 + months; i++) {
            double monthlyInterest = loanBalance * monthlyInterestRate;
            double monthlyCredit = monthlyPayment - monthlyInterest;

            payments.add(new LoanPayment(year, month++, monthlyPayment, monthlyInterest, monthlyCredit, loanBalance));

            if (month > 12) {
                year++;
                month = 1;
            }

            loanBalance -= monthlyCredit;
        }

        return payments;
    }
}
