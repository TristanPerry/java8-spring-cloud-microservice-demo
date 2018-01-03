package com.tristanperry.microservices.businesslogicapi.model;

import com.tristanperry.microservices.businesslogicapi.json.FizzBuzzJsonRequest;

import javax.persistence.*;

@Entity
public class FizzBuzz {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String fizz;

    @Column(nullable = false)
    private String buzz;

    @Column
    private Boolean isFizzBuzz;

    public FizzBuzz() {
    }

    public FizzBuzz(String fizz, String buzz, Boolean isFizzBuzz) {
        this.fizz = fizz;
        this.buzz = buzz;
        this.isFizzBuzz = isFizzBuzz;
    }

    public Long getId() {
        return id;
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
        return "FizzBuzz{" +
                "id=" + id +
                ", fizz='" + fizz + '\'' +
                ", buzz='" + buzz + '\'' +
                ", isFizzBuzz=" + isFizzBuzz +
                '}';
    }
}
