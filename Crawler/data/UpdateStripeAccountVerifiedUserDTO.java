2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/service/dto/UpdateStripeAccountVerifiedUserDTO.java
package com.gardle.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStripeAccountVerifiedUserDTO {
    private Boolean verified;
    private String stripeVerificationKey;
}
