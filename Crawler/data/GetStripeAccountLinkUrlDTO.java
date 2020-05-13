2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/dto/GetStripeAccountLinkUrlDTO.java
package com.gardle.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO used for checking requests from stirpe, received over our stripe webhooks
 */
@Data
@AllArgsConstructor
public class GetStripeAccountLinkUrlDTO {
    final String stripeAccountId;
    final String stripeVerificationKey;
}
