2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/builder/Conductor.java
package com.wz.creational.builder;

/**
 * @author 隔壁老王
 * @create 2020-04-28 19:24
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//指挥者
public class Conductor {
    private ComputerBuilder computerBuilder;

    public void setComputerBuilder(ComputerBuilder computerBuilder) {
        this.computerBuilder = computerBuilder;
    }

    public Computer AssembleComputer(String CPU,String motherboard,String RAM,String GPU,String monitor){
        this.computerBuilder.buildCPU(CPU);
        this.computerBuilder.buildMotherboard(motherboard);
        this.computerBuilder.buildRAM(RAM);
        this.computerBuilder.buildGPU(GPU);
        this.computerBuilder.buildMonitor(monitor);
        return this.computerBuilder.AssembleComputer();
    }
}
