import java.rmi.RemoteException;

public interface Electronics extends Product {
    String getWarranty() throws RemoteException;
}
