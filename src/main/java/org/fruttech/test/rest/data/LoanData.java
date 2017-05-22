package org.fruttech.test.rest.data;

public class LoanData {
    private String loanId;
    private Float amount;
    private String term;
    private String name;
    private String surname;
    private String personalId;
    private String countryCode = "lv";
    private Boolean isValid;

    public LoanData() {
    }

    public LoanData(Float amount, String term, String name, String surname, String personalId) {
        this.amount = amount;
        this.term = term;
        this.name = name;
        this.surname = surname;
        this.personalId = personalId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
