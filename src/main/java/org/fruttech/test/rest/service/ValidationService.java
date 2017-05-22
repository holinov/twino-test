package org.fruttech.test.rest.service;

import org.fruttech.test.rest.data.LoanData;

import java.util.List;

public interface ValidationService{
    boolean isValid(LoanData loan);
}

