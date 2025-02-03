import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class WhatsUTServerMain {

    public static void main(String[] args) {
        Helper helper = Helper.getInstance();

        helper.getAllUsers();

        //erick

        System.out.println("dsfsadfasf");
        try {
            LocateRegistry.createRegistry(1099);
            WhatsUTServerImpl server = new WhatsUTServerImpl();
            Naming.rebind("WhatsUTServer", server);
            System.out.println("WhatsUT Server is running...");
            System.out.println("erikc ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
