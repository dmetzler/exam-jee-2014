package org.isen.jee.billing;

import static org.junit.Assert.*;

import org.isen.jee.billing.harness.JettyHarness;
import org.isen.jee.billing.harness.WebRuntimeException;
import org.junit.Test;

/**
 * Cette classe de test démarre un serveur Jetty dans lequel on déploie
 * l'application web courante. On y teste alors la servlet pour différentes
 * valeurs de carte de crédit.
 *
 * @author dmetzler
 *
 */
public class ServletTest extends JettyHarness {

    @Test
    public void aValidCardReturnsAValidResponse() throws Exception {
        assertEquals(
                "ok",
                get(getBaseUri()
                        + "/cc?ccNumber=0001-0000-0000-0000&month=10&year=2014&ccv=375"));

    }

    @Test
    public void aValidAMEXCardReturnsAValidResponse() throws Exception {
        assertEquals(
                "ok",
                get(getBaseUri()
                        + "/cc?ccNumber=0001-0000-0000-0000&month=10&year=2014&ccv=1375&type=amex"));
    }

    @Test
    public void anInvalidCardReturnPreconditionFailed() throws Exception {
        try {
            get(getBaseUri() + "/cc?ccNumber=111");
            fail("Should throw a 412 error");
        } catch (WebRuntimeException e) {
            assertEquals(412, e.getCode());
        }
    }

}
