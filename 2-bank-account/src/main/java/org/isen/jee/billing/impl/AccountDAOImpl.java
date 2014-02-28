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

    @PersistenceContext(name = "account")
    EntityManager em;

    @Override
    public Account createAccount(String ownerName, int initialBalance,
            String ccNumber) {
        Account account = new AccountImpl(ownerName, initialBalance, ccNumber);
        em.persist(account);
        return account;

    }

    @Override
    public Account findAccount(Long id) {
        return em.find(AccountImpl.class, id);
    }

    @Override
    public Account findAccountByCC(String number) {
        return em
                .createQuery(
                        "SELECT a FROM AccountImpl a WHERE a.ccNumber = :number",
                        Account.class).setParameter("number", number)
                .getResultList().get(0);
    }

    @Override
    public Transaction addTransaction(Long id, TxType type, DateTime date,
            String description, int amount) {
        Account  acc = findAccount(id);
        Transaction tx = new TransactionImpl((AccountImpl) acc, type, date, description, amount);
        em.persist(tx );
        acc.setBalance(acc.getBalance() + (TxType.CREDIT.equals(type) ? amount : -amount));
        em.merge(acc);
        return tx ;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = findAccount(id);
        if (account != null) {
            em.remove(account);
        }

    }

    @Override
    public List<Account> list() {
        return em.createQuery("SELECT a FROM AccountImpl a", Account.class)
                .getResultList();
    }

    @Override
    public Transaction findTransaction(Long id) {
        return em.find(TransactionImpl.class, id);
    }

}
