2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/database/abstracts/EntityTimestamp.java
package com.nitro.application.database.abstracts;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
public abstract class EntityTimestamp
{
    @ColumnDefault("'CURRENT_TIMESTAMP'")
    @Column(name = "timestamp_created", nullable = false)
    private Date timestampCreated;

    @ColumnDefault("'CURRENT_TIMESTAMP'")
    @Column(name = "timestamp_updated", nullable = false)
    private Date timestampUpdated;

    @PrePersist
    protected void onCreate()
    {
        timestampUpdated = timestampCreated = new Date();
    }

    @PreUpdate
    protected void onUpdate()
    {
        timestampUpdated = new Date();
    }

    public Date getTimestampCreated() {
        return this.timestampCreated;
    }

    public Date getTimestampUpdated() {
        return this.timestampUpdated;
    }
}
