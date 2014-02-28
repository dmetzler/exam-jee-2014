package org.isen.jee.billing;

import org.isen.jee.billing.api.CCValidator;
import org.isen.jee.billing.api.CreditCard;

public class CCValidatorImpl implements CCValidator {

    @Override
    public boolean isValid(CreditCard card, int ccv) {
        return true;
    }

}
