import java.rmi.RemoteException;

// !! multi level from product
public interface Grenade extends Product {
    String getWarranty() throws RemoteException;
}
