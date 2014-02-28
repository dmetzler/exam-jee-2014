package org.isen.jee.billing.impl;

import java.util.List;

import org.isen.jee.billing.Account;
import org.isen.jee.billing.AccountDAO;
import org.isen.jee.billing.Transaction;
import org.isen.jee.billing.TxType;
import org.joda.time.DateTime;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public Account createAccount(String ownerName, int initialBalance,
            String ccNumber) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Account findAccount(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Account findAccountByCC(String string) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Transaction addTransaction(Long id, TxType credit,
            DateTime dateTime, String description, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAccount(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Account> list() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Transaction findTransaction(Long id) {
        // TODO Auto-generated method stub
        return null;
    }


}
