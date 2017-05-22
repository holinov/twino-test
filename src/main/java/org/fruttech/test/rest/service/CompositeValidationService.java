package org.fruttech.test.rest.service;

import org.fruttech.test.rest.data.LoanData;

import java.util.List;

public class CompositeValidationService implements ValidationService{
    private final List<ValidationService> services;

    public CompositeValidationService(List<ValidationService> services) {
        this.services = services;
    }

    @Override
    public boolean isValid(LoanData loan) {
        return services.stream().allMatch(s->s.isValid(loan));
    }
}
