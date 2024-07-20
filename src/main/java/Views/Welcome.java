package Views;

import db.MyConnection;
import model.user;
import services.SendOTPService;
import services.UserService;
import services.generateOTP;
import  DAOs.user_DAO;
//import services.userService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public  void welcome_part(){
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME  to the File Hider App");
        System.out.println("Press 1 : To Login ");
        System.out.println("Press 2 : To SignUp");
        System.out.println("Press 3 : To Show Existing Users");
        System.out.println("Press 0 : To Exit ");

        System.out.print("Enter a choice :  ");
        int inp = sc.nextInt();

        switch (inp){
            case 0->{
//                System.exit(1);
                System.out.print("Exiting System");
                int i = 4;
                while(i!=0) {

                    System.out.print(".");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    i--;
                }
                System.exit(1);
            }
            case 1-> login();
            case 2-> signup();
            case 3 -> showUsers();
            case 5 ->{
                new userView("r@123").home();
            }
            default -> {
                System.out.println("You have entered wrong choice. Please try again");
                try {
                    Thread.sleep(1550);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    private  void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the email");
        String email = sc.nextLine();
        try {
            if(user_DAO.isExist(email)) {
                String generatedOTP = generateOTP.getOTP();
                SendOTPService.sendOTP(email, generatedOTP);
                System.out.println("Enter the otp");
                String otp = sc.nextLine();
                if(otp.equals(generatedOTP)) {
                    new userView(email).home();  // userview class ka obj banake home method ko call kiya
                                                 // email as const argument pass kari
//                    System.out.println("welcome");
                } else {
                    System.out.println("Wrong OTP");
                }
            } else {
                System.out.println("User not found");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
    private void signup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name");
        String name = sc.nextLine();
        System.out.println("Enter the email");
        String email = sc.nextLine();
        String generatedOTP = generateOTP.getOTP();
        SendOTPService.sendOTP(email, generatedOTP);
        System.out.println("Enter the otp");
        String otp = sc.nextLine();
        if(otp.equals(generatedOTP)) {
            user user = new user(name, email);
            int response = UserService.saveUser(user);
            switch (response) {
                case 1 -> System.out.println("User registered Successfully!!!");
                case 0 -> System.out.println("User already exists!! Please Login!!");
            }
//            try {
//                if(user_DAO.isExist(user.getEmail())){
//                    // System.out.println("usere xists!");  by me
//                    System.out.println("User already exists!! Please Login !");
//
//                }else{
//                    try {
//                        user_DAO.saveUser(user);
//                        System.out.println("User registered Sucessfully!!!");
//
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }

        } else {
            System.out.println("Wrong OTP entered !");
        }
    }
    private void showUsers()  {
        Connection con = MyConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = ps.executeQuery();
            System.out.println("Current Users:");
            System.out.println("+----------------+------------------+---------------+");
            System.out.println("| ID             |  name            | email         | ");
            System.out.println("+----------------+------------------+---------------+");

            while ((rs.next())){
             int id = rs.getInt(1);
             String name = rs.getString(2);
             String emaill = rs.getString(3);
                System.out.printf("| %-14d | %-16s | %-14s |\n",
                        id, name, emaill);
            }
            System.out.println("+----------------+------------------+---------------+");
            Thread.sleep(450);


        } catch (SQLException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }


}

