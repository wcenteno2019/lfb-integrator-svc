
package com.loyalty.lfbintegratorsvc.pojo.response.web;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loan",
    "creditCard",
    "personal"
})
public class Accounts {

    @JsonProperty("loan")
    private List<Loan> loan = null;
    @JsonProperty("creditCard")
    private List<CreditCard> creditCard = null;
    @JsonProperty("personal")
    private List<Personal> personal = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Accounts() {
    }

    /**
     * 
     * @param creditCard
     * @param personal
     * @param loan
     */
    public Accounts(List<Loan> loan, List<CreditCard> creditCard, List<Personal> personal) {
        super();
        this.loan = loan;
        this.creditCard = creditCard;
        this.personal = personal;
    }

    @JsonProperty("loan")
    public List<Loan> getLoan() {
        return loan;
    }

    @JsonProperty("loan")
    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }

    @JsonProperty("creditCard")
    public List<CreditCard> getCreditCard() {
        return creditCard;
    }

    @JsonProperty("creditCard")
    public void setCreditCard(List<CreditCard> creditCard) {
        this.creditCard = creditCard;
    }

    @JsonProperty("personal")
    public List<Personal> getPersonal() {
        return personal;
    }

    @JsonProperty("personal")
    public void setPersonal(List<Personal> personal) {
        this.personal = personal;
    }

}
