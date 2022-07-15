package models;

import java.math.BigDecimal;

public class Bill {

    private String people;
    private BigDecimal balance;

    public Bill(String people, BigDecimal balance) {
        this.people = people;
        this.balance = balance;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
