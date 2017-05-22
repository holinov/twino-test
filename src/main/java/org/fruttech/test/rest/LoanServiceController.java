package org.fruttech.test.rest;

import org.fruttech.test.rest.dao.LoanDataDAO;
import org.fruttech.test.rest.data.LoanData;
import org.fruttech.test.rest.service.LoanDataEnrichmentService;
import org.fruttech.test.rest.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class LoanServiceController {

    public static final String VALID = "VALID";
    public static final String INVALID = "INVALID";

    private LoanDataDAO loanDataDAO;
    private LoanDataEnrichmentService enrichmentService;
    private ValidationService validationService;

    @Autowired
    public void setLoanDataDAO(LoanDataDAO loanDataDAO) {
        this.loanDataDAO = loanDataDAO;
    }

    @Autowired
    public void setEnrichmentService(LoanDataEnrichmentService enrichmentService) {
        this.enrichmentService = enrichmentService;
    }

    @Autowired
    public void setValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    @RequestMapping(value = "/apply", method = POST)
    public String apply(@RequestParam("amount") Float amount,
                        @RequestParam("term") String term,
                        @RequestParam("name") String name,
                        @RequestParam("surname") String surname,
                        @RequestParam("personalId") String personalId,
                        HttpServletRequest request) {
        final LoanData loanData = new LoanData(amount, term, name, surname, personalId);

        enrichmentService.enrich(loanData, request.getRemoteAddr());
        loanData.setValid(validationService.isValid(loanData));
        loanDataDAO.save(loanData);

        return loanData.getValid() ? VALID : INVALID;
    }

    private List<LoanData> filterValid(List<LoanData> dataList){
        return dataList.stream()
                .filter(LoanData::getValid)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/listAll", method = GET)
    public List<LoanData> listAll(){
        return filterValid(loanDataDAO.getAll());
    }

    @RequestMapping(value = "/listAllByUser", method = GET)
    public List<LoanData> listAllByUser(@RequestParam("personalId") String personalId) {
        return filterValid(loanDataDAO.getAllByUserId(personalId));
    }
}

