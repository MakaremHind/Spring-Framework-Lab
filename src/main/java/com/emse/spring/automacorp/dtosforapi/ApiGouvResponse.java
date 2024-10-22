package com.emse.spring.automacorp.dtosforapi;

import java.util.List;

public record ApiGouvResponse(
        String version,
        String query,
        Integer limit,
        List<ApiGouvFeature> features
) {
}
