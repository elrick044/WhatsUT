
import jdk.jfr.internal.util.UserDataException;

import java.sql.*;
import java.util.ArrayList;

public class Helper {
    private final String dbName = "whatsut";
    private final String dbUser = "root";
    private final String dbPass = "260405";
    private final String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;

    private Connection databaseLink;
    private static Helper INSTANCE;

    private Helper(){
        this.connect();
    }
    public static Helper getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Helper();
        }
        return INSTANCE;
    }

    private void connect(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(dbUrl, dbUser, dbPass);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getDatabaseLink() {
        return databaseLink;
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<Meliponario>();


        try {
            PreparedStatement pre = databaseLink.prepareStatement("select * from user");
            pre.execute();
            ResultSet rs = pre.getResultSet();
            while (rs.next()){

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                

                users.add(user);

                System.out.println(rs.getString("id"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return meliponarios;
    }

}
