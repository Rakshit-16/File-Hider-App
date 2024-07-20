package services;

import DAOs.user_DAO;
import model.user;

import java.sql.SQLException;

public class UserService {
    public static Integer saveUser(user user){
        try {
            if(user_DAO.isExist(user.getEmail())) {
                return 0;
            } else {
                return user_DAO.saveUser(user);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
