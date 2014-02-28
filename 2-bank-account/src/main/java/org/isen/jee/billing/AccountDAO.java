package org.isen.jee.billing;

import java.util.List;

import org.joda.time.DateTime;

public interface AccountDAO {

    /**
     * Crée un compte. Le compte retourné doit être déjà persisté en base de
     * données.
     *
     * @param ownerName
     * @param initialBalance
     * @param ccNumber
     * @return retourne l' {@link Account} créé
     */
    public Account createAccount(String ownerName, int initialBalance,
            String ccNumber);

    /**
     * Récupère un compte en connaissant sont id (clé primaire).
     *
     * @param id
     * @return retourne l' {@link Account} trouvé ou null.
     */
    public Account findAccount(Long id);

    /**
     * Récupère un compte en connaissant son numéro de carte de crédit
     *
     * @param string
     * @return retourne l' {@link Account} trouvé ou null.
     */
    public Account findAccountByCC(String creditCardNumber);

    /**
     * Créé une transaction associée à un compte. La balance du compte est
     * recalculée est mise à jour.
     *
     * @param accountId l'id du {@link Account}
     * @param transactionType le {@link TxType} de la transaction
     * @param date la date de l'opération
     * @param description un libellé pour la transaction
     * @param amount le montant non signé de la transaction
     * @return la {@link Transaction} créée.
     */
    public Transaction addTransaction(Long accountId, TxType transactionType,
            DateTime date, String description, int amount);

    /**
     * Supprime un compte.
     * @param accountId l'id du {@link Account}
     */
    public void deleteAccount(Long accountId);

    /**
     * Liste tous les comptes.
     * @return
     */
    public List<Account> list();

    /**
     * Trouve une {@link Transaction} en connaissant sont id.
     * @param id
     * @return
     */
    public Transaction findTransaction(Long id);

}
