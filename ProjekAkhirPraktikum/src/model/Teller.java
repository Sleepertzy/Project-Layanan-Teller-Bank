package model;

public class Teller extends User {

    private int idTeller;

    private String namaTeller;

    private String statusTeller;

    public Teller() {
    }

    public int getIdTeller() {
        return idTeller;
    }

    public void setIdTeller(int idTeller) {
        this.idTeller = idTeller;
    }

    public String getNamaTeller() {
        return namaTeller;
    }

    public void setNamaTeller(String namaTeller) {
        this.namaTeller = namaTeller;
    }

    public String getStatusTeller() {
        return statusTeller;
    }

    public void setStatusTeller(String statusTeller) {
        this.statusTeller = statusTeller;
    }

    @Override
    public void tampilInfo() {

        System.out.println(
                "Teller : "
                        + namaTeller
        );
    }
}