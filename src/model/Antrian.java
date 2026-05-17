package model;

public class Antrian {

    private int idAntrian;

    private String nomorAntrian;

    private int idNasabah;

    private String statusAntrian;

    public Antrian() {
    }

    public int getIdAntrian() {
        return idAntrian;
    }

    public void setIdAntrian(int idAntrian) {
        this.idAntrian = idAntrian;
    }

    public String getNomorAntrian() {
        return nomorAntrian;
    }

    public void setNomorAntrian(String nomorAntrian) {
        this.nomorAntrian = nomorAntrian;
    }

    public int getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(int idNasabah) {
        this.idNasabah = idNasabah;
    }

    public String getStatusAntrian() {
        return statusAntrian;
    }

    public void setStatusAntrian(String statusAntrian) {
        this.statusAntrian = statusAntrian;
    }
}