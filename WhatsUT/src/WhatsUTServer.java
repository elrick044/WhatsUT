import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface WhatsUTServer extends Remote {
    boolean registerUser(String username, String password) throws RemoteException;
    boolean loginUser(String username, String password) throws RemoteException;
    List<String> getOnlineUsers() throws RemoteException;
    List<String> getGroups() throws RemoteException;
    boolean createGroup(String groupName, String admin) throws RemoteException;
    boolean joinGroup(String groupName, String username) throws RemoteException;
    boolean sendMessageToUser(String from, String to, String message) throws RemoteException;
    boolean sendMessageToGroup(String groupName, String from, String message) throws RemoteException;
    boolean sendFile(String from, String to, byte[] fileData, String fileName) throws RemoteException;
    boolean banUser(String admin, String username) throws RemoteException;
}
