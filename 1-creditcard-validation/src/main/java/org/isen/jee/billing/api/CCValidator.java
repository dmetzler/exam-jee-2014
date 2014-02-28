package org.isen.jee.billing.api;

/**
 * Interface de validation d'une carte de crédit
 *
 * @author dmetzler
 *
 */
public interface CCValidator {

    /**
     * Valide le couple carte de crédit / CCV.
     *
     * @param card une carte de crédit
     * @param ccv le code CCV
     * @return true si le couple est valide, false sinon.
     */
    boolean isValid(CreditCard card, int ccv);

}
