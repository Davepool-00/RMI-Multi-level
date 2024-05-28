import java.rmi.RemoteException;

public class ClothingImp extends ProductImp implements Clothing {
    private String size;

    public ClothingImp(String name, String desc, double price, String size) throws RemoteException {
        super(name, desc, price);
        this.size = size;
    }

    @Override
    public String getSize() throws RemoteException {
        return this.size;
    }
}
