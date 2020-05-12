2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/structural/proxy/staticproxy/HomesIntermediary.java
package com.wz.structural.proxy.staticproxy;

/**
 * @author 隔壁老王
 * @create 2020-05-04 15:50
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//代理角色：房屋中介
public class HomesIntermediary implements Rent {

    private Landlord landlord;

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    @Override
    public void Renting() {
        seeHouse();
        landlord.Renting();
        charge();
    }

    //功能增强
    private void seeHouse(){
        System.out.println("中介带你看房");
    }
    //功能增强
    private void charge(){
        //是否收费应根据用户是否满意等进行逻辑判断，此处就不做过多处理了
        System.out.println("收取费用");
    }
}
