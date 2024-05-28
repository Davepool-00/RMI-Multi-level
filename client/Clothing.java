import java.rmi.RemoteException;

public interface Clothing extends Product {
    String getSize() throws RemoteException;
}
