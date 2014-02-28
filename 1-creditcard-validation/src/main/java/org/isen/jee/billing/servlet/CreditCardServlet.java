package org.isen.jee.billing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.isen.jee.billing.CCValidatorImpl;
import org.isen.jee.billing.CreditCardImpl;
import org.isen.jee.billing.api.CCType;
import org.isen.jee.billing.api.CCValidator;
import org.isen.jee.billing.api.CreditCard;
import org.joda.time.IllegalFieldValueException;

@WebServlet("/cc")
public class CreditCardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            CreditCard card = getCardFromRequest(req);
            int ccv = getCCVFromRequest(req);

            CCValidator validator = new CCValidatorImpl();
            if (validator.isValid(card, ccv)) {
                resp.getWriter().print("ok");
            } else {
                resp.sendError(412, "card not valid");
            }
        } catch (IllegalFieldValueException e) {
            resp.sendError(412, "Illegal values for month or year");
        }
    }

    private int getCCVFromRequest(HttpServletRequest req) {
        return getIntParamater(req, "ccv");
    }

    private CreditCard getCardFromRequest(HttpServletRequest req) {
        String cardNumber = req.getParameter("ccNumber");
        if (cardNumber == null) {
            return null;
        }

        int month = getIntParamater(req, "month");
        int year = getIntParamater(req, "year");

        String type = req.getParameter("type");
        CCType cardType = "amex".equals(type) ? CCType.AMEX : CCType.VISA;

        return new CreditCardImpl(cardNumber, month, year, cardType);
    }

    private int getIntParamater(HttpServletRequest req, String paramName) {
        String param = req.getParameter(paramName);
        return param == null ? -1 : Integer.parseInt(param);
    }
}
