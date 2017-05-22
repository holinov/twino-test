package org.fruttech.test.rest.dao;

import org.fruttech.test.rest.data.LoanData;

import java.util.List;

public interface LoanDataDAO {
    void save(LoanData v);
    LoanData get(String loanId);
    List<LoanData> getAll();
    List<LoanData> getAllByUserId(String personalId);
}

