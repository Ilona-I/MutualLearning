package ua.nure.illiashenko.mutuallearning.controller;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import ua.nure.illiashenko.mutuallearning.dto.checkout.ChargeRequest;
import ua.nure.illiashenko.mutuallearning.dto.checkout.ChargeResponse;
import ua.nure.illiashenko.mutuallearning.service.ChargeService;
import ua.nure.illiashenko.mutuallearning.util.JsonParser;

@Controller
public class ChargeController {

    @Autowired
    private ChargeService chargeService;
    @Autowired
    private JsonParser jsonParser;

    @PostMapping("/charge")
    public ChargeResponse charge(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
        ChargeRequest chargeRequest) throws StripeException {
        final String login = jsonParser.getLoginFromJsonString(authorization);
        return chargeService.charge(login, chargeRequest);
    }
}
