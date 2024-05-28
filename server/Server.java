import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void viewProducts(Registry registry) throws Exception {
        Product[] products = {
            (Product) registry.lookup("Ak-47"),
            (Product) registry.lookup("M249"),
            (Product) registry.lookup("P90"),
            (Product) registry.lookup("P2000"),
            (Product) registry.lookup("Laptop"),
            (Product) registry.lookup("T-Shirt")
        };

        for (Product product : products) {
            System.out.println("Name: " + product.Getname());
            System.out.println("Price: " + product.Getprice());
            System.out.println("Description: " + product.Getdesc());

            if (product instanceof Electronics) {
                System.out.println("Warranty: " + ((Electronics) product).getWarranty());
            } else if (product instanceof Clothing) {
                System.out.println("Size: " + ((Clothing) product).getSize());
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            Registry startRMI = LocateRegistry.createRegistry(9200);
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            System.out.println("The server is running");

            // Creating product objects
            ProductImp p1 = new ProductImp("Ak-47", "Assault-Rifle", 200.0);
            ProductImp p2 = new ProductImp("M249", "Light-Machine Gun", 4087.0);
            ProductImp p3 = new ProductImp("P90", "Sub Machine Gun", 2350.0);
            ProductImp p4 = new ProductImp("P2000", "Semi-automatic Pistol", 560.47);
            ElectronicsImp p5 = new ElectronicsImp("Laptop", "Electronics Device", 1200.0, "2 years");
            ClothingImp p6 = new ClothingImp("T-Shirt", "Cotton T-Shirt", 20.0, "L");

            Product stub1 = (Product) UnicastRemoteObject.exportObject(p1, 0);
            Product stub2 = (Product) UnicastRemoteObject.exportObject(p2, 0);
            Product stub3 = (Product) UnicastRemoteObject.exportObject(p3, 0);
            Product stub4 = (Product) UnicastRemoteObject.exportObject(p4, 0);
            Product stub5 = (Product) UnicastRemoteObject.exportObject(p5, 0);
            Product stub6 = (Product) UnicastRemoteObject.exportObject(p6, 0);

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9200);

            Cart cart = new CartImp();
            registry.bind("Cart", cart);
            registry.bind("Ak-47", stub1);
            registry.bind("M249", stub2);
            registry.bind("P90", stub3);
            registry.bind("P2000", stub4);
            registry.bind("Laptop", stub5);
            registry.bind("T-Shirt", stub6);

            viewProducts(registry);
            System.out.println("Exporting and Binding done...");

        } catch (Exception e) {
            System.out.println("Some server error ..." + e);
        }
    }
}
