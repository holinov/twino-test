package org.fruttech.test.rest.dao;

import org.fruttech.test.rest.data.LoanData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryLoanDataDAO implements LoanDataDAO {
    private final Map<String,LoanData> buffer = new ConcurrentHashMap<>();

    @Override
    public void save(LoanData v) {
        if (v.getLoanId() == null)
            v.setLoanId(UUID.randomUUID().toString());
        buffer.put(v.getLoanId(), v);
    }

    @Override
    public LoanData get(String loanId) {
        return buffer.get(loanId);
    }

    @Override
    public List<LoanData> getAll() {
        return new ArrayList<>(buffer.values());
    }

    @Override
    public List<LoanData> getAllByUserId(String personalId) {
        return buffer.values().stream()
                .filter(l-> personalId.equals(l.getPersonalId()))
                .collect(Collectors.toList());
    }
}

