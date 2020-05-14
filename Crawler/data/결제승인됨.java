13
https://raw.githubusercontent.com/msa-ez/example-food-delivery/master/store/src/main/java/fooddelivery/%EA%B2%B0%EC%A0%9C%EC%8A%B9%EC%9D%B8%EB%90%A8.java

package fooddelivery;

import javax.persistence.PrePersist;

public class 결제승인됨 extends AbstractEvent {

    private Long id;
    private String orderId;
    private Double 금액;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Double get금액() {
        return 금액;
    }

    public void set금액(Double 금액) {
        this.금액 = 금액;
    }


    @PrePersist
    public void delay(){
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
