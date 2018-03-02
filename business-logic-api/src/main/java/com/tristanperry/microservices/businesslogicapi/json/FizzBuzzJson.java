package com.tristanperry.microservices.businesslogicapi.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tristanperry.microservices.businesslogicapi.model.FizzBuzz;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FizzBuzzJson {

    private Long id;
    private String fizz;
    private String buzz;
    private Boolean isFizzBuzz;

    public FizzBuzzJson() {

    }

    public FizzBuzzJson(String fizz) {
        this.fizz = fizz;
    }

    public FizzBuzzJson(FizzBuzz entity) {
        this.id  = entity.getId();
        this.fizz = entity.getFizz();
        this.buzz = entity.getBuzz();
        this.isFizzBuzz = entity.getFizzBuzz();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", fizz='" + fizz + '\'' +
                ", buzz='" + buzz + '\'' +
                ", isFizzBuzz=" + isFizzBuzz +
                '}';
    }

}
