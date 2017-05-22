package org.fruttech.test.rest;

import org.fruttech.test.rest.dao.InMemoryLoanDataDAO;
import org.fruttech.test.rest.dao.LoanDataDAO;
import org.fruttech.test.rest.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {
    @Bean
    public LoanDataDAO getLoanDataDAO() {
      return new InMemoryLoanDataDAO();
    }

    @Bean
    public LoanDataEnrichmentService getEnrichmentService() {
        return new LoanDataEnrichmentService();
    }

    @Bean
    public ValidationService getValidationService() {
        return new CompositeValidationService(Arrays.asList(new BlacklistLoanValidationService(), new CountryHitCountValidationService()));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
