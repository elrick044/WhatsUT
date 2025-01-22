import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class WhatsUTServerMain {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            WhatsUTServerImpl server = new WhatsUTServerImpl();
            Naming.rebind("WhatsUTServer", server);
            System.out.println("WhatsUT Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
