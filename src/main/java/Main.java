import Views.Welcome;
import db.MyConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

//        MyConnection mc = new MyConnection()
//
//        System.out.println("Hello world!");
//        Connection mycon = mc

        // how to run it here myconnection class ka object banake

        Welcome wc = new Welcome();
        do{
            wc.welcome_part();
        }while(true);


    }
}