package org.isen.jee.billing;

import static org.junit.Assert.*;

import org.isen.jee.billing.api.CCType;
import org.isen.jee.billing.api.CCValidator;
import org.isen.jee.billing.api.CreditCard;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * Cette classe permet de tester les différents cas de validation d'une carte de
 * crédit.
 *
 */
public class CreditCardValidatorTest {
    private CCValidator validator = new CCValidatorImpl();

    private static final DateTime FUTURE_DATE = new DateTime().plusMonths(10);
    private static final DateTime PAST_DATE = new DateTime().minusMonths(2);

    @Test
    public void itCanValidateAVisaCard() throws Exception {

        CreditCard card = new CreditCardImpl("1111-2222-3333-4444", 10, 5000,
                CCType.VISA);
        assertTrue(validator.isValid(card, 600));

    }

    @Test
    public void itCanInvalidateAVisaCard() throws Exception {
        CreditCard card = new CreditCardImpl("1111-2222-3333-0000", 10, 5000,
                CCType.VISA);
        assertFalse(validator.isValid(card, 600));
    }

    @Test
    public void itCanVerifyBadCardFormat() throws Exception {
        CreditCard card = getVisaCardWithGoodDate("01111-2222-3333-4444");
        assertNoCCVCanValidateCard(card);

        card = getVisaCardWithGoodDate("a111-2222-3333-4444");
        assertNoCCVCanValidateCard(card);

        card = getVisaCardWithGoodDate("1111-2222-3333-4444-5555");
        assertNoCCVCanValidateCard(card);

        card = getVisaCardWithGoodDate("1-2-3-4");
        assertNoCCVCanValidateCard(card);

    }

    private CreditCardImpl getVisaCardWithGoodDate(String cardNumber) {
        return new CreditCardImpl(cardNumber, FUTURE_DATE.getMonthOfYear(),
                FUTURE_DATE.getYear(), CCType.VISA);
    }

    @Test
    public void itCanVerifyTheDate() throws Exception {

        CreditCard card = new CreditCardImpl("1111-2222-3333-4444",
                FUTURE_DATE.getMonthOfYear(), FUTURE_DATE.getYear(),
                CCType.VISA);
        assertOnlyOneCCVCanValidateCard(card);

        card = new CreditCardImpl("1111-2222-3333-4444",
                PAST_DATE.getMonthOfYear(), PAST_DATE.getYear(), CCType.VISA);
        assertNoCCVCanValidateCard(card);

    }

    @Test
    public void ItCanValidateAnAmexCard() throws Exception {
        CreditCard card = new CreditCardImpl("1111-2222-3333-0000", 10, 5000,
                CCType.AMEX);
        assertTrue(validator.isValid(card, 2180));
        assertFalse(validator.isValid(card, 3456));
    }

    @Test
    public void aNullCardIsInvalid() throws Exception {
        for (int ccv = 0; ccv < 10000; ccv++) {
            assertFalse(validator.isValid(null, ccv));
        }
    }

    private void assertNoCCVCanValidateCard(CreditCard card) {
        for (int ccv = 0; ccv < card.getType().getMaxCCVNumber(); ccv++) {
            assertFalse(
                    "Card " + card.getNumber() + " was validated by " + ccv,
                    validator.isValid(card, ccv));
        }
    }

    private void assertOnlyOneCCVCanValidateCard(CreditCard card) {
        int count = 0;
        for (int ccv = 0; ccv < card.getType().getMaxCCVNumber(); ccv++) {
            if (validator.isValid(card, ccv)) {
                count++;
            }
        }
        assertEquals("Only one CCV may validate a card", 1, count);
    }

}
