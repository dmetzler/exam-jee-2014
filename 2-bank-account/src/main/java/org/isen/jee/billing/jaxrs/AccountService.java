package org.isen.jee.billing.jaxrs;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.isen.jee.billing.Account;
import org.isen.jee.billing.AccountDAO;
import org.isen.jee.billing.impl.AccountImpl;

@Path("/account")
@Produces({ "application/json", "application/xml" })
public class AccountService {

    @Inject
    AccountDAO dao;

    @GET
    @Path("{id}")
    public Account getAccount(@PathParam("id") long id) {
        return dao.findAccount(id);

    }

    @POST
    public Account create(AccountImpl account) {
        Account result = dao.createAccount(account.getOwnerName(), account.getBalance(),
                account.getCCNumber());
        return result;

    }


    @GET
    public List<AccountImpl> list() {
        List<AccountImpl> result = new ArrayList<>();
        for(Account acc : dao.list()) {
            result.add((AccountImpl) acc);
        }
        return result;
    }
}
