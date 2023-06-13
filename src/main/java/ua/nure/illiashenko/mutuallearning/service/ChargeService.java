package ua.nure.illiashenko.mutuallearning.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.illiashenko.mutuallearning.dto.checkout.ChargeRequest;
import ua.nure.illiashenko.mutuallearning.dto.checkout.ChargeRequest.Currency;
import ua.nure.illiashenko.mutuallearning.dto.checkout.ChargeResponse;

@Service
public class ChargeService {

    @Autowired
    private StripeService paymentsService;
    @Autowired
    private UserService userService;

    public ChargeResponse charge(String login, ChargeRequest chargeRequest)
        throws StripeException {
        chargeRequest.setDescription("PREMIUM");
        chargeRequest.setAmount(15000);
        chargeRequest.setCurrency(Currency.UAH);
        Charge charge = paymentsService.charge(chargeRequest);
        if (charge.getStatus().equals("succeeded")) {
            userService.updateToPremiumUser(login);
        }
        final ChargeResponse chargeResponse = new ChargeResponse();
        chargeResponse.setId(charge.getId());
        chargeResponse.setStatus(charge.getStatus());
        chargeResponse.setChargeId(charge.getId());
        chargeResponse.setBalanceTransaction(charge.getBalanceTransaction());
        return chargeResponse;
    }
}
