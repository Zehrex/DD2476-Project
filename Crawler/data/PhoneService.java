21
https://raw.githubusercontent.com/southwind9801/phone_store_demo_springboot/master/src/main/java/com/southwind/phone_store_demo/service/PhoneService.java
package com.southwind.phone_store_demo.service;

import com.southwind.phone_store_demo.vo.DataVO;
import com.southwind.phone_store_demo.vo.PhoneInfoVO;
import com.southwind.phone_store_demo.vo.SpecsPackageVO;

import java.util.List;

public interface PhoneService {
    public DataVO findDataVO();
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType);
    public SpecsPackageVO findSpecsByPhoneId(Integer phoneId);
    public void subStock(Integer specsId,Integer quantity);
}
