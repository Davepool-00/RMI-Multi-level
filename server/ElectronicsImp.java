import java.rmi.RemoteException;

public class ElectronicsImp extends ProductImp implements Electronics {
    private String warranty;

    public ElectronicsImp(String name, String desc, double price, String warranty) throws RemoteException {
        super(name, desc, price);
        this.warranty = warranty;
    }

    @Override
    public String getWarranty() throws RemoteException {
        return this.warranty;
    }
}
