package model;

public class Admin extends User {

    private int idAdmin;

    public Admin() {
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public void tampilInfo() {

        System.out.println("Admin");
    }
}