package com.tristanperry.microservices.businesslogicapi.json;

public class FizzBuzzJsonRequest extends FizzBuzzJson {

    private Long id;

    public FizzBuzzJsonRequest() {
    }

    public FizzBuzzJsonRequest(String fizz, String buzz, Boolean isFizzBuzz, Long id) {
        super(fizz, buzz, isFizzBuzz);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FizzBuzzRequest{" +
                "id=" + id +
                "} " + super.toString();
    }
}
