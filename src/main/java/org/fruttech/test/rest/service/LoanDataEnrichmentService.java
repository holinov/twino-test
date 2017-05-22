package org.fruttech.test.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fruttech.test.rest.data.LoanData;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class LoanDataEnrichmentService {
    private static final String URL_BASE = "http://ip-api.com/json/";
    private static final String SUCCESS = "success";
    private static final String STATUS = "status";
    private static final String COUNTRY_CODE = "countryCode";

    public void enrich(LoanData loanData, String clientIp) {
        loanData.setCountryCode(readCountryCode(clientIp));
    }

    private String readCountryCode(String clientIp) {
        final ObjectMapper mapper = new ObjectMapper();
        String countryCode = "lv";
        try {
            @SuppressWarnings("unchecked")
            final Map<String, Object> map = mapper.readValue(new URL(URL_BASE +clientIp), Map.class);
            if(SUCCESS.equals(map.get(STATUS))
                    && map.containsKey(COUNTRY_CODE)) {
                countryCode = map.get(COUNTRY_CODE).toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryCode;
    }
}
