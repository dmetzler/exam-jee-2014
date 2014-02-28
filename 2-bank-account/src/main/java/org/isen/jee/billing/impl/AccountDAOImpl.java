package org.isen.jee.billing.impl;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.isen.jee.billing.Account;
import org.isen.jee.billing.AccountDAO;
import org.isen.jee.billing.Transaction;
import org.isen.jee.billing.TxType;
import org.joda.time.DateTime;

@Singleton
public class AccountDAOImpl implements AccountDAO {


    @PersistenceContext(name="account")
    EntityManager em;

    @Override
    public Account createAccount(String ownerName, int initialBalance,
            String ccNumber) {
        Account account  = new AccountImpl(ownerName, initialBalance, ccNumber);
        em.persist(account);
        return account;

    }

    @Override
    public Account findAccount(Long id) {
        return em.find(AccountImpl.class, id);
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
