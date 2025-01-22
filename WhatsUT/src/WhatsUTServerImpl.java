import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class WhatsUTServerImpl extends UnicastRemoteObject implements WhatsUTServer {
    private final Map<String, String> users = new HashMap<>(); // username -> password
    private final Map<String, Boolean> onlineUsers = new HashMap<>(); // username -> online status
    private final Map<String, List<String>> groups = new HashMap<>(); // group -> members
    private final Map<String, String> groupAdmins = new HashMap<>(); // group -> admin

    public WhatsUTServerImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized boolean registerUser(String username, String password) throws RemoteException {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        users.put(username, encryptPassword(password));
        onlineUsers.put(username, false);
        return true;
    }

    @Override
    public synchronized boolean loginUser(String username, String password) throws RemoteException {
        if (users.containsKey(username) && users.get(username).equals(encryptPassword(password))) {
            onlineUsers.put(username, true);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getOnlineUsers() throws RemoteException {
        List<String> online = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : onlineUsers.entrySet()) {
            if (entry.getValue()) {
                online.add(entry.getKey());
            }
        }
        return online;
    }

    @Override
    public List<String> getGroups() throws RemoteException {
        return new ArrayList<>(groups.keySet());
    }

    @Override
    public synchronized boolean createGroup(String groupName, String admin) throws RemoteException {
        if (groups.containsKey(groupName)) {
            return false; // Group already exists
        }
        groups.put(groupName, new ArrayList<>(List.of(admin)));
        groupAdmins.put(groupName, admin);
        return true;
    }

    @Override
    public synchronized boolean joinGroup(String groupName, String username) throws RemoteException {
        if (groups.containsKey(groupName) && !groups.get(groupName).contains(username)) {
            groups.get(groupName).add(username);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean sendMessageToUser(String from, String to, String message) throws RemoteException {
        if (onlineUsers.getOrDefault(to, false)) {
            System.out.println(from + " to " + to + ": " + message);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean sendMessageToGroup(String groupName, String from, String message) throws RemoteException {
        if (groups.containsKey(groupName) && groups.get(groupName).contains(from)) {
            System.out.println(from + " to group " + groupName + ": " + message);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean sendFile(String from, String to, byte[] fileData, String fileName) throws RemoteException {
        if (onlineUsers.getOrDefault(to, false)) {
            System.out.println(from + " sent file to " + to + ": " + fileName);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean banUser(String admin, String username) throws RemoteException {
        if (users.containsKey(username)) {
            users.remove(username);
            onlineUsers.remove(username);
            return true;
        }
        return false;
    }

    private String encryptPassword(String password) {
        // Simple example; replace with a real encryption algorithm (e.g., BCrypt)
        return Integer.toHexString(password.hashCode());
    }
}
