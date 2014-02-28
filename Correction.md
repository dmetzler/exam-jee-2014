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


