package org.fruttech.test.rest.service;

import org.fruttech.test.rest.data.LoanData;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class BlacklistLoanValidationService implements ValidationService {
    public static final Set<String> BLACKLIST = new HashSet<>(Arrays.asList("user1", "user2", "user3"));

    public boolean isValid(LoanData loan) {
        return validateBlacklist(loan);
    }

    private boolean validateBlacklist(LoanData loan) {
        return !BLACKLIST.contains(loan.getPersonalId());
    }

}
