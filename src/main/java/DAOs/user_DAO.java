package DAOs;

import db.MyConnection;
import model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user_DAO {
    public static boolean isExist(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from user");
        ResultSet rs =ps.executeQuery();
        while(rs.next()){
            String e = rs.getString(1);

            if(e.equals(email)){
                return true;
            }
        }
        return false;
    }
    public  static int saveUser (user user) throws SQLException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps1 = connection.prepareStatement("INSERT INTO user VALUES (default,?,?)");
                ps1.setString(1,user.getName());
                ps1.setString(2,user.getEmail());
                return ps1.executeUpdate();


    }

}
