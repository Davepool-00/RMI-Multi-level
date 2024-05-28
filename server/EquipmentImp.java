import java.rmi.RemoteException;

public class EquipmentImp extends ProductImp implements Equipment {
    private String size;

    public EquipmentImp(String name, String desc, double price, String size) throws RemoteException {
        super(name, desc, price);
        this.size = size;
    }

    @Override
    public String getSize() throws RemoteException {
        return this.size;
    }
}
