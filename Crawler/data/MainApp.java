1
https://raw.githubusercontent.com/enriquedevs/java11/master/mainmodule/src/com/enriquedevs/main/MainApp.java
package com.enriquedevs.main;

import com.enriquedevs.librarymodule.Hello;
import com.enriquedevs.librarymodule.HelloInterface;

public class MainApp {
	public static void main(String[] args) {
        Hello.doSomething();
        
        HelloInterface helloInterface = new Hello();
        helloInterface.sayHello();
    }
}
