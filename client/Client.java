import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.List;

public class Client {

    // !! used for casee 1
    public static void viewProducts(Registry registry) throws Exception { //?? checks in the products
        Product[] products = {
            (Product) registry.lookup("Ak-47"),
            (Product) registry.lookup("M249"),
            (Product) registry.lookup("P90"),
            (Product) registry.lookup("P2000"),
            (Product) registry.lookup("Frag-grenade"),
            (Product) registry.lookup("Ghillie-suit")
        };

        for (Product product : products) {
            System.out.println("Name: " + product.Getname());
            System.out.println("Price: " + product.Getprice());
            System.out.println("Description: " + product.Getdesc());

            if (product instanceof Grenade) {
                System.out.println("Warranty: " + ((Grenade) product).getWarranty());
            } else if (product instanceof Equipment) {
                System.out.println("Size: " + ((Equipment) product).getSize());
            }

            System.out.println();
        }
    }

    public static void addProductToCart(Registry registry, Scanner scanner) throws Exception {
        System.out.println("Enter the name of the product to add to the cart:");
        System.out.println("(This is a case-sensitive part)");
        String productName = scanner.next();
        Product product = (Product) registry.lookup(productName);
        Cart cart = (Cart) registry.lookup("Cart");
        cart.addProduct(productName);
        System.out.println("Product added to the cart successfully!");
    }

    public static void viewCart(Registry registry) throws Exception {
        Cart cart = (Cart) registry.lookup("Cart");
        List<String> productsInCart = cart.getProducts();
        if (productsInCart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Products in the cart:");
            for (String productName : productsInCart) {
                Product product = (Product) registry.lookup(productName);
                System.out.println("Name: " + product.Getname());
                System.out.println("Price: " + product.Getprice());
                System.out.println("Description: " + product.Getdesc());

                if (product instanceof Grenade) {
                    System.out.println("Warranty: " + ((Grenade) product).getWarranty());
                } else if (product instanceof Equipment) {
                    System.out.println("Size: " + ((Equipment) product).getSize());
                }

                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9200);
            Scanner scanner = new Scanner(System.in);
            int userInput = 0;
            do {
                System.out.println("\n\n");
                System.out.println("╔════════════════════════════════╗");
                System.out.println("║             MENU               ║");
                System.out.println("╠════════════════════════════════╣");
                System.out.println("║ [1] Display all products       ║");
                System.out.println("║ [2] Add product to cart        ║");
                System.out.println("║ [3] View Cart                  ║");
                System.out.println("║ [0] Exit                       ║");
                System.out.println("╚════════════════════════════════╝");
                System.out.print("Input command: ");
                userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        System.out.println("\n\nDisplaying products\n\n");
                        viewProducts(registry);
                        break;
                    case 2:
                        System.out.println("\n\nAdding a new product to the cart:\n\n");
                        addProductToCart(registry, scanner);
                        break;
                    case 3:
                        System.out.println("\n\nCart inventory:\n\n");
                        viewCart(registry);
                        break;
                    case 0:
                        System.out.println("\n\nExiting...\n\n");
                        break;
                    default:
                        System.out.println("\n\nInvalid input... Try again\n\n");
                        break;
                }
            } while (userInput != 0);

            System.out.println("Client side done...");

        } catch (Exception e) {
            System.out.println("\n\n\nClient side error... " + e);
            System.out.println("================================================");
            System.out.println("Try following command :)");
        }
    }
}
