package com.tristanperry.microservices.businesslogicapi.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FizzReport {

    private FizzBuzz fizzBuzz;
    private FizzReportRow reportRow;

    public FizzReport() {
    }

    public FizzReport(FizzBuzz fizzBuzz, FizzReportRow reportRow) {
        this.fizzBuzz = fizzBuzz;
        this.reportRow = reportRow;
    }

    public FizzBuzz getFizzBuzz() {
        return fizzBuzz;
    }

    public void setFizzBuzz(FizzBuzz fizzBuzz) {
        this.fizzBuzz = fizzBuzz;
    }

    public FizzReportRow getReportRow() {
        return reportRow;
    }

    public void setReportRow(FizzReportRow reportRow) {
        this.reportRow = reportRow;
    }

    @Override
    public String toString() {
        return "FizzReportRow{" +
                "fizzBuzz=" + fizzBuzz +
                ", reportRow=" + reportRow +
                '}';
    }

}
