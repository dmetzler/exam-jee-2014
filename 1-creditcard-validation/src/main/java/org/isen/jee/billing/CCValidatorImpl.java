package org.isen.jee.billing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.isen.jee.billing.api.CCValidator;
import org.isen.jee.billing.api.CreditCard;
import org.joda.time.DateTime;

public class CCValidatorImpl implements CCValidator {

    private static final int VALIDATOR_SALT = 55;
    private static final String CREDIT_CARD_REGEX = "^([0-9]{4})-([0-9]{4})-([0-9]{4})-([0-9]{4})$";
    private static final Pattern CREDIT_CARD_PATTERN = Pattern
            .compile(CREDIT_CARD_REGEX);

    @Override
    public boolean isValid(CreditCard card, int ccv) {

        return card != null && isValidDate(card) && ccvMatchesNumber(card, ccv);

    }

    private boolean isValidDate(CreditCard card) {
        if (card.getMonth() > 12 || card.getMonth() < 0) {
            return false;
        }

        //On calcule la fin du mois
        DateTime cardDate = new DateTime(card.getYear(), card.getMonth(), 15,
                0, 0, 0, 0).dayOfMonth().withMaximumValue();
        return cardDate.isAfterNow();
    }

    private boolean ccvMatchesNumber(CreditCard card, int ccv) {
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
