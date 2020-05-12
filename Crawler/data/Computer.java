2
https://raw.githubusercontent.com/wangIQD/GoF23/master/GOF23/DesignPattern/src/com/wz/creational/builder/v2/Computer.java
package com.wz.creational.builder.v2;

/**
 * @author 隔壁老王
 * @create 2020-04-28 18:59
 * @bilibili https://space.bilibili.com/320299990
 * @description
 */
//模式演进：使用静态内部类，静态内部类就是建造者，这种写法可以实现链式调用
public class Computer {
    //属性过多，只列举一部分
    private String CPU;     //CUP
    private String motherboard;   //主板
    private String RAM;     //内存条
    private String GPU;     //显卡
    private String monitor; //显示器

    public Computer(ComputerBuilder computerBuilder) {
        this.CPU = computerBuilder.CPU;
        this.motherboard = computerBuilder.motherboard;
        this.RAM = computerBuilder.RAM;
        this.GPU = computerBuilder.GPU;
        this.monitor = computerBuilder.monitor;
    }

    public static class ComputerBuilder{
        private String CPU;     //CUP
        private String motherboard;   //主板
        private String RAM;     //内存条
        private String GPU;     //显卡
        private String monitor; //显示器

        public ComputerBuilder buildCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public ComputerBuilder buildMotherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public ComputerBuilder buildRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public ComputerBuilder buildGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public ComputerBuilder buildMonitor(String monitor) {
            this.monitor = monitor;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }

    }



    @Override
    public String toString() {
        return "Computer{" +
                "CPU='" + CPU + '\'' +
                ", motherboard='" + motherboard + '\'' +
                ", RAM='" + RAM + '\'' +
                ", GPU='" + GPU + '\'' +
                ", monitor='" + monitor + '\'' +
                '}';
    }
}
