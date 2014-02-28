package org.isen.jee.billing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class AccountTest extends ContainerHarness {
    @Inject
    AccountDAO dao;

    @Before
    public void doBefore() throws NamingException {
        dao = getEjb("AccountDAOImpl", AccountDAO.class);
    }

    @Test
    public void itCanCreateAnAccount() throws Exception {
        Account acc = dao.createAccount("John Doe", 200, "0001-0000-0000-0000");
        Long id = acc.getId();
        assertThat(id).isNotNull();

        acc = dao.findAccount(id);
        assertThat(acc.getOwnerName()).isEqualTo("John Doe");
        assertThat(acc.getBalance()).isEqualTo(200);

        dao.deleteAccount(id);
    }

    @Test
    public void itCanDeleteAnAccount() throws Exception {
        Account acc = dao.createAccount("John Doe", 200, "0001-0000-0000-0000");
        Long id = acc.getId();

        dao.deleteAccount(id);

        assertThat(dao.findAccount(id)).isNull();

    }

    @Test
    public void itCanListAccount() throws Exception {

        dao.createAccount("John Doe", 200, "0001-0000-0000-0000");
        dao.createAccount("John Doe", 200, "0002-0000-0000-0000");
        dao.createAccount("John Doe", 200, "0003-0000-0000-0000");

        List<Account> accounts = dao.list();

        assertThat(accounts.size() >= 3).isTrue();

    }

    @Test
    public void itCanFindAnAccountByCreditCard() throws Exception {
        Account acc = dao.createAccount("John Doe", 200, "0004-0000-0000-0000");

        acc = dao.findAccountByCC("0004-0000-0000-0000");
        assertThat(acc.getOwnerName()).isEqualTo("John Doe");
    }

    @Test
    public void itCanAddATransaction() throws Exception {
        Account acc = dao.createAccount("John Doe", 200, "0003-0000-0000-0000");

        Transaction tx = dao.addTransaction(acc.getId(), TxType.CREDIT,
                new DateTime(), "some description", 50);

        tx = dao.findTransaction(tx.getId());

        assertThat(tx.getId()).isNotNull();
        assertThat(tx.getType()).isEqualTo(TxType.CREDIT);
        assertThat(tx.getDescription()).isEqualTo("some description");
        assertThat(tx.getAmount()).isEqualTo(50);

        acc = dao.findAccount(acc.getId());
        assertThat(acc.getTransactions()).hasSize(1);
        assertThat(acc.getBalance()).isEqualTo(250);

    }

}
