= Correction de l'examen JEE 2014

== JEE-1 : Premier test

Ce premier test est le test nominal : on prend une carte de crédit valide, et on vérifie qu'un validateur nous renvoie bien true. Cela nous fait créer deux classes : CreditCardImpl et CCValidatorImpl.

    @Test
    public void itCanValidateAVisaCard() throws Exception {

        CreditCard card = new CreditCardImpl("1111-2222-3333-4444",10,2014,CCType.VISA);
        CCValidator validator = new CCValidatorImpl();
        assertTrue(validator.isValid(card, 370));

    }

Une première implémentation de isValid() peut juste retourner true : on a un premier test qui passe


== JEE-2 : test d'invalidation d'une carte

Un deuxième test va nous obliger à écrire une implémentation moins naïve

    @Test
    public void itCanInvalidateAVisaCard() throws Exception {
        CreditCard card = new CreditCardImpl("1111-2222-3333-0000",10,2014,CCType.VISA);
        CCValidator validator = new CCValidatorImpl();
        assertFalse(validator.isValid(card, 370));
    }

En se basant sur l'exemple StringCalculator du cours, on va utiliser une expression régulière pour matcher le format de la carte et en extraire les valeurs.

Une fois que le test passe, on va refactorer la méthode de test pour avoir moins de redondance au niveau du code.



== JEE-3 : Vérification de formats

On veut vérifier que certain format de nombre ne passent pas. On va donc ajouter un test avec différents nombres de carte malformés. Pour renforcer le test (et suite à une bonne idée de Rémi Roussel), on peut tester qu'aucun CCV ne marche pour une carte malformée. Cela se fait par l'intermédiaire de la méthode suivante :

    private void assertNoCCVCanValidateCard(CreditCard card) {
        for(int ccv = 0; ccv < card.getType().getMaxCCVNumber(); ccv++) {
            assertFalse("Card " + card.getNumber() + " was validated by " + ccv,validator.isValid(card, ccv));
        }
    }



== JEE-4 : Vérification de la date

On va vérifier maintenant qu'une date incorrecte ne permet pas de valider une date.

Cela se fait en ajoutant une clause isValidDate à l'implémentation du validateur. Cela nous permet aussi d'extraire la validation par CCV dans une méthode distincte.

Il faut aussi mettre à jour les test précédents pour qu'il ne plantent pas une fois la date prise au hasard soit dépassée.

== JEE-5 : Vérification d'une AMEX

    On ajoute un test pour vérifier le cas de l'AMEX

    @Test
    public void ItCanValidateAnAmexCard() throws Exception {
        CreditCard card = new CreditCardImpl("1111-2222-3333-0000", 10, 5000,
                CCType.AMEX);
        assertTrue(validator.isValid(card, 2180));
        assertFalse(validator.isValid(card, 3456));
    }