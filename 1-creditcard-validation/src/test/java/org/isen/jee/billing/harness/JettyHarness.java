package org.isen.jee.billing.harness;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.isen.jee.billing.servlet.CreditCardServlet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class JettyHarness {

    private static final int BASE_PORT = 9090;
    private static final int MAX_PORT_DEVIATION = 100;
    private static final String CONTEXT_PATH = "app";

    private HttpClient httpClient;
    private static Server server;

    private static int port = BASE_PORT
            + (int) (Math.random() * MAX_PORT_DEVIATION);

    protected static final String SERVLET_PATH = "/cc";
    protected static final String SERVLET_URI = getBaseUri() + SERVLET_PATH;

    @BeforeClass
    public static void startServer() throws Exception {
        server = new Server(port);
        server.setStopAtShutdown(true);

        WebAppContext context = new WebAppContext();

        String wardir = "src/main/webapp";
        context.setResourceBase(wardir);
        context.setDescriptor(wardir + "WEB-INF/web.xml");

        context.setContextPath("/" + CONTEXT_PATH);
        context.setParentLoaderPriority(true);

        // Uncomment here to add your own servlet implementation

         context.addServlet(new ServletHolder(new CreditCardServlet()),
         SERVLET_PATH);

        server.setHandler(context);
        server.start();

    }

    public static String getBaseUri() {
        return "http://localhost:" + port + "/" + CONTEXT_PATH;
    }

    @AfterClass
    public static void stopServer() throws Exception {
        server.stop();

    }

    @Before
    public void doBefore() throws Exception {
        httpClient = new HttpClient();
    }

    @After
    public void doAfter() throws Exception {
        httpClient.getHttpConnectionManager().closeIdleConnections(0);
    }

    public String get(String uri) throws IOException {
        HttpMethod method = new GetMethod(uri);
        try {
            int responseCode = httpClient.executeMethod(method);
            if (responseCode > 400) {
                throw new WebRuntimeException(responseCode, "Bad request");
            }
            byte[] responseBody = method.getResponseBody();
            return new String(responseBody);
        } finally {
            method.releaseConnection();
        }
    }
}
