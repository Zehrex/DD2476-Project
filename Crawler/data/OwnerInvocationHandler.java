38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Proxy%20Pattern/Protection%20Proxy%20Pattern/src/OwnerInvocationHandler.java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class OwnerInvocationHandler implements InvocationHandler {
    private IPerson person;

    public OwnerInvocationHandler(IPerson person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("get")) {
            return method.invoke(person, args);
        } else if (method.getName().equals("setHotOrNotRating")) {
            throw new IllegalAccessException();
        } else if (method.getName().startsWith("set")) {
            return method.invoke(person,args);
        }
        return null;
    }
}
