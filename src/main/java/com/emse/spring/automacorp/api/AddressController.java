package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.dtosforapi.ApiGouvAdress;
import com.emse.spring.automacorp.service.AdressSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AdressSearchService addressSearchService;

    public AddressController(AdressSearchService addressSearchService) {
        this.addressSearchService = addressSearchService;
    }

    @GetMapping
    public ResponseEntity<List<ApiGouvAdress>> searchAddress(@RequestParam String criteria) {
        List<ApiGouvAdress> results = addressSearchService.searchAdress(Arrays.asList(criteria.split("%20")));
        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(results);
    }
}
