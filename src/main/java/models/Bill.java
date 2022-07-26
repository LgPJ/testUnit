package models;

import exceptions.InsufficientMoney;

import java.math.BigDecimal;

public class Bill {

    private String people;
    private BigDecimal balance;
    private Bank bank;

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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void debit(BigDecimal amount){

        BigDecimal newBalance = this.balance.subtract(amount);

        if(newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new InsufficientMoney("Insufficient Money");
        }

        this.balance = newBalance;

    }

    public void credit(BigDecimal amount){ this.balance = this.balance.add(amount); }


    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Bill)){
            return false;
        }

        Bill c = (Bill) obj;

        if(this.people == null || this.balance == null){
            return false;
        }

        return this.people.equals(c.getPeople()) && this.balance.equals(c.getBalance());

    }
}
