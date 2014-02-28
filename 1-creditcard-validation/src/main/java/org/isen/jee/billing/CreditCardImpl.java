package org.isen.jee.billing;

import org.isen.jee.billing.api.CCType;
import org.isen.jee.billing.api.CreditCard;

public class CreditCardImpl implements CreditCard {

    private String cardNumber;
    private int month;
    private int year;
    private CCType type;

    public CreditCardImpl(String cardNumber, int month, int year, CCType type) {
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
        this.type = type;
    }

    @Override
    public CCType getType() {
        return type;
    }

    @Override
    public String getNumber() {
        return cardNumber;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public int getYear() {
        return year;
    }

}
