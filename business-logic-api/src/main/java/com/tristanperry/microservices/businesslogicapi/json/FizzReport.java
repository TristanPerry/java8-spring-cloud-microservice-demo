package com.tristanperry.microservices.businesslogicapi.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tristanperry.microservices.commonresourceservices.json.FizzReportRow;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FizzReport {

    private FizzBuzzJson fizzBuzz;
    private FizzReportRow reportRow;

    public FizzReport() {
    }

    public FizzReport(FizzBuzzJson fizzBuzz, FizzReportRow reportRow) {
        this.fizzBuzz = fizzBuzz;
        this.reportRow = reportRow;
    }

    public FizzBuzzJson getFizzBuzz() {
        return fizzBuzz;
    }

    public void setFizzBuzz(FizzBuzzJson fizzBuzz) {
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
