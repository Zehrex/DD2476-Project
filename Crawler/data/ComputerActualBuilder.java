2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/builder/ComputerActualBuilder.java
package com.wz.creational.builder;

/**
 * @author 隔壁老王
 * @create 2020-04-28 19:14
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//实际建造者
public class ComputerActualBuilder extends ComputerBuilder {

    private Computer computer = new Computer();

    @Override
    public void buildCPU(String CPU) {
        computer.setCPU(CPU);
    }

    @Override
    public void buildMotherboard(String motherboard) {
        computer.setMotherboard(motherboard);
    }

    @Override
    public void buildRAM(String RAM) {
        computer.setRAM(RAM);
    }

    @Override
    public void buildGPU(String GPU) {
        computer.setGPU(GPU);
    }

    @Override
    public void buildMonitor(String monitor) {
        computer.setMonitor(monitor);
    }

    @Override
    public Computer AssembleComputer() {
        return computer;
    }
}
