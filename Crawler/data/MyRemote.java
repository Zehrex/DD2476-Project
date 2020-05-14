38
https://raw.githubusercontent.com/piyush6348/Design-Patterns/master/Proxy%20Pattern/Remote%20Proxy%20Pattern/src/MyRemote.java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemote extends Remote {
    public String sayHello() throws RemoteException;
}
