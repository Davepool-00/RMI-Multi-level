import java.rmi.RemoteException;

public interface Equipment extends Product {
    String getSize() throws RemoteException;
}
