package org.fruttech.test.rest.service;

import org.fruttech.test.rest.data.LoanData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LoanValidationServiceTest {

    private ValidationService blacklistLoanValidationService;
    private ValidationService hitCountLoanValidationService;

    @BeforeEach
    void init(){
        blacklistLoanValidationService = new BlacklistLoanValidationService();
        hitCountLoanValidationService = new CountryHitCountValidationService();
    }

    @Test
    void userValidationTest() {
        final List<LoanData> invalidUsers = BlacklistLoanValidationService.BLACKLIST.stream()
                .map(u -> {
                    final LoanData data = new LoanData();
                    data.setPersonalId(u);
                    return data;
                })
                .collect(Collectors.toList());

        final List<LoanData> validUsers = Stream.of("u1", "u2", "u3")
                .map(u -> {
                    final LoanData data = new LoanData();
                    data.setPersonalId(u);
                    return data;
                })
                .collect(Collectors.toList());

        invalidUsers.forEach(l -> assertEquals(false, blacklistLoanValidationService.isValid(l)));
        validUsers.forEach(l -> assertEquals(true, blacklistLoanValidationService.isValid(l)));
    }

    @Test
    void hitRateValidationTest(){
        final LoanData loanData = new LoanData();
        assertTrue(hitCountLoanValidationService.isValid(loanData));
        for (int i =0;i<10;i++){
            hitCountLoanValidationService.isValid(loanData);
        }
        assertFalse(hitCountLoanValidationService.isValid(loanData));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(hitCountLoanValidationService.isValid(loanData));
    }
}
