1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/controller/ItemController.java
package com.loststars.quickbuy.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.loststars.quickbuy.controller.viewobject.ItemVO;
import com.loststars.quickbuy.error.BusinessException;
import com.loststars.quickbuy.error.EmBusinessError;
import com.loststars.quickbuy.response.CommonReturnType;
import com.loststars.quickbuy.service.ItemService;
import com.loststars.quickbuy.service.model.ItemModel;
import com.loststars.quickbuy.service.model.PromoModel;

@RestController
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ItemController extends BaseController {
    
    @Autowired
    private ItemService itemService;
    
    @PostMapping(value = "/create")
    @ResponseBody
    public CommonReturnType createItem(@RequestParam("title") String title, @RequestParam("description") String description,
                                        @RequestParam("price") Double price, @RequestParam("imgUrl") String imgUrl,
                                        @RequestParam("stock") Integer stock) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(BigDecimal.valueOf(price));
        itemModel.setImgUrl(imgUrl);
        itemModel.setStock(stock);
        itemModel = itemService.createItem(itemModel);
        ItemVO itemVO = convertFromModel(itemModel);
        return CommonReturnType.createSuccess(itemVO);
    }
    
    @GetMapping(value = "/get")
    @ResponseBody
    public CommonReturnType getItemById(@RequestParam("id") Integer id) throws BusinessException {
        ItemModel itemModel = itemService.getItemById(id);
        if (itemModel == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        return CommonReturnType.createSuccess(convertFromModel(itemModel));
    }
    
    @GetMapping(value = "/list")
    @ResponseBody
    public CommonReturnType listItems() {
        List<ItemModel> itemModels = itemService.listItems();
        List<ItemVO> itemVOs = itemModels.stream().map((itemModel) -> {
            ItemVO itemVO = convertFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());
        return CommonReturnType.createSuccess(itemVOs);
    }
    
    private ItemVO convertFromModel(ItemModel itemModel) {
        if (itemModel == null) return null;
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        PromoModel promoModel = itemModel.getPromoModel();
        if (promoModel != null) {
            itemVO.setPromoId(promoModel.getId());
            itemVO.setPromoStatus(promoModel.getStatus());
            itemVO.setPromoPrice(promoModel.getPromoItemPrice());
            itemVO.setStartDate(promoModel.getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        } else {
            itemVO.setPromoStatus(PromoModel.STATUS_NO_PROMO);
        }
        return itemVO;
    }
}
