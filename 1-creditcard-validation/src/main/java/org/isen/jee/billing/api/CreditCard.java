package org.isen.jee.billing.api;

/**
 * Interface d'une carte de crédit.
 * @author dmetzler
 *
 */
public interface CreditCard {

    /**
     * Renvoie le type de la carte de crédit @see {@link CCType}.
     * @return
     */
    CCType getType();

    /**
     * Renvoie le numéro de la carte de crédit.
     * @return
     */
    String getNumber();

    /**
     * Renvoie le mois de validité de la carte de crédit.
     * @return
     */
    int getMonth();

    /**
     * Renvoie l'année de validité de la carte de crédit sur 4 chiffres.
     * @return
     */
    int getYear();
}
