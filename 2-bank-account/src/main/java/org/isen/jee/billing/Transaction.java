package org.isen.jee.billing;

import org.joda.time.DateTime;

public interface Transaction {

    Long getId();

    TxType getType();

    String getDescription();

    int getAmount();

    DateTime getDate();

    void setType(TxType type);

    void setDescription(String description);

    void setAmount(int amount);

    void setDate(DateTime date);

}
