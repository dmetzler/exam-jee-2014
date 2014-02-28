package org.isen.jee.billing.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.isen.jee.billing.Transaction;
import org.isen.jee.billing.TxType;
import org.joda.time.DateTime;

@Entity(name = "Transaction")
@XmlRootElement(name="transaction")
public class TransactionImpl implements Transaction, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    Long id;

    TxType type;

    String description;

    int amount;

    Date date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    AccountImpl account;

    public TransactionImpl() {

    }

    public TransactionImpl(AccountImpl acc, TxType type, DateTime date,
            String description, int amount) {
        account = acc;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date.toDate();

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public TxType getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public DateTime getDate() {
        return new DateTime(date);
    }

    @Override
    public void setType(TxType type) {
        this.type = type;

    }

    @Override
    public void setDescription(String description) {
        this.description = description;

    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;

    }

    @Override
    public void setDate(DateTime date) {
        this.date = date.toDate();
    }

}
