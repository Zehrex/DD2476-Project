38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Proxy%20Pattern/Protection%20Proxy%20Pattern/src/MainClass.java
import java.lang.reflect.Proxy;

public class MainClass {
    public static void main(String[] args) {
        MainClass mainClass = new MainClass();

        IPerson piyush = mainClass.getPersonFromDB("Piyush");
        IPerson ownerProxy = mainClass.getOwnerProxy(piyush);
        ownerProxy.setGender("Male");

        IPerson nonOwnerProxy = mainClass.getNonOwnerProxy(piyush);
        // Will throw exception
        nonOwnerProxy.setGender("Male");
    }

    IPerson getOwnerProxy(IPerson person) {
        return (IPerson) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person));
    }

    IPerson getNonOwnerProxy(IPerson person) {
        return (IPerson) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person));
    }

    IPerson getPersonFromDB(String name) {
        // let's assume this is fetching from DB
        return null;
    }
}
