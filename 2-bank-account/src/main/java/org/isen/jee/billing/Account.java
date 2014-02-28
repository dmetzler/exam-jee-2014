package org.isen.jee.billing;

import java.util.List;


/**
 * Interface d'un copte bancaire.
 * @author dmetzler
 *
 */
public interface Account {

    public Long getId();

    public String getOwnerName();

    public void setOwnerName(String ownerName);

    public int getBalance();

    public String getCCNumber();

    public void setCCNumber(String ccNumber);

    public List<? extends Transaction> getTransactions();

    public void setBalance(int balance);
}
