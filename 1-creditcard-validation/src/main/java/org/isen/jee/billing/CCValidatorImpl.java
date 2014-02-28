package org.isen.jee.billing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.isen.jee.billing.api.CCValidator;
import org.isen.jee.billing.api.CreditCard;

public class CCValidatorImpl implements CCValidator {

    private static final int VALIDATOR_SALT = 55;
    private static final String CREDIT_CARD_REGEX = "^([0-9]{4})-([0-9]{4})-([0-9]{4})-([0-9]{4})$";
    private static final Pattern CREDIT_CARD_PATTERN = Pattern
            .compile(CREDIT_CARD_REGEX);

    @Override
    public boolean isValid(CreditCard card, int ccv) {
        Matcher m = CREDIT_CARD_PATTERN.matcher(card.getNumber());
        if (!m.find()) {
            return false;
        } else {
            int sum = 0;
            for (int i = 1; i <= m.groupCount(); i++) {
                sum += Integer.parseInt(m.group(i));
            }
            sum += card.getMonth() + card.getYear();
            sum *= VALIDATOR_SALT;
            return sum % card.getType().getMaxCCVNumber() == ccv;

        }
    }

}
