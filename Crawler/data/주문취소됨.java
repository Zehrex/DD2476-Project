13
https://raw.githubusercontent.com/msa-ez/example-food-delivery/master/pay/src/main/java/fooddelivery/%EC%A3%BC%EB%AC%B8%EC%B7%A8%EC%86%8C%EB%90%A8.java
package fooddelivery;


public class 주문취소됨 extends AbstractEvent {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
