13
https://raw.githubusercontent.com/msa-ez/example-food-delivery/master/app/src/main/java/fooddelivery/external/%EA%B2%B0%EC%A0%9C%EC%9D%B4%EB%A0%A5ServiceFallback.java
package fooddelivery.external;

/**
 * Created by uengine on 2020. 4. 18..
 */
public class 결제이력ServiceFallback implements 결제이력Service {
    @Override
    public void 결제(결제이력 주문) {
        //do nothing if you want to forgive it

        System.out.println("Circuit breaker has been opened. Fallback returned instead.");
    }
}
