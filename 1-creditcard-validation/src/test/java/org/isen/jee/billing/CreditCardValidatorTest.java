package org.isen.jee.billing;

import static org.junit.Assert.*;

import org.isen.jee.billing.api.CCType;
import org.isen.jee.billing.api.CCValidator;
import org.isen.jee.billing.api.CreditCard;
import org.junit.Test;

/**
 * Cette classe permet de tester les différents cas de validation d'une carte de
 * crédit.
 *
 */
public class CreditCardValidatorTest {
    private CCValidator validator = new CCValidatorImpl();

    @Test
    public void itCanValidateAVisaCard() throws Exception {

        CreditCard card = new CreditCardImpl("1111-2222-3333-4444", 10, 2014,
                CCType.VISA);

        assertTrue(validator.isValid(card, 370));

    }

    @Test
    public void itCanInvalidateAVisaCard() throws Exception {
        CreditCard card = new CreditCardImpl("1111-2222-3333-0000", 10, 2014,
                CCType.VISA);
        assertFalse(validator.isValid(card, 370));
    }

    @Test
    public void itCanVerifyBadCardFormat() throws Exception {
        CreditCard card = new CreditCardImpl("01111-2222-3333-4444", 10, 2014,
                CCType.VISA);
        assertNoCCVCanValidateCard(card);

        card = new CreditCardImpl("a111-2222-3333-4444", 10, 2014, CCType.VISA);
        assertNoCCVCanValidateCard(card);

        card = new CreditCardImpl("1111-2222-3333-4444-5555", 10, 2014, CCType.VISA);
        assertNoCCVCanValidateCard(card);

        card = new CreditCardImpl("1-2-3-4", 10, 2014, CCType.VISA);
        assertNoCCVCanValidateCard(card);

    }

    private void assertNoCCVCanValidateCard(CreditCard card) {
        for(int ccv = 0; ccv < card.getType().getMaxCCVNumber(); ccv++) {
            assertFalse("Card " + card.getNumber() +" was validated by " + ccv,validator.isValid(card, ccv));
        }
    }

}
