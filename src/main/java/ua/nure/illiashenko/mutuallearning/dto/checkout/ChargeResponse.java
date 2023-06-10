package ua.nure.illiashenko.mutuallearning.dto.checkout;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChargeResponse {

    private String id;
    private String status;
    private String chargeId;
    private String balanceTransaction;
}
