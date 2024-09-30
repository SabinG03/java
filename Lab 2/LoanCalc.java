public class LoanCalc {
    public static void main(String[] args) {
        double loanAmount = 250000;
        double annualInterestRate = 3;
        double monthlyRepayment = 1600;

        int monthsNeeded = monthsToPayOff(loanAmount, annualInterestRate, monthlyRepayment);
        System.out.println("It will take " + monthsNeeded + " months to pay off the loan.");
    }

    public static int monthsToPayOff(double loanAmount, double annualInterestRate, double monthlyRepayment) {
        // Calculate monthly interest rate
        double monthlyInterestRate = annualInterestRate / 12 / 100;

        // Calculate remaining loan amount after making the monthly repayment
        double remainingLoan = loanAmount * (1 + monthlyInterestRate) - monthlyRepayment;

        // Base case: if remaining loan amount is less than or equal to 0, return 1
        if (remainingLoan <= 0) {
            return 1;
        }

        // Recursive case: call the function with updated parameters
        return 1 + monthsToPayOff(remainingLoan, annualInterestRate, monthlyRepayment);
    }
}
