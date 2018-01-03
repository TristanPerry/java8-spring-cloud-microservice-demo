package com.tristanperry.microservices.businesslogicapi.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FizzBuzzJson {

    private String fizz;
    private String buzz;
    private Boolean isFizzBuzz;

    public FizzBuzzJson() {

    }

    public FizzBuzzJson(String fizz) {
        this.fizz = fizz;
    }

    public FizzBuzzJson(String fizz, String buzz, Boolean isFizzBuzz) {
        this.fizz = fizz;
        this.buzz = buzz;
        this.isFizzBuzz = isFizzBuzz;
    }

    public String getFizz() {
        return fizz;
    }

    public void setFizz(String fizz) {
        this.fizz = fizz;
    }

    public String getBuzz() {
        return buzz;
    }

    public void setBuzz(String buzz) {
        this.buzz = buzz;
    }

    public Boolean getFizzBuzz() {
        return isFizzBuzz;
    }

    public void setFizzBuzz(Boolean fizzBuzz) {
        isFizzBuzz = fizzBuzz;
    }

    @Override
    public String toString() {
        return "FizzBuzzJson{" +
                "fizz='" + fizz + '\'' +
                ", buzz='" + buzz + '\'' +
                ", isFizzBuzz=" + isFizzBuzz +
                '}';
    }

}
