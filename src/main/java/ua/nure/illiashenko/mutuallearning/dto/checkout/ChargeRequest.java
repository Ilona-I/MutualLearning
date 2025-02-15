package ua.nure.illiashenko.mutuallearning.dto.checkout;

import lombok.Data;

@Data
public class ChargeRequest {

    public enum Currency {
        EUR, USD, UAH
    }

    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}