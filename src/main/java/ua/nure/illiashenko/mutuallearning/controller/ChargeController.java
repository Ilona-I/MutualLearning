package ua.nure.illiashenko.mutuallearning.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ua.nure.illiashenko.mutuallearning.dto.checkout.ChargeRequest;
import ua.nure.illiashenko.mutuallearning.dto.checkout.ChargeRequest.Currency;
import ua.nure.illiashenko.mutuallearning.dto.checkout.ChargeResponse;
import ua.nure.illiashenko.mutuallearning.service.StripeService;

@Controller
public class ChargeController {

    @Autowired
    private StripeService paymentsService;

    @PostMapping("/charge")
    public ChargeResponse charge(ChargeRequest chargeRequest)
        throws StripeException {
        System.out.println("====="+chargeRequest);
        chargeRequest.setDescription("PREMIUM");
        chargeRequest.setAmount(15000);
        chargeRequest.setCurrency(Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        final ChargeResponse chargeResponse = new ChargeResponse();
        chargeResponse.setId(charge.getId());
        chargeResponse.setStatus(charge.getStatus());
        chargeResponse.setChargeId(charge.getId());
        chargeResponse.setBalanceTransaction(charge.getBalanceTransaction());
        return chargeResponse;
    }
}
