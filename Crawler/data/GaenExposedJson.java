125
https://raw.githubusercontent.com/DP-3T/dp3t-sdk-backend/develop/dpppt-backend-sdk/dpppt-backend-sdk-model/src/main/java/org/dpppt/backend/sdk/model/gaen/GaenExposedJson.java
package org.dpppt.backend.sdk.model.gaen;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GaenExposedJson {
    @NotNull
    @NotEmpty
    List<GaenKey> gaenKeys;
    Header header;


    public List<GaenKey> getGaenKeys() {
        return this.gaenKeys;
    }

    public Header getHeader() {
        return this.header;
    }


    public GaenExposedJson gaenKeys(List<GaenKey> gaenKeys) {
        this.gaenKeys = gaenKeys;
        return this;
    }

    public GaenExposedJson header(Header header) {
        this.header = header;
        return this;
    }

}