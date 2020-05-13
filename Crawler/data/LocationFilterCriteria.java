2
https://raw.githubusercontent.com/gardle/gardle-web/master/src/main/java/com/gardle/domain/criteria/LocationFilterCriteria.java
package com.gardle.domain.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationFilterCriteria {
    private Double latitude;
    private Double longitude;
    private Integer radiusInKM;

    public boolean checkAllNull() {
        return latitude == null && longitude == null && radiusInKM == null;
    }

    public boolean checkNonNull() {
        return latitude != null && longitude != null && radiusInKM != null;
    }
}
