package com.tristanperry.microservices.commonresourceservices.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FizzReportRow {

    private String complicatedData1;
    private Integer someTrickySumValue;

    public FizzReportRow() {
    }

    public FizzReportRow(String complicatedData1, Integer someTrickySumValue) {
        this.complicatedData1 = complicatedData1;
        this.someTrickySumValue = someTrickySumValue;
    }

    public String getComplicatedData1() {
        return complicatedData1;
    }

    public void setComplicatedData1(String complicatedData1) {
        this.complicatedData1 = complicatedData1;
    }

    public Integer getSomeTrickySumValue() {
        return someTrickySumValue;
    }

    public void setSomeTrickySumValue(Integer someTrickySumValue) {
        this.someTrickySumValue = someTrickySumValue;
    }

    @Override
    public String toString() {
        return "FizzReportRow{" +
                "complicatedData1='" + complicatedData1 + '\'' +
                ", someTrickySumValue=" + someTrickySumValue +
                '}';
    }

}
