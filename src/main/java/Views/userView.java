package Views;

import DAOs.data_DAO;
import model.data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class userView {
    private static String email;
    public userView(String email) {
        this.email =email;
    }
public void home(){
            System.out.println("welcome  " + email);
        do {

          //  System.out.println("welcome  " + email);
            System.out.println("Press 1 to show hidden files");
            System.out.println("Press 2 to hide a new file");
            System.out.println("Press 3 to unhide a file");
            System.out.println("Press 0 to exit");
            Scanner sc = new Scanner(System.in);
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1 -> {
                    try {
                        System.out.println("-----------+-----------------------------------+");
                        System.out.println("|    ID    |              File Name            |");
                        System.out.println("-----------+-----------------------------------+");

                        List<data> files = data_DAO.getAllFiles(email);
                        for (data file : files) {
                            System.out.printf("| %-8d | %-32s |\n",file.getId(),file.getFileName());
//                            System.out.println(file.getId() + " - " + file.getFileName());
                        }
                        System.out.println("-----------+-----------------------------------+");

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    System.out.println("Enter the file path");
                    String path = sc.nextLine();
                    File f = new File(path);
                    data file = new data(0, f.getName(), path, email);
                    try {
                        data_DAO.hideFile(file);
                        System.out.println("file hidden successfully!!");
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {
                    List<data> files = null;
                    try {
                        files = data_DAO.getAllFiles(email);

                        System.out.println("-----------+-----------------------------------+");
                        System.out.println("|    ID    |              File Name            |");
                        System.out.println("-----------+-----------------------------------+");

                        for (data file : files) {
                            System.out.printf("| %-8d | %-32s |\n",file.getId(),file.getFileName());
                        }
                        System.out.println("Enter the id of file to unhide");
                        int id = Integer.parseInt(sc.nextLine());
                        boolean isValidID = false;
                        for (data file : files) {
                            if (file.getId() == id) {
                                isValidID = true;
                                break;
                            }
                        }
                        if (isValidID) {
                            data_DAO.unhide(id);
                            System.out.println("File unhidden Sucessfully !!");
                        } else {
                            System.out.println("Wrong ID");
                        }
                    } catch (SQLException  e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case 0 -> {
                    System.out.print("Exiting System");
                    int i = 5;
                    while(i!=0) {

                        System.out.print(".");
                        try {
                            Thread.sleep(450);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        i--;
                    }
                    System.exit(0);
                }
            }
        } while (true);
    }


}



