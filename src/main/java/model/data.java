package model;

public class data {
    private int id;
    private String fileName;
    private String path;
    private String email;

    public data(int id, String fileName, String path) {
        this.id = id;
        this.fileName = fileName;
        this.path = path;
    }

    public int getId() {
        return id;
    }


    public String getFileName() {
        return fileName;
    }



    public String getPath() {
        return path;
    }


    public String getEmail() {
        return email;
    }


    public data(int id, String fileName, String path, String email) {
        this.id = id;
        this.fileName = fileName;
        this.path = path;
        this.email = email;
    }
}
