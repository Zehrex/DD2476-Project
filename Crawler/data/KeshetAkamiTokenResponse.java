9
https://raw.githubusercontent.com/idanapp/IdanPlusPlus/master/app/src/main/java/com/example/idan/plusplus/Classes/KeshetAkamiTokenResponse.java
package com.example.idan.plusplus.Classes;

public class KeshetAkamiTokenResponse {
    public String caseId;
    public String status;
    public Ticket[] tickets;

    public class Ticket {
        public String vendor;
        public String ticket;
        public String url;
    }
}
