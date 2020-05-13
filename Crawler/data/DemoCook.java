2
https://raw.githubusercontent.com/chengcheng1021/javaStudy/master/src/java2/Cook/DemoCook.java
package java2.Cook;

/**
 * 需求：
 *      给定一个厨子 Cook 接口，内含唯一的抽象方法 makeFood，且无参数，无返回值
 *      使用 Lambda 的标准格式调用 invokeCook 方法，打印出"吃饭啦！"字样
 */
public class DemoCook {
    public static void main(String[] args) {
        //调用invokeCook方法，参数是cook接口，传递cook接口的匿名内部类对象
        invokeCook(new Cook() {
            @Override
            public void makeFood() {
                System.out.println("吃饭啦！");
            }
        });

        //使用Lambda表达式，简化匿名内部类的书写
        invokeCook(()->{
            System.out.println("吃饭啦！");
        });
    }

    //定义一个方法，参数传递Cook接口，方法内部调用Cook接口的方法makeFood
    public static void invokeCook(Cook cook){
        cook.makeFood();
    }
}
