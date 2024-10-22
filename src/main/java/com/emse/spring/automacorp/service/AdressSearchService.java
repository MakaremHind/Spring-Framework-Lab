//package com.emse.spring.automacorp.service;
//
//import com.emse.spring.automacorp.dtosforapi.ApiGouvAdress;
//import com.emse.spring.automacorp.dtosforapi.ApiGouvResponse;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.util.List;
//
//@Service
//public class AdressSearchService {
//
//    private final RestTemplate restTemplate;
//
//    public AdressSearchService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public List<ApiGouvAdress> searchAdress(List<String> keywords) {
//        // Create query string from keywords
//        String params = String.join("+", keywords);
//
//        // Build the URI for the API call
//        String uri = UriComponentsBuilder.fromUriString("https://api-adresse.data.gouv.fr/search")
//                .queryParam("q", params)
//                .queryParam("limit", 15)
//                .build()
//                .toUriString();
//
//        // Call the API and deserialize the response into ApiGouvResponse
//        ApiGouvResponse response = restTemplate.getForObject(uri, ApiGouvResponse.class);
//
//        // Return the list of addresses from the response
//        return response != null ? response.features().stream()
//                .map(ApiGouvFeature::properties)
//                .toList() : List.of();
//    }
//}
