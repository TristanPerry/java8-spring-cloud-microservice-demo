package com.tristanperry.microservices.businesslogicapi;

import com.tristanperry.microservices.businesslogicapi.exception.NonHttpCompliantException;
import com.tristanperry.microservices.businesslogicapi.exception.NotFoundException;
import com.tristanperry.microservices.businesslogicapi.feign.ReportsApiClient;
import com.tristanperry.microservices.businesslogicapi.json.FizzBuzzJson;
import com.tristanperry.microservices.businesslogicapi.json.FizzReport;
import com.tristanperry.microservices.businesslogicapi.model.FizzBuzz;
import com.tristanperry.microservices.businesslogicapi.model.FizzBuzzRepository;
import com.tristanperry.microservices.commonresourceservices.json.FizzReportRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FizzBuzzController {

    private static final Logger log = LoggerFactory.getLogger(FizzBuzzController.class);

    @Autowired
    private ReportsApiClient reportsApiClient;

    @Autowired
    private FizzBuzzRepository fizzBuzzRepository;

    @GetMapping("/fizz-buzz")
    @PreAuthorize("#oauth2.hasScope('read')")
    public List<FizzBuzzJson> getAllFizzBuzzes() {
        final List<FizzBuzzJson> result = fizzBuzzRepository.findAll().stream()
                .map(FizzBuzzJson::new)
                .collect(Collectors.toList());

        log.debug("We have {} fiz buzzes in our database", result.size());
        return result;
    }

    @GetMapping("/fizz-buzz/{fizzBuzzId}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public FizzBuzzJson getFizzBuzz(@PathVariable Long fizzBuzzId) {
        final FizzBuzz fizzBuzz = fizzBuzzRepository.findOne(fizzBuzzId);

        if (fizzBuzz == null) {
            log.error("No FizzBuzz entity found for id {}", fizzBuzzId);
            throw new NotFoundException();
        }

        final FizzBuzzJson json = new FizzBuzzJson(fizzBuzz);
        log.debug("Retrieved FizzBuzz {} from id {}", json, fizzBuzzId);
        return json;
    }

    @PostMapping("/fizz-buzz")
    @PreAuthorize("#oauth2.hasScope('write')")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFizzBuzz(@RequestBody FizzBuzzJson request, HttpServletResponse response) {
        final FizzBuzz entity = fizzBuzzRepository.save(new FizzBuzz(request.getFizz(), request.getBuzz(), request.getFizzBuzz()));
        log.debug("Created FizzBuzz entity with id {} from request {}", entity.getId(), request);

        response.setHeader("Location", "/fizz-buzz/" + entity.getId());
    }

    @GetMapping("/fizz/report")
    @PreAuthorize("#oauth2.isUser()")
    // An example of how we can use Feign Client to get data from another microservice, via our Eureka server
    public FizzReport getFizzReportData(@RequestHeader String authorization) {
        // Catch exception and handle 'ourselves', instead of using something like Hystrix fallback
        try {
            final ResponseEntity<List<FizzReportRow>> reportData = reportsApiClient.getFizzReportDataFromReportsApi(authorization);
            return new FizzReport(new FizzBuzzJson("Fizz1"), reportData.getBody().get(0));

        } catch (Exception ex) {
            log.error("Could not retrieve data from reports-api for authorization header {} due to exception {}", authorization, ex);
            throw new NonHttpCompliantException();
        }
    }

    // Allowed without being authenticated due to the HttpSecurity config in ResourceServerConfig
    @GetMapping("/public/fizz-buzz")
    public List<FizzBuzzJson> getLimitedUnauthenticatedFizzBuzzes() {
        final List<FizzBuzzJson> result = fizzBuzzRepository.findAll().stream()
                .map(entity -> new FizzBuzzJson(entity.getFizz()))
                .collect(Collectors.toList());

        log.debug("We have {} fizes in our database. This is public so no buzz-type data will be revealed.", result.size());
        return result;
    }

}
