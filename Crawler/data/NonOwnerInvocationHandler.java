38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Proxy%20Pattern/Protection%20Proxy%20Pattern/src/NonOwnerInvocationHandler.java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NonOwnerInvocationHandler implements InvocationHandler {
    private IPerson person;

    public NonOwnerInvocationHandler(IPerson person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("get")) {
            return method.invoke(person, args);
        } else if (method.getName().startsWith("set")) {
            throw new IllegalAccessException();
        }
        return null;
    }
}
