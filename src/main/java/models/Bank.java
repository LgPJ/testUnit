package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String name;

    private List<Bill> listBill;

    public Bank() {

        listBill = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bill> getListBill() {
        return listBill;
    }

    public void setListBill(List<Bill> listBill) {
        this.listBill = listBill;
    }

    public void addBill(Bill bill){
        listBill.add(bill);
        bill.setBank(this);
    }

    public void toTransfer(Bill rootAccount, Bill destinationAccount, BigDecimal amount){

        rootAccount.debit(amount);
        destinationAccount.credit(amount);
    }
}
