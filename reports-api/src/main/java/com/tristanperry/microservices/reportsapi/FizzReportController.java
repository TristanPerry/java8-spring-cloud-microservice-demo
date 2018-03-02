package com.tristanperry.microservices.reportsapi;

import com.tristanperry.microservices.commonresourceservices.json.FizzReportRow;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FizzReportController {

    @GetMapping("/fizz/collate")
    @PreAuthorize("#oauth2.hasScope('report')")
    public List<FizzReportRow> collateFizzReportData() {
        return new ArrayList<>(Arrays.asList(
                new FizzReportRow("0100001101001111010011010101000001001100010010010100001101000001010101000100010101000100", 4235432),
                new FizzReportRow("T0gtSEFJ", 4)));
    }

}
