package com.demo.myfirstproject;

import org.springframework.web.bind.annotation.*;

@RestController

public class Controller {
    @PostMapping("/loan")
    public double getinterest(@RequestBody LoanRequest loanRequest) {
        double monthlyinterest = (double) loanRequest.getInterest() / (100 * 12);
        int totalPayment = loanRequest.getYears() * 12;
        double v = Math.pow((1 + monthlyinterest), totalPayment) - 1;
        double q = Math.pow((1 + monthlyinterest), totalPayment);
        double emi = loanRequest.getLoanamount() * monthlyinterest * q / v;
        return emi;


    }


    @PostMapping("/extraamount")
    public double getextraamount(@RequestBody LoanRequest loanRequest) {
        double emi = getinterest(loanRequest);
        double totalpayments = 12 * loanRequest.getYears();
        double totalamountwithinterest = emi * totalpayments;
        double totalinterestpaid = totalamountwithinterest - loanRequest.getLoanamount();
        return totalinterestpaid;
    }

    @PostMapping("/saveamount")
    public double savemonthamount(@RequestBody LoanRequest loanRequest) {
        double emi = getinterest(loanRequest);//1.10
        double extraamount = loanRequest.getExtraamount();//1.00
        double newmonthlypayment = emi + extraamount;
        double newtotalpayments = 12 * loanRequest.getYears() * emi / newmonthlypayment;
        return newtotalpayments;
    }

    @PostMapping("/interestpaid/{months}")
    public double interestPaid(@RequestBody LoanRequest loanRequest, @PathVariable int months) {
        double interestPerMonth = (double) loanRequest.getInterest() / 1200; // 10/12 // 0.1/12
        double interestPaid = loanRequest.getLoanamount() * interestPerMonth * months;
        return interestPaid;
    }

    @PostMapping("/interest/{months}")
    public double interest(@RequestBody LoanRequest loanRequest, @PathVariable int months) {
        double Monthlypayment=  (double)loanRequest.getLoanamount()/1200;
        double interestpaid =Monthlypayment*loanRequest.getLoanamount()  * months;
        return interestpaid;

    }
}
