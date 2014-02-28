package org.isen.jee.billing.impl;

import java.util.List;

import org.isen.jee.billing.Account;
import org.isen.jee.billing.Transaction;

public class AccountImpl implements Account  {

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getOwnerName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setOwnerName(String ownerName) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getBalance() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getCCNumber() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCCNumber(String ccNumber) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<? extends Transaction> getTransactions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBalance(int balance) {
        // TODO Auto-generated method stub

    }


}
