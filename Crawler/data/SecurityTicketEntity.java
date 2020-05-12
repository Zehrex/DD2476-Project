2
https://raw.githubusercontent.com/billsonnn/nitro-java/master/application/src/main/java/com/nitro/application/database/entities/SecurityTicketEntity.java
package com.nitro.application.database.entities;

import com.nitro.application.database.abstracts.EntityTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "security_tickets")
public class SecurityTicketEntity extends EntityTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "ticket")
    private String ticket;

    @Column(name = "ip_address")
    private String ipAddress;

    public int getId() {
        return this.id;
    }

    public int userId() {
        return this.userId;
    }

    public String getTicket() {
        return this.ticket;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }
}
