import java.rmi.RemoteException;

public interface Grenade extends Product {
    String getWarranty() throws RemoteException;
}
