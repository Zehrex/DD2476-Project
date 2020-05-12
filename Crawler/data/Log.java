1
https://raw.githubusercontent.com/falvojr/stackoverflow-61560293/master/src/main/java/com/falvojr/domain/Log.java
package com.falvojr.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Log {

    @Id
    private ObjectId id;
    private org.bson.Document outbound;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public org.bson.Document getOutbound() {
        return outbound;
    }

    public void setOutbound(org.bson.Document outbound) {
        this.outbound = outbound;
    }

}
