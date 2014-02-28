package org.isen.jee.billing;

import org.isen.jee.billing.api.CCType;
import org.isen.jee.billing.api.CreditCard;

public class CreditCardImpl implements CreditCard {

    public CreditCardImpl(String cardNumber, int month, int year, CCType type) {
    }

    @Override
    public CCType getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNumber() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getMonth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getYear() {
        // TODO Auto-generated method stub
        return 0;
    }

}
