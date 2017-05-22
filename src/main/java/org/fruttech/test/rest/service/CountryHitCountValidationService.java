package org.fruttech.test.rest.service;

import org.fruttech.test.rest.data.LoanData;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class CountryHitCountValidationService implements ValidationService {
    private static final Map<String, Long> COUNTRY_HITS_COUNT;
    private static final float MAX_HIT_RATE = 1f;
    private static final Thread COUNTER_THREAD;

    static {
        COUNTRY_HITS_COUNT = new ConcurrentHashMap<>();
        COUNTER_THREAD = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1000);
                    COUNTRY_HITS_COUNT.clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        },"CountryHitCounterThread");

        COUNTER_THREAD.setDaemon(true);
        COUNTER_THREAD.start();
    }


    @Override
    public boolean isValid(LoanData loan) {
        return validateCountryRate(loan);
    }

    private boolean validateCountryRate(LoanData loan) {
        final Long countryHits = addCountryHit(loan.getCountryCode());
        return countryHits <= MAX_HIT_RATE;
    }

    private Long addCountryHit(String countryCode) {
        final long count = COUNTRY_HITS_COUNT.getOrDefault(countryCode, 0L) + 1;
        COUNTRY_HITS_COUNT.put(countryCode, count);
        return count;
    }
}
