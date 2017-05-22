package org.fruttech.test.rest.dao;

import org.fruttech.test.rest.data.LoanData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoanDataDAOTest {
    private LoanDataDAO dao;

    @BeforeEach
    void init(){
        dao = new InMemoryLoanDataDAO();
    }

    @Test
    void inMemoryLoanDataDAOBasicTest() {
        final LoanData loanData = new LoanData(1.3f, "term", "name", "surname", "pid");
        final LoanData loanData2 = new LoanData(1.3f, "term", "name", "surname", "pid");
        final String loanId = UUID.randomUUID().toString();
        loanData.setLoanId(loanId);

        dao.save(loanData);

        final LoanData readFromDao = dao.get(loanData.getLoanId());
        assertNotNull(readFromDao);
        assertEquals(loanId, readFromDao.getLoanId());

        //CHECK THAT ONLY 1 ENTRY IS STORED
        final List<LoanData> allData = dao.getAll();
        assertEquals(1, allData.size());
        allData.forEach(l -> assertEquals(loanId, l.getLoanId()));

        //CHECK GET BY USER
        final List<LoanData> allByUserId = dao.getAllByUserId(loanData.getPersonalId());
        assertEquals(1,allByUserId.size());
        allByUserId.forEach(l->assertEquals(loanId,l.getLoanId()));

        //CHECK MULTI RESULTS
        dao.save(loanData2);
        assertEquals(2,dao.getAll().size());
    }
}


