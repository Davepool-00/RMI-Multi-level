import java.rmi.RemoteException;

// !! multi level from product
public interface Equipment extends Product {
    String getSize() throws RemoteException;
}
