package org.isen.jee.billing.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.isen.jee.billing.Account;
import org.isen.jee.billing.Transaction;

@Entity
public class AccountImpl implements Account  {


    @Id
    @GeneratedValue
    public long id;

    private String ownerName;
    private int balance;
    private String ccNumber;

    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    private List<TransactionImpl> transactions = new ArrayList<TransactionImpl>();

    public AccountImpl() {

    }

    public AccountImpl(String ownerName, int initialBalance, String ccNumber) {
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.ccNumber = ccNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;

    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public String getCCNumber() {
        return ccNumber;
    }

    @Override
    public void setCCNumber(String ccNumber) {
        this.ccNumber = ccNumber;

    }

    @Override
    public List<? extends Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public void setBalance(int balance) {
        this.balance = balance;

    }


}
