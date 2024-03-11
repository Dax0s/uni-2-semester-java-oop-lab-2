package org.example.lab.loans;

import java.util.List;

public class LinearLoan extends Loan {

    public LinearLoan(double sum, int years, int months, double yearlyPercentage) {
        super(sum, years, months, yearlyPercentage);
    }

    @Override
    public List<LoanPayment> GetMonthlyPayments() {
        return null;
    }
}
