package de.securitysquad.webifier.output.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by samuel on 09.11.16.
 */
public class TestResult {
    @JsonProperty("result")
    private WebifierResultType resultType;

    public WebifierResultType getResultType() {
        return resultType;
    }
}
