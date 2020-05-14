13
https://raw.githubusercontent.com/msa-ez/example-food-delivery/master/store/src/main/java/fooddelivery/%EA%B2%B0%EC%A0%9C%EC%B7%A8%EC%86%8C%EB%90%A8.java
package fooddelivery;


public class 결제취소됨 extends AbstractEvent {

    private Long id;
    private Long orderId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
