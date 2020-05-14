21
https://raw.githubusercontent.com/southwind9801/phone_store_demo_springboot/master/src/main/java/com/southwind/phone_store_demo/repository/OrderMasterRepository.java
package com.southwind.phone_store_demo.repository;

import com.southwind.phone_store_demo.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
