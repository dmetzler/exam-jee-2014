package org.isen.jee.billing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.isen.jee.billing.impl.AccountImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountServiceTest extends ContainerHarness {

    private AccountDAO dao;
    private AccountServiceClientAPI client;

    @Before
    public void doBefore() throws NamingException {
        dao = getEjb("AccountDAOImpl", AccountDAO.class);
        client = JAXRSClientFactory.create(getServiceURI(),
                AccountServiceClientAPI.class);
    }

    @After
    public void doAfter() {
        for (Account account : dao.list()) {
            dao.deleteAccount(account.getId());
        }
    }

    @Test
    public void itCanGetAnAccount() throws Exception {
        // Given an account created in the DB
        Account acc = dao.createAccount("John Doe", 200, "0001-0000-0000-0000");

        // When i call the REST API to get it
        acc = client.get(acc.getId());

        // Then i get the same values
        assertThat(acc.getOwnerName()).isEqualTo("John Doe");

    }

    @Test
    public void itCanCreateAnAccount() throws Exception {
        // Given an account bean
        AccountImpl acc = new AccountImpl();
        acc.setOwnerName("Jane Doe");
        acc.setBalance(300);
        acc.setCCNumber("0001-0000-0000-0000");

        // When i call the REST API to create it
        acc = client.create(acc);

        // Then it is created in DB
        assertThat(dao.findAccount(acc.getId())).isNotNull();
    }

    @Test
    public void itCanListAccounts() throws Exception {
        // Given some accounts created in DB
        List<Account> accounts = new ArrayList<>();
        accounts.add(dao.createAccount("John Doe", 200, "0001-0000-0000-0000"));
        accounts.add(dao.createAccount("Jane Doe", 150, "0002-0000-0000-0000"));
        accounts.add(dao.createAccount("Steve Doe", 110, "0003-0000-0000-0000"));

        // When i call the REST API to list them
        List<AccountImpl> results = client.list();

        // The i get same resultset size.
        assertThat(results).hasSameSizeAs(accounts);

    }

    @Path("/api/account")
    public static interface AccountServiceClientAPI {

        @GET
        @Path("{id}")
        AccountImpl get(@PathParam("id") long id);

        @POST
        AccountImpl create(AccountImpl acc);

        @GET
        List<AccountImpl> list();

    }

}
