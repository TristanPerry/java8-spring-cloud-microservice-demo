package com.tristanperry.microservices.businesslogicapi;

import com.tristanperry.microservices.businesslogicapi.exception.NonHttpCompliantException;
import com.tristanperry.microservices.businesslogicapi.feign.ReportsApiClient;
import com.tristanperry.microservices.businesslogicapi.json.FizzBuzz;
import com.tristanperry.microservices.businesslogicapi.json.FizzReport;
import com.tristanperry.microservices.businesslogicapi.json.FizzReportRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FizzBuzzController {

    @Autowired
    private ReportsApiClient reportsApiClient;

    @GetMapping("/fizz-buzz")
    @PreAuthorize("#oauth2.hasScope('READ')")
    public List<FizzBuzz> getAllFizzBuzzes() {
        return new ArrayList<>(Arrays.asList(new FizzBuzz("Fizz1", "Buzz1", Boolean.TRUE), new FizzBuzz("Fizz2", null, Boolean.FALSE)));
    }

    @GetMapping("/fizz/report/{fizzId}")
    @PreAuthorize("#oauth2.isUser()")
    // An example of how we can use Feign Client to get data from another microservice, via our Eureka server
    public FizzReport getFizzReportData(@PathVariable Integer fizzId, @RequestHeader String authorization) {
        // TODO completely ignore fizzId for now, this flow doesn't currently make sense as we get all report data, then pick one of them
        final FizzBuzz fizzBuzz = new FizzBuzz("Fizz1", "Buzz1", Boolean.TRUE);

        // Catch exception and handle 'ourselves', instead of using something like Hystrix fallback (which, from prior experience, can be quite fiddly)
        try {
            final ResponseEntity<List<FizzReportRow>> reportData = reportsApiClient.getFizzReportDataFromReportsApi(authorization);
            return new FizzReport(fizzBuzz, reportData.getBody().get(0));

        } catch (Exception ex) {
            // TODO log exception
            throw new NonHttpCompliantException();
        }
    }

    // Allowed without being authenticated due to the HttpSecurity config in ResourceServerConfig
    @GetMapping("/public/fizz-buzz")
    public List<FizzBuzz> getLimitedUnauthenticatedFizzBuzzes() {
        return new ArrayList<>(Arrays.asList(new FizzBuzz("Fizz1"), new FizzBuzz("Fizz2")));
    }

}
