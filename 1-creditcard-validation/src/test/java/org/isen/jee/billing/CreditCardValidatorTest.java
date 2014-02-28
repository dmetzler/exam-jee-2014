package org.isen.jee.billing;

import static org.junit.Assert.assertTrue;

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

    @Test
    public void itCanValidateAVisaCard() throws Exception {

        CreditCard card = new CreditCardImpl("1111-2222-3333-4444",10,2014,CCType.VISA);

        CCValidator validator = new CCValidatorImpl();

        assertTrue(validator.isValid(card, 370));

    }
}
