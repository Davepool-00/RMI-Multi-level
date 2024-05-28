import java.rmi.RemoteException;

public class GrenadeImp extends ProductImp implements Grenade {
    private String warranty;

    public GrenadeImp(String name, String desc, double price, String warranty) throws RemoteException {
        super(name, desc, price);
        this.warranty = warranty;
    }

    @Override
    public String getWarranty() throws RemoteException {
        return this.warranty;
    }
}
